package xyz.iyichen.udp.server.message.pack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.DatagramPacket;

import java.nio.ByteBuffer;

/**
 * 数据包工具类，提供封包/拆包，字节序为小端序
 */
public class DataPack {

    public static ByteBuf pack(Packet packet) {
        return Unpooled.copiedBuffer(intToBytes(packet.getType()), packet.getData());
    }


    public static Packet unPack(DatagramPacket datagramPacket) {
        ByteBuffer byteBuffer = datagramPacket.content().nioBuffer();

        byte[] typeBytes = new byte[4];
        byteBuffer.get(typeBytes);

        byte[] data = new byte[byteBuffer.limit() - byteBuffer.position()];
        byteBuffer.get(data);
        return new Packet().setType(bytesToInt(typeBytes)).setData(data);
    }


    /*********** 数字转字节数组(小端序) *************/

    private static byte[] intToBytes(int i) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) ((i >> 24) & 0xFF);
        bytes[2] = (byte) ((i >> 16) & 0xFF);
        bytes[1] = (byte) ((i >> 8) & 0xFF);
        bytes[0] = (byte) (i & 0xFF);
        return bytes;
    }

    private static int bytesToInt(byte[] bytes) {
        return (bytes[3] << 24 & 0xFF00) | (bytes[2] << 16 & 0xFF00) | (bytes[1] << 8 & 0xFF00) | (bytes[0] & 0xFF);
    }

}