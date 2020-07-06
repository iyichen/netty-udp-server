package xyz.iyichen.udp.server.starter.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.iyichen.udp.server.message.builder.MessageBuilder;
import xyz.iyichen.udp.server.message.pack.Packet;
import xyz.iyichen.udp.server.message.send.service.MessageSender;
import xyz.iyichen.udp.server.starter.heartbeat.service.HeartBeatService;
import pb.MessageType;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class HeartBeatHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartBeatHandler.class);

    private static final Map<InetSocketAddress, HeartBeatInfo> LAST_ACTIVE_INFO = new ConcurrentHashMap<>();

    private static final int MAX_PING_TIMES = 5;

    private static final long NO_REPLY_TIME = 5000L;

    @Resource
    private MessageSender messageSender;

    @Resource
    private HeartBeatService heartBeatService;

    @Scheduled(fixedDelay = 1000)
    public void handle() {
        for (Map.Entry<InetSocketAddress, HeartBeatInfo> entry : LAST_ACTIVE_INFO.entrySet()) {
            InetSocketAddress socketAddress = entry.getKey();

            int pingCount = entry.getValue().pingCount;
            if (pingCount > MAX_PING_TIMES) {

                LAST_ACTIVE_INFO.remove(socketAddress);

                heartBeatService.noReply(socketAddress);

                LOGGER.info("Ping times more than 5. socketAddr: `{}`.", socketAddress);
                continue;
            }

            long lastActiveTime = entry.getValue().lastActiveTime;
            if (System.currentTimeMillis() - lastActiveTime > NO_REPLY_TIME * (pingCount + 1)) {
                Packet packet = new Packet().setType(MessageType.PING_VALUE).setData(MessageBuilder.ping().toByteArray());
                messageSender.sendMessage(socketAddress, packet);

                LOGGER.info("Ping. count: `{}`. socketAddr: `{}`. lastActiveTime: `{}`.", pingCount, socketAddress, lastActiveTime);
                entry.getValue().incrPingCount();
            }
        }
    }

    public void updateActiveTime(InetSocketAddress socketAddress) {
        HeartBeatInfo heartBeatInfo = LAST_ACTIVE_INFO.computeIfAbsent(socketAddress, e -> new HeartBeatInfo());
        heartBeatInfo.updateActiveTime();
    }

    private static class HeartBeatInfo {
        private long lastActiveTime = 0L;
        private int pingCount = 0;

        private void incrPingCount() {
            this.pingCount++;
        }

        private void updateActiveTime() {
            long time = System.currentTimeMillis();
            this.pingCount = 0;
            this.lastActiveTime = time;
        }
    }

}