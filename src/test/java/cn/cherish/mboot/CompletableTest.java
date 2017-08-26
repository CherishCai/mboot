package cn.cherish.mboot;

import java.util.concurrent.CompletableFuture;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/8/22 18:06
 */
public class CompletableTest {
    public static void main(String[] args) throws InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(800L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello CompletableFuture!";
        }).exceptionally(Throwable::getMessage);

        CompletableFuture future = completableFuture.thenAccept(o2 -> {
            System.out.println("thenAccept: " + o2);
        });

        System.out.println("future has done? " + future.isDone());
        System.out.println("wait future " + Thread.currentThread().getName());


        Thread.sleep(1000L);
        System.out.println("future has done? " + future.isDone());
    }
}
