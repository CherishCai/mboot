package cn.cherish.mboot.socket;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/5/2 22:03
 */
public class SocketServerTest {
    public static void main(String[] args) {
        try {
            SimpleHttpServer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
