import entity.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

public class ClientHandler {
    private String name;
    private ServerApp serverApp;
    private SocketChannel socketChannel;

    public ClientHandler(ServerApp server, SocketChannel socketChannel) {
        this.serverApp = server;
        this.socketChannel = socketChannel;
    }

    public String getName() {
        return name;
    }

    public void type_of_message(String msg, ChannelHandlerContext cht) {
        String[] credentialValues = msg.split("\\s");
        if (msg.startsWith("-auth")) {
            serverApp.getAuthenticationService().doAuth(credentialValues[1], credentialValues[2]).ifPresentOrElse(
                    user -> {
                        if (!serverApp.isLoggedIn(user.getNickname())) {
                            name = user.getNickname();
                            serverApp.subscribe(this);
                            cht.writeAndFlush("Auth OK");
                        } else {
                            cht.writeAndFlush("Current user is already logged in.");
                        }
                    },
                    new Runnable() {
                        @Override
                        public void run() {
                            cht.writeAndFlush("No a such user by email and password.");
                        }
                    });
        }
        if (msg.startsWith("-reg")) {
            if(serverApp.getAuthenticationService().doReg(credentialValues[1], credentialValues[2])){
                cht.writeAndFlush("Such user is already registered");
            }else{
                serverApp.getAuthenticationService().addUser(new User(credentialValues[1], credentialValues[2]));
                cht.writeAndFlush("Reg OK");
            }
        }
    }
}
