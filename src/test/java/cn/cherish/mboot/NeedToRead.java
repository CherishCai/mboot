package cn.cherish.mboot;

import sun.misc.Unsafe;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 这些类的源码必须看一下
 * Created by Cherish on 2017/3/10.
 */
public class NeedToRead {
    public static void main(String[] args) {

        String string = "原来整型字面量编译后是这样的，" +
                "调用了Integer.valueOf(i)，可以取缓存";
        Boolean bool = true;
        Byte b = 1;
        Character character = 'c';
        Short shortI = 64;
        Integer integer = 127;
        Long l = 127L;

        Float f = 0.0F;
        Double d = 0.0;

        Class clazz = Class.class;

        StringBuilder stringBuilder = new StringBuilder(16);
        StringBuffer stringBuffer = new StringBuffer(16);// toStringCache 有一个toString的缓存

        Thread thread = new Thread();
        ThreadLocal threadLocal = new ThreadLocal();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        //TODO 每个 TreadLocal 都只是一个键，它对应着 .set(value);
        //TODO 用它本身作为键去  ThreadLocalMap 中获取 value
        threadLocal.set(random);
        Object o = threadLocal.get();
        System.out.println("o = " + o.getClass());

        ThreadLocal threadLocal2 = new ThreadLocal();
        threadLocal2.set(threadLocal);
        Object o1 = threadLocal2.get();
        System.out.println("o1.getClass() = " + o1.getClass());

        //TODO 数据源
        //TODO java.util 及 concurrent 包，认真阅读源码
        TreeMap rbTree = new TreeMap();
        HashMap hashMap = new HashMap(8);
        Iterator iterator = hashMap.entrySet().iterator();

        ArrayList arrayList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        AbstractQueue priorityQueue = new PriorityQueue();

        BlockingQueue lbQueue = new LinkedBlockingQueue();

        ConcurrentMap concurrentMap = new ConcurrentHashMap();
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        AtomicInteger atomicInteger = new AtomicInteger();
        LongAdder longAdder = new LongAdder();

        ReentrantLock lock = new ReentrantLock();
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ForkJoinPool forkJoin = new ForkJoinPool();
        ExecutorService threadPoolExecutor =
                new ThreadPoolExecutor(5, 10, 5,
                        TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        FutureTask futureTask = new FutureTask<>(() -> new Random().nextInt(100));
        final String returnObj = "";
        futureTask = new FutureTask<>(() -> {

        }, returnObj);

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "Hello CompletableFuture!";
        });
        CompletableFuture future = completableFuture.thenAccept(o2 -> {
            System.out.println("thenAccept: " + o2);
        });


        FileInputStream fileInputStream;// 策略模式，装饰者模式

        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            final Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
            System.out.println(UNSAFE);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
