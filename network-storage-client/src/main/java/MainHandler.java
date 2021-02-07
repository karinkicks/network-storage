import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //File file = new File("E:\\geekBrains\\Java\\5\\network-storage\\network-storage-server\\src\\main\\resources\\test.txt");
        byte[] b = Files.readAllBytes(Paths.get("E:\\geekBrains\\Java\\5\\network-storage\\network-storage-server\\src\\main\\resources\\test.txt"));
        ctx.writeAndFlush(Unpooled.wrappedBuffer(b));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("Получено новое сообщение: "+s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
