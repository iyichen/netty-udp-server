package xyz.iyichen.udp.server.chat.recv;

import org.springframework.stereotype.Service;
import pb.Broadcast;
import pb.Login;
import pb.MessageType;
import xyz.iyichen.udp.server.chat.user.OnlineUserHolder;
import xyz.iyichen.udp.server.chat.user.User;
import xyz.iyichen.udp.server.message.pack.DataPack;
import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.recv.handler.impl.AbstractMessageRecvHandler;
import xyz.iyichen.udp.server.message.send.service.MessageSender;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.List;

@Service("MessageRecvHandler_" + MessageType.LOGIN_VALUE)
public class LoginMessageHandler extends AbstractMessageRecvHandler {

    @Resource
    private OnlineUserHolder onlineUserHolder;

    @Resource
    private MessageSender messageSender;

    @Override
    protected void handle0(InetSocketAddress socketAddress, Packet packet) throws Exception {
        Login login = Login.parseFrom(packet.getData());
        String name = login.getName();
        User user = new User(name, socketAddress);

        onlineUserHolder.online(user);

        Broadcast broadcast = Broadcast.newBuilder().setContent(user.getName() + " Enter Chat Room.").build();
        Packet packet1 = new Packet();
        packet1.setType(MessageType.BROADCAST_VALUE);
        packet1.setData(broadcast.toByteArray());

        for (User user1 : onlineUserHolder.getAll()) {
            messageSender.sendMessage(user1.getInetSocketAddress(), packet1);
        }
    }
}
