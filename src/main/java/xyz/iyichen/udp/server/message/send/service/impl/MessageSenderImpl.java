package xyz.iyichen.udp.server.message.send.service.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.iyichen.udp.server.message.pack.DataPack;
import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.send.service.MessageSender;

import java.net.InetSocketAddress;

@Component
public class MessageSenderImpl implements MessageSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSenderImpl.class);

    private static ChannelHandlerContext channelHandlerContext = null;

    public synchronized void init(ChannelHandlerContext ctx) {
        if (channelHandlerContext != null) {
            return;
        }
        channelHandlerContext = ctx;
    }

    @Override
    public void sendMessage(InetSocketAddress socketAddress, Packet packet) {
        if (socketAddress != null) {
            channelHandlerContext.writeAndFlush(new DatagramPacket(DataPack.pack(packet), socketAddress));
            LOGGER.warn("Message send success. target: `{}`. type: `{}`.", socketAddress, packet.getType());
        }
    }

}
