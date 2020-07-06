package xyz.iyichen.udp.server.starter.channel;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.iyichen.udp.server.message.recv.MessageReceiver;
import xyz.iyichen.udp.server.message.send.service.MessageSender;

import javax.annotation.Resource;

@ChannelHandler.Sharable
@Component
public class SocketChannelHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private final static Logger LOGGER = LoggerFactory.getLogger(SocketChannelHandler.class);

    @Resource
    private MessageSender messageSender;

    @Resource
    private MessageReceiver messageReceiver;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket datagramPacket) {
        try {
            messageReceiver.recv(ctx, datagramPacket);
        } catch (Exception e) {
            LOGGER.error("Handler data error. data:`{}`.", datagramPacket, e);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        LOGGER.info("Channel active. init messageSender...");
        messageSender.init(ctx);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("Exception!", cause);
    }

}
