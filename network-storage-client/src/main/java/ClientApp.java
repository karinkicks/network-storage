import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientApp {

    public static void main(String[] args) throws Exception {

        final EventLoopGroup[] workerGroup = {new NioEventLoopGroup()};

        try {

            Bootstrap b = new Bootstrap();                    // (1)
            b.group(workerGroup[0]);                             // (2)
            b.channel(NioSocketChannel.class);                // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {

                    ch.pipeline().addLast(new StringDecoder(), new StringEncoder(), new MainHandler());
                }
            });

            ///ChannelFuture f = b.connect("localhost", 8189).sync();   // (4)

            Channel channel = b.connect("localhost", 8189).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                try {
                      channel.writeAndFlush(in.readLine());
                } catch (IOException e) {
                       e.printStackTrace();
                }
            }

        } finally {
            workerGroup[0].shutdownGracefully();
        }
    }
}
