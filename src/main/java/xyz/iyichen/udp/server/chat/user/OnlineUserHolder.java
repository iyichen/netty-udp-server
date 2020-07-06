package xyz.iyichen.udp.server.chat.user;

import org.springframework.stereotype.Service;
import xyz.iyichen.udp.server.starter.heartbeat.service.HeartBeatService;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OnlineUserHolder implements HeartBeatService {

    private static final Map<Integer, User> USER_MAP = new ConcurrentHashMap<>();

    public void online(User user) {
        USER_MAP.put(user.getUid(), user);
    }

    public void offline(InetSocketAddress inetSocketAddress) {
        for (int id : USER_MAP.keySet()) {
            if (USER_MAP.get(id).getInetSocketAddress().equals(inetSocketAddress)) {
                USER_MAP.remove(id);
                return;
            }
        }
    }

    public User getBySocketAddr(InetSocketAddress inetSocketAddress) {
        for (User user : USER_MAP.values()) {
            if (user.getInetSocketAddress().equals(inetSocketAddress)) {
                return user;
            }
        }
        return null;
    }

    public Set<User> getAll() {
        return new HashSet<>(USER_MAP.values());
    }

    @Override
    public void noReply(InetSocketAddress inetSocketAddress) {
        offline(inetSocketAddress);
    }
}
