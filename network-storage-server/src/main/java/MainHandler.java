import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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
        super.channelRead(ctx, msg);
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
