package nettytest;

import com.sun.corba.se.internal.CosNaming.BootstrapServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.StandardCharsets;

/**
 * @Description: todo<br>
 * @CreateDate: Created in 2021/11/18 15:10 <br>
 * @Author: zlw
 * @email: zlw980725@gmail.com
 */
public class DefaultEventLoopGroupTest {

    public static void main(String[] args) {

        // 自定义的
        DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup();

        new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("nettyHandler", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + buf.toString().getBytes(StandardCharsets.UTF_8));
                                ctx.fireChannelRead(msg);
                            }
                        })
                                .addLast(defaultEventLoopGroup, "111", new ChannelInboundHandlerAdapter() {

                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        ByteBuf buf = (ByteBuf) msg;
                                        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName() + buf.toString().getBytes(StandardCharsets.UTF_8));
                                    }
                                });


                    }
                }).bind(8888);

    }
}
