package test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Description: todo<br>
 * @CreateDate: Created in 2021/11/11 12:46 <br>
 * @Author: zlw
 * @email: zlw980725@gmail.com
 */

public class ServerClient {


    public static void main(String[] args) throws IOException {

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8888));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();


        SelectionKey register = ssc.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            selector.select();


            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {

                //获取所有的key
                SelectionKey key = iterator.next();

                // 当前key 为可链接状态
                if (key.isAcceptable()) {

                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    System.out.println("开始连接 ");

                    SocketChannel accept = channel.accept();

                    System.out.println("连接之后");

                    // 从当前的集合中删除
                    iterator.remove();
                }


            }

        }

    }
}
