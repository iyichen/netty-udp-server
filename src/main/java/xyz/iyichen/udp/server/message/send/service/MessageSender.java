package xyz.iyichen.udp.server.message.send.service;


import io.netty.channel.ChannelHandlerContext;
import xyz.iyichen.udp.server.message.pack.Packet;

import java.net.InetSocketAddress;

public interface MessageSender {

    void init(ChannelHandlerContext ctx);

    void sendMessage(InetSocketAddress socketAddress, Packet packet);

}
