package nettytest;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author zlw
 * @ClassName EventLoop
 * @description
 * @createTime 2021/11/16 23:36
 */
public class EventLoop {

    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup(2);

        //
        System.out.println("eventExecutors.next()1 = " + eventExecutors.next());
        System.out.println("eventExecutors.next()2 = " + eventExecutors.next());


        // 普通任务
        eventExecutors.next().execute(() -> {
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        });


        // 定时任务
        eventExecutors.scheduleWithFixedDelay(() -> {
                    System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

                }
                , 0, 1, TimeUnit.SECONDS);


        eventExecutors.shutdownGracefully();
    }
}
