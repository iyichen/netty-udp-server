package xyz.iyichen.udp.server.chat.user;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class User {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private int uid;

    private String name;

    private InetSocketAddress inetSocketAddress;

    public User(String name, InetSocketAddress inetSocketAddress) {
        this.uid = atomicInteger.incrementAndGet();
        this.name = name;
        this.inetSocketAddress = inetSocketAddress;
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", inetSocketAddress=" + inetSocketAddress +
                '}';
    }
}
