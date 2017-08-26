package cn.cherish.mboot.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/8/15 17:05
 */
public class ThreadTest {
    private static final ThreadLocal<Long> THREAD_LOCAL =
            ThreadLocal.withInitial(System::currentTimeMillis);

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable e) -> {
            System.err.println("t = " + t);
            e.printStackTrace(System.err);
        });

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () ->
                System.out.println("cyclicBarrier barrierAction "));

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "  aLong = " + THREAD_LOCAL.get());
            }).start();

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
