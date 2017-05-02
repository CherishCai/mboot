package cn.cherish.mboot.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Cherish
 * @version 1.0
 * @date 2017/5/2 22:03
 */
public class SocketClientTest {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("localhost", 8080);
            outputStream = socket.getOutputStream();
            outputStream.write(new byte[]{88});
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {

                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
