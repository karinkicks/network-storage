import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.stream.ChunkedFile;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MainHandler extends SimpleChannelInboundHandler<String> {
    final private ServerApp serverApp;
    private ClientHandler client;

    public MainHandler(ServerApp serverApp, ClientHandler client){
        this.serverApp=serverApp;
        this.client=client;
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("Зарегайтесь или авторизуйтесь");
        System.out.println("Клиент подключился: " + ctx);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            ByteBuf buff = (ByteBuf)msg;
        System.out.println("1");
            ByteBuffer nioBuffer = buff.nioBuffer();
        System.out.println("2");
            FileOutputStream fos = new FileOutputStream("E:\\test\\2.txt");
        System.out.println("3");
            FileChannel channel = fos.getChannel();
        System.out.println("4");
            while (nioBuffer.hasRemaining()) {
                channel.write(nioBuffer);
            }
            channel.close();
            fos.close();

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("Получено новое сообщение: "+s);
        client.type_of_message(s,channelHandlerContext);

    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        serverApp.unsubscribe(client);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
