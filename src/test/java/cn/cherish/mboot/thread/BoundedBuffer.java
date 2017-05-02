package cn.cherish.mboot.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Cherish on 2017/3/14.
 */
public class BoundedBuffer {
    public BoundedBuffer(){
//        throw new NullPointerException();
    }
    private final ReentrantLock putLock = new ReentrantLock();
    final Condition notFull = putLock.newCondition();

    private final ReentrantLock takeLock = new ReentrantLock();
    final Condition notEmpty = takeLock.newCondition();

    final Object[] items = new Object[10];
    volatile int putptr, takeptr, count;

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public void put(Object x) throws InterruptedException {
        putLock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            System.out.println("生产了一个，当前可消费的产品有：" + count);
            if (count + 1 < items.length)
                notFull.signal();

        } finally {
            putLock.unlock();
        }
        if (count == 0)
            signalNotEmpty();
    }

    public Object take() throws InterruptedException {
        takeLock.lock();
        Object x;
        try {
            while (count == 0)
                notEmpty.await();
            x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            System.out.println("消费了一个，当前可消费的产品有：" + count);
            if (count > 0) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (count < items.length)
            signalNotFull();
        return x;
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
