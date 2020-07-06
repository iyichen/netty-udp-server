package xyz.iyichen.udp.server.message.builder;

import pb.*;

/**
 * 公共消息构建类
 */
public class MessageBuilder {

    /**
     * 创建 Ping 消息
     *
     * @return Ping 消息
     */
    public static Ping ping() {
        return Ping.newBuilder().build();
    }

    /**
     * 创建 Pong 消息
     *
     * @return Pong 消息
     */
    public static Pong pong() {
        return Pong.newBuilder().build();
    }

}
