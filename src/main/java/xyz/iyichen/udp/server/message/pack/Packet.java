package xyz.iyichen.udp.server.message.pack;

import java.util.Arrays;

/**
 * 数据包结构(TLV)
 */
public class Packet {

    /**
     * 消息类型
     */
    private int type;

    /**
     * 消息数据
     */
    private byte[] data;

    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    public int getType() {
        return type;
    }

    /**
     * 设置消息类型
     *
     * @param type 消息类型
     * @return 消息数据
     */
    public Packet setType(int type) {
        this.type = type;
        return this;
    }

    /**
     * 获取消息数据
     *
     * @return 消息数据
     */
    public byte[] getData() {
        return data;
    }

    /**
     * 获取消息消息数据
     *
     * @param data 消息数据
     * @return 消息数据
     */
    public Packet setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Packet{" +
                ", type=" + type +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
