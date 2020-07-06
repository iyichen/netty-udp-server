package xyz.iyichen.udp.server.starter.util;


import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketAddrHolder {

    private static final Map<String, InetSocketAddress> SOCKET_ADDR_MAP = new ConcurrentHashMap<>();

    public static void put(String connId, InetSocketAddress socketAddress) {
        SOCKET_ADDR_MAP.put(connId, socketAddress);
    }

    public static InetSocketAddress get(String connId) {
        return SOCKET_ADDR_MAP.get(connId);
    }

    public static void remove(String connId) {
        SOCKET_ADDR_MAP.remove(connId);
    }

    public static void remove(InetSocketAddress socketAddress) {
        for (String key : SOCKET_ADDR_MAP.keySet()) {
            if (SOCKET_ADDR_MAP.get(key).equals(socketAddress)) {
                SOCKET_ADDR_MAP.remove(key);
                break;
            }
        }
    }

}