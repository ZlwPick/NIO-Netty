package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @Description: todo<br>
 * @CreateDate: Created in 2021/11/11 12:50 <br>
 * @Author: zlw
 * @email: zlw980725@gmail.com
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        SocketChannel open = SocketChannel.open();


        open.connect(new InetSocketAddress("localhost", 8888));
        System.out.println("waiting...");

    }
}
