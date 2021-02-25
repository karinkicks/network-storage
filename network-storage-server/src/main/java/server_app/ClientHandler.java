package server_app;

import entity.User;
import io.netty.channel.Channel;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler {

    private final Map<Channel, User> clients = new HashMap<>();

    public void subscribe(Channel ch, User user) {
        clients.put(ch,user);
    }

    public User unsubscribe(Channel ch) {
        if(clients.containsKey(ch)){
            User user = clients.get(ch);
            clients.remove(ch);
            return user;
        }
        return null;
    }

    public String getName(Channel ch){
        return clients.get(ch).getNickname();
    }

    public User getUser(Channel ch){
        return clients.get(ch);
    }

    public boolean isLoggedIn(User user) {
        return clients.containsValue(user);
    }
    public boolean isLoggedIn(Channel ch) {
        return clients.containsKey(ch);
    }
}
