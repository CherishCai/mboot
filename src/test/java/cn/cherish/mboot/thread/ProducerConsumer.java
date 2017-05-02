package cn.cherish.mboot.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * java多线程模拟生产者消费者问题
 * ProducerConsumer是主类，Producer生产者，Consumer消费者，Product产品，Storage仓库
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        Storage s = pc.new Storage();

        ExecutorService service = Executors.newCachedThreadPool();
        Producer p = pc.new Producer("生产者1", s);
        Producer p2 = pc.new Producer("生产者2", s);
        Consumer c = pc.new Consumer("消费者1", s);
        Consumer c2 = pc.new Consumer("消费者2", s);
        Consumer c3 = pc.new Consumer("消费者3", s);
        service.submit(p);
        service.submit(p2);
        service.submit(c);
        service.submit(c2);
        service.submit(c3);

    }

    /**
     * 消费者
     */
    class Consumer implements Runnable {
        private String name;
        private Storage s = null;

        public Consumer(String name, Storage s) {
            this.name = name;
            this.s = s;
        }

        public void run() {
            try {
                while (true) {
                    Product product = s.pop();
                    System.out.println(name + "已消费(" + product.toString() + ").");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 生产者
     */
    class Producer implements Runnable {
        private String name;
        private Storage s = null;

        public Producer(String name, Storage s) {
            this.name = name;
            this.s = s;
        }

        public void run() {
            try {
                while (true) {
                    Product product = new Product((int) (Math.random() * 10000)); // 产生0~9999随机整数
                    s.push(product);
                    System.out.println(name + "已生产(" + product.toString() + ").");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    /**
     * 仓库，用来存放产品
     */
    public class Storage {
        BlockingQueue<Product> queues = new LinkedBlockingQueue<>(10);

        /**
         * 生产
         * @param p 产品
         * @throws InterruptedException
         */
        public void push(Product p) throws InterruptedException {
            queues.put(p);
        }

        /**
         * 消费
         * @return 产品
         * @throws InterruptedException
         */
        public Product pop() throws InterruptedException {
            return queues.take();
        }
    }

    /**
     * 产品
     */
    public class Product {
        private int id;

        public Product(int id) {
            this.id = id;
        }

        public String toString() {// 重写toString方法
            return "产品：" + this.id;
        }
    }

}
