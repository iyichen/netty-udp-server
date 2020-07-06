package xyz.iyichen.udp.server.message.recv.handler.impl;

import org.springframework.stereotype.Service;
import xyz.iyichen.udp.server.message.builder.MessageBuilder;
import xyz.iyichen.udp.server.message.pack.Packet;
import pb.MessageType;

import java.net.InetSocketAddress;

@Service("MessageRecvHandler_" + MessageType.PING_VALUE)
public class PingMessageHandler extends AbstractMessageRecvHandler {

    @Override
    public void handle0(InetSocketAddress socketAddress, Packet packet) {
        messageSender.sendMessage(socketAddress,
                new Packet().setType(MessageType.PONG_VALUE).setData(MessageBuilder.pong().toByteArray()));
    }
}
