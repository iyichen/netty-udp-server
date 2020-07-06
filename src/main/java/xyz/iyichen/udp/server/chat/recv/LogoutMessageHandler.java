package xyz.iyichen.udp.server.chat.recv;

import org.springframework.stereotype.Service;
import pb.Broadcast;
import pb.MessageType;
import xyz.iyichen.udp.server.chat.user.OnlineUserHolder;
import xyz.iyichen.udp.server.chat.user.User;
import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.recv.handler.impl.AbstractMessageRecvHandler;

import javax.annotation.Resource;
import java.net.InetSocketAddress;

@Service("MessageRecvHandler_" + MessageType.LOGOUT_VALUE)
public class LogoutMessageHandler extends AbstractMessageRecvHandler {

    @Resource
    private OnlineUserHolder onlineUserHolder;

    @Override
    protected void handle0(InetSocketAddress socketAddress, Packet packet) {

        User user = onlineUserHolder.getBySocketAddr(socketAddress);
        onlineUserHolder.offline(socketAddress);

        if (user != null) {
            Broadcast broadcast = Broadcast.newBuilder().setContent(user.getName() + " Leave Chat Room.").build();
            Packet packet1 = new Packet();
            packet1.setType(MessageType.BROADCAST_VALUE);
            packet1.setData(broadcast.toByteArray());

            for (User user1 : onlineUserHolder.getAll()) {
                messageSender.sendMessage(user1.getInetSocketAddress(), packet1);
            }
        }
    }
}
