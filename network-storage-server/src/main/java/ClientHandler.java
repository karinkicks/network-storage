import auth.BasicAuthService;
import io.netty.channel.Channel;
import io.netty.channel.unix.Socket;

public class ClientHandler {
    private Socket socket;
 private ServerApp serverApp;
    public ClientHandler(ServerApp server, Socket socket) {
        this.socket = socket;
        this.serverApp = server;
    }

    /*
    public void type_of_message(String msg) {
        String[] credentialValues = msg.split("\\s");
        if (msg.startsWith("-auth")) {
            BasicAuthService bas = new BasicAuthService();
            bas.doAuth(credentialValues[1], credentialValues[2]).ifPresentOrElse(
                    user -> {


                    },
                    new Runnable() {
                        @Override
                        public void run() {
                            channel.writeAndFlush("No a such user by email and password.");
                        }
                    });
        }
    }*/
}
