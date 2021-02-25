package server_app;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final ServerApp serverApp;
    public ClientHandler clientHandler;
    public ServerChannelInitializer(ServerApp serverApp, ClientHandler clientHandler){
        this.serverApp = serverApp;
        this.clientHandler=clientHandler;
    }
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        socketChannel.pipeline().addLast(
                new MainHandler(serverApp, clientHandler));
    }
}
