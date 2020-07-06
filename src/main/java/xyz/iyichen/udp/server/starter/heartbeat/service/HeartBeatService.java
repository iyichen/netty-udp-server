package xyz.iyichen.udp.server.starter.heartbeat.service;

import java.net.InetSocketAddress;

public interface HeartBeatService {

    default void noReply(InetSocketAddress inetSocketAddress) {

    }

}
