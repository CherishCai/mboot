package cn.cherish.mboot.socket;

import cn.cherish.mboot.thread.DefaultThreadPool;
import cn.cherish.mboot.thread.ThreadPool;

import java.io.Closeable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/5/2 21:48
 */
public class SimpleHttpServer {

    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(2);

    static String basePath = "/testServer";

    static ServerSocket serverSocket;

    static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            // TODO
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println("socketIp: " + socket.getInetAddress());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        //TODO
                    }
                }
            }
        }
    }
}

