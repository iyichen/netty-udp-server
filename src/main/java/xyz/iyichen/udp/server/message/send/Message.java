package xyz.iyichen.udp.server.message.send;

import xyz.iyichen.udp.server.message.pack.Packet;

import java.net.InetSocketAddress;

public class Message {

    private InetSocketAddress socketAddress;

    private Packet packet;

    public Message(InetSocketAddress socketAddress, Packet packet) {
        this.socketAddress = socketAddress;
        this.packet = packet;
    }

    public InetSocketAddress getSocketAddress() {
        return socketAddress;
    }

    public Packet getPacket() {
        return packet;
    }

    @Override
    public String toString() {
        return "Message{" +
                "socketAddress=" + socketAddress +
                ", packet=" + packet +
                '}';
    }
}
