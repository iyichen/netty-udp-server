package xyz.iyichen.udp.server.message.recv.handler.impl;

import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.recv.handler.MessageRecvHandler;
import xyz.iyichen.udp.server.message.send.service.MessageSender;
import xyz.iyichen.udp.server.starter.heartbeat.HeartBeatHandler;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

public abstract class AbstractMessageRecvHandler implements MessageRecvHandler {

    @Resource
    private HeartBeatHandler heartBeatHandler;

    @Resource
    protected MessageSender messageSender;

    @Override
    public void handle(InetSocketAddress socketAddress, Packet packet) throws Exception {
        // 更新最后活跃时间
        heartBeatHandler.updateActiveTime(socketAddress);

        handle0(socketAddress, packet);
    }

    protected abstract void handle0(InetSocketAddress socketAddress, Packet packet) throws Exception;
}
