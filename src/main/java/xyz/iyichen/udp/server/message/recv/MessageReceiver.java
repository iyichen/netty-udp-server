package xyz.iyichen.udp.server.message.recv;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;

public interface MessageReceiver {

    void recv(ChannelHandlerContext ctx, DatagramPacket datagramPacket) throws Exception;
}
