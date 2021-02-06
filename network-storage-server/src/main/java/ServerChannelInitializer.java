import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private ServerApp serverApp;
    public ServerChannelInitializer(ServerApp serverApp){
        this.serverApp = serverApp;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new StringDecoder(), new StringEncoder(), new MainHandler(serverApp));

    }
}
