package cn.cherish.mboot.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/5/2 21:19
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    private static final int MAX_WORKER_NUMBER = 20;

    private static final int DEFAULT_WORKER_NUMBER = 5;

    private static final int MIN_WORKER_NUMBER = 1;

    private final LinkedList<Job> jobs = new LinkedList<>();

    private final List<Worker> workers = new CopyOnWriteArrayList<>();

    private int workNum = DEFAULT_WORKER_NUMBER;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(workNum);
    }

    public DefaultThreadPool(int num) {
        workNum = num > MAX_WORKER_NUMBER ? MAX_WORKER_NUMBER :
                (num < MIN_WORKER_NUMBER ? MIN_WORKER_NUMBER : num);
        initializeWorkers(workNum);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorker(int num) {
        synchronized (jobs) {
            if (num + this.workNum > MAX_WORKER_NUMBER) {
                num = MAX_WORKER_NUMBER - this.workNum;
            }
            initializeWorkers(num);
            this.workNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workNum) {
                throw new IllegalArgumentException("beyond workNum");
            }

            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }

    class Worker implements Runnable {

        private volatile boolean running = true;

        public void shutdown() {
            running = false;
        }

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    job = jobs.removeFirst();
                }

                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        // TODO 忽略错误信息
                    }
                }
            }
        }

    }
}
