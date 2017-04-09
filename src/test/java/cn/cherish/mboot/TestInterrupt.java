package cn.cherish.mboot;

/**
 * @author Cherish
 * @version Id: TestInterrupt.java, v 1 2017/3/24 10:19 Cherish Exp $$
 */
public class TestInterrupt {

    public static void main(String[] args) {
        Thread sleepThread = new Thread(() -> {
            try {
                System.out.println("【sleepThread】沉睡的线程" + Thread.currentThread().getName());
                Thread.sleep(1000_00);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("【sleepThread】沉睡中收到中断信号，会抛出InterruptedException");
            }finally {
                System.out.println("【sleepThread】finally方法可以做些啥");
            }
            while(true){
                System.out.println("【sleepThread】感谢中断信号，我不睡了");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread loopThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("【loopThread】 没收到中断信号，随便玩");
            }

            int count = 0;
            while (Thread.currentThread().isInterrupted()) {
                ++count;
                System.out.println("【loopThread】 收到中断信号，改变操作方式，我可以选择释放资源，但是我不 " + count);

                if (count == 10) {
                    boolean beforeState = Thread.interrupted();
                    System.out.println("【loopThread】 beforeState = " + beforeState);
                    System.out.println("Thread.interrupted() 会重置中断标记为false");
                    boolean nowState = Thread.currentThread().isInterrupted();
                    System.out.println("【loopThread】 nowState = " + nowState);
                    System.out.println("【loopThread】任性了10次，该结束了");
                }
            }

            System.out.println("【loopThread】正式结束");
        });

        sleepThread.start();
        loopThread.start();

        try {
            Thread.sleep(1);//等待其它两个线程的执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sleepThread.interrupt();
        loopThread.interrupt();

        while (true) {
            try {
                Thread.sleep(1000);//等待其它两个线程收到中断信号
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【sleepThread】 isInterrupted = " + sleepThread.isInterrupted());
            System.out.println("【loopThread】 isInterrupted = " + loopThread.isInterrupted());
        }
    }


}
