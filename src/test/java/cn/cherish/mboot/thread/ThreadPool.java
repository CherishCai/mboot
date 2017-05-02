package cn.cherish.mboot.thread;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/5/2 21:16
 */
public interface ThreadPool<Job extends Runnable> {
    void execute(Job job);

    void shutdown();

    void addWorker(int num);

    void removeWorker(int num);

    int getJobSize();
}
