package xyz.iyichen.udp.server.message.recv.impl;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import xyz.iyichen.udp.server.message.pack.DataPack;
import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.recv.MessageReceiver;
import xyz.iyichen.udp.server.message.recv.handler.MessageRecvHandler;
import xyz.iyichen.udp.server.message.send.Message;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Service
public class MessageReceiverImpl implements MessageReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiverImpl.class);

    private static final int THREAD_COUNT = 1;

    private ThreadPoolExecutor executorService = new ThreadPoolExecutor(THREAD_COUNT, THREAD_COUNT, 5, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10));

    private HandlerTask[] handlerTasks = new HandlerTask[THREAD_COUNT];

    @Resource
    private Map<String, MessageRecvHandler> handlerMap;

    @PostConstruct
    private void init() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            handlerTasks[i] = new HandlerTask();
            executorService.execute(handlerTasks[i]);
        }
    }

    @PreDestroy
    private void destroy() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            handlerTasks[i].stopTask();
        }
        executorService.shutdown();
    }


    @Override
    public void recv(ChannelHandlerContext ctx, DatagramPacket datagramPacket) {
        Packet packet = DataPack.unPack(datagramPacket);
        InetSocketAddress socketAddress = datagramPacket.sender();
        // 解包
        LOGGER.info("Recv data. data:`{}`. addr: `{}`.", packet.getType(), datagramPacket.sender());
        HandlerTask handlerTask = handlerTasks[Math.abs(socketAddress.hashCode() % THREAD_COUNT)];

        handlerTask.addMessage(new Message(socketAddress, packet));
    }

    private MessageRecvHandler getRecvHandler(int type) {
        return handlerMap.get("MessageRecvHandler_" + type);
    }


    private class HandlerTask implements Runnable {

        private volatile boolean stop = false;

        private LinkedBlockingQueue<Message> recvMessageQueue = new LinkedBlockingQueue<>();

        @Override
        public void run() {
            while (!stop) {
                try {
                    Message message = recvMessageQueue.take();
                    MessageRecvHandler messageRecvHandler = getRecvHandler(message.getPacket().getType());
                    if (messageRecvHandler == null) {
                        LOGGER.error("Can not find message handler. type: `{}`.", message.getPacket().getType());
                        return;
                    }
                    try {
                        messageRecvHandler.handle(message.getSocketAddress(), message.getPacket());
                    } catch (Exception e) {
                        LOGGER.info("Handle recv message error. message: `{}`.", message, e);
                    }
                } catch (InterruptedException e) {
                    LOGGER.info("Get recv message error.", e);
                }
            }
        }

        public void addMessage(Message message) {
            recvMessageQueue.add(message);
        }

        public void stopTask() {
            this.stop = true;
        }
    }
}
