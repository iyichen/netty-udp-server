package xyz.iyichen.udp.server.message.recv.handler;

import xyz.iyichen.udp.server.message.pack.Packet;

import java.net.InetSocketAddress;

/**
 * 接收客户端消息处理类
 */
public interface MessageRecvHandler {

    /**
     * 处理消息
     *
     * @param socketAddress 发送方地址
     * @param packet        数据包
     * @throws Exception 异常信息，如字节流转对象异常
     */
    void handle(InetSocketAddress socketAddress, Packet packet) throws Exception;
}
