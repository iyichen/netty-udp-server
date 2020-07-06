package xyz.iyichen.udp.server.message.recv.handler.impl;

import org.springframework.stereotype.Service;
import xyz.iyichen.udp.server.message.pack.Packet;
import pb.MessageType;

import java.net.InetSocketAddress;

@Service("MessageRecvHandler_" + MessageType.PONG_VALUE)
public class PongMessageHandler extends AbstractMessageRecvHandler {

    @Override
    public void handle0(InetSocketAddress socketAddress, Packet packet) {

    }
}
