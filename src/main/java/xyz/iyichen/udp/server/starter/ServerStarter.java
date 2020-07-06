package xyz.iyichen.udp.server.starter;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.iyichen.udp.server.starter.channel.SocketChannelHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
public class ServerStarter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerStarter.class);

    private EventLoopGroup workerGroup = null;

    @Value("${socket.port}")
    private int socketPort;

    @Resource
    private SocketChannelHandler socketChannelHandler;

    @PostConstruct
    public void start() throws Exception {
        workerGroup = Epoll.isAvailable() ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workerGroup)
                .channel(Epoll.isAvailable() ? EpollDatagramChannel.class : NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.SO_RCVBUF, 1024)// 设置UDP读缓冲区为1M
                .option(ChannelOption.SO_SNDBUF, 1024)// 设置UDP写缓冲区为1M
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel channel) {
                        channel.pipeline()
                                .addLast(socketChannelHandler);
                    }
                });

        if (Epoll.isAvailable()) {
            bootstrap.option(EpollChannelOption.SO_REUSEPORT, true);
            for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
                ChannelFuture future = bootstrap.bind(socketPort).sync();
                if (!future.isSuccess()) {
                    throw new Exception("Bootstrap bind port failed. port:" + socketPort);
                }
            }
        } else {
            ChannelFuture channelFuture = bootstrap.bind(socketPort).sync();
            if (channelFuture.isSuccess()) {
                LOGGER.info("Start server success.");
            }
        }


//        workerGroup = new NioEventLoopGroup();
//        Bootstrap bootstrap = new Bootstrap()
//                .group(workerGroup)
//                .channel(NioDatagramChannel.class)
//                .option(ChannelOption.SO_BROADCAST, true)
//                .option(ChannelOption.SO_BACKLOG, 128)
//                .option(ChannelOption.SO_RCVBUF, 1024)// 设置UDP读缓冲区为1M
//                .option(ChannelOption.SO_SNDBUF, 1024)// 设置UDP写缓冲区为1M
//                .handler(new ChannelInitializer<NioDatagramChannel>() {
//                    @Override
//                    protected void initChannel(NioDatagramChannel channel) {
//                        channel.pipeline()
//                                .addLast(socketChannelHandler);
//                    }
//                });
//
//
//        ChannelFuture channelFuture = bootstrap.bind(socketPort).sync();
//        if (channelFuture.isSuccess()) {
//            LOGGER.info("Start server success.");
//        }
    }

    @PreDestroy
    public void destroy() {
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
            LOGGER.info("Stop server success.");
        }
    }

}
