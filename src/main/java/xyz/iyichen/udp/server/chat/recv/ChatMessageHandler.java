package xyz.iyichen.udp.server.chat.recv;

import org.springframework.stereotype.Service;
import pb.Broadcast;
import pb.Chat;
import pb.MessageType;
import xyz.iyichen.udp.server.chat.user.OnlineUserHolder;
import xyz.iyichen.udp.server.chat.user.User;
import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.recv.handler.impl.AbstractMessageRecvHandler;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@Service("MessageRecvHandler_" + MessageType.CHAT_VALUE)
public class ChatMessageHandler extends AbstractMessageRecvHandler {

    @Resource
    private OnlineUserHolder onlineUserHolder;

    @Override
    protected void handle0(InetSocketAddress socketAddress, Packet packet) throws Exception {
        User user = onlineUserHolder.getBySocketAddr(socketAddress);
        if (user != null) {
            Chat chat = Chat.parseFrom(packet.getData());
            String content = user.getName() + ": " + chat.getContent();

            Broadcast broadcast = Broadcast.newBuilder().setContent(content).build();
            Packet packet1 = new Packet();
            packet1.setType(MessageType.BROADCAST_VALUE);
            packet1.setData(broadcast.toByteArray());

            for (User user1 : onlineUserHolder.getAll()) {
                messageSender.sendMessage(user1.getInetSocketAddress(), packet1);
            }
        }
    }
}
