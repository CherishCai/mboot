package cn.cherish.mboot.util;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/8/28 19:54
 */
@Slf4j
@ToString
public class SnowflakeIdFactory {

    private final long twepoch = 1503922109858L;// 2017/8/28 20:08
    private final long datacenterIdBits = 5L;// 数据中心编号二进制位数
    private final long workerIdBits = 5L; // 工作者编号二进制位数
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long sequenceBits = 12L;// 序号二进制位数
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;


    public SnowflakeIdFactory(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            //服务器时钟被调整了,ID生成器停止服务.
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    protected long timeGen() {
        return System.currentTimeMillis();
    }

    public static void testPerSecondProductIdNums() {
        SnowflakeIdFactory idWorker = new SnowflakeIdFactory(1, 1);
        idWorker.nextId();
        long start = System.currentTimeMillis();
        int count = 0;
        for (; System.currentTimeMillis() - start < 1000; count++) {
            /*  测试方法一: 此用法纯粹的生产ID,每秒生产ID个数为400w+ */
            idWorker.nextId();
            /*  测试方法二: 在log中打印,同时获取ID,此用法生产ID的能力受限于log.error()的吞吐能力.
             * 每秒徘徊在10万左右. */
            //log.error("{}",idWorker.nextId());
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("耗时：" + end);
        System.out.println("生产数：" + count);
    }

    public static void testSequences() {
        Sequence sequence = new Sequence();
        sequence.nextId();
        long start = System.currentTimeMillis();
        int count = 0;
        for (; System.currentTimeMillis() - start < 1000; count++) {
            /*  测试方法一: 此用法纯粹的生产ID,每秒生产ID个数为400w+ */
            sequence.nextId();
            /*  测试方法二: 在log中打印,同时获取ID,此用法生产ID的能力受限于log.error()的吞吐能力.
             * 每秒徘徊在10万左右. */
            //log.error("{}",sequence.nextId());
        }
        long end = System.currentTimeMillis() - start;
        System.out.println("耗时：" + end);
        System.out.println("生产数：" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        /* case1: 测试每秒生产id个数?
         *   结论: 每秒生产id个数400w+ */
        System.out.println("=========== 单线程 System.currentTimeMillis() =================");
        testPerSecondProductIdNums();
        System.out.println("=========== 单线程 SystemClock =================");
        testSequences();

        for (int n = 1;n < 502;n += 20){
            testNThread(n);
        }

    }

    private static void testNThread(int n) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(n);
        System.out.println("++++++++++++++++ " + n + "个线程并发 ++++++++++++++++");

        System.out.println("############  System.currentTimeMillis() ########");
        SnowflakeIdFactory snowflakeIdFactory = new SnowflakeIdFactory(1, 1);
        snowflakeIdFactory.nextId();
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                snowflakeIdFactory.nextId();
                latch.countDown();
            }).start();
        }
        latch.await();
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));
        System.out.println("生产数：" + n);

        System.out.println("######### SystemClock ##########");
        CountDownLatch latchSequence = new CountDownLatch(n);
        Sequence sequence = new Sequence();
        sequence.nextId();
        long startSequence = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                sequence.nextId();
                latchSequence.countDown();
            }).start();
        }
        latchSequence.await();
        long endSequence = System.currentTimeMillis();
        System.out.println("耗时：" + (endSequence - startSequence));
        System.out.println("生产数：" + n);
    }
}
