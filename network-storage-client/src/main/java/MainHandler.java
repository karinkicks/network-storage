import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MainHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("Получено новое сообщение: "+s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
