package cn.cherish.mboot;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Cherish on 2017/3/14.
 */
public class BoundedBuffer {
    private final ReentrantLock lock = new ReentrantLock();
    final Condition notEmpty = lock.newCondition();
    final Condition notFull = lock.newCondition();

    final Object[] items = new Object[100];
    volatile int putptr, takeptr, count;

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.lock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            System.out.println("生产了一个，当前可消费的产品有：" + count);
            if (count + 1 < items.length)
                notFull.signal();

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
        if (count == 0)
            signalNotEmpty();
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            System.out.println("消费了一个，当前可消费的产品有：" + count);
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public class PutThread extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    put(66);
                    Thread.sleep((long)(Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class GetThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    take();
                    Thread.sleep((long)(Math.random()*500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        final BoundedBuffer test = new BoundedBuffer();
        test.new PutThread().start();
        test.new PutThread().start();
        test.new PutThread().start();
        test.new GetThread().start();
        test.new GetThread().start();
        test.new GetThread().start();

       /* while(!get1.isInterrupted()){
            get1.interrupt();

        }*/
    }
}
