import auth.BasicAuthService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainHandler extends SimpleChannelInboundHandler<String> {
    final private ServerApp serverApp;

public MainHandler(ServerApp serverApp){
    this.serverApp=serverApp;
}
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("Зарегайтесь или авторизуйтесь");
        System.out.println("Клиент подключился: " + ctx);

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
