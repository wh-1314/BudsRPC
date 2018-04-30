package cn.zhengjianglong.budsrpc.remoting.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.BudsRpcChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:27
 */
public class NettyClient implements Client {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private Channel channel;
    private Bootstrap bootstrap;
    private String host;
    private int port;
    public ChannelHandler channelHandler;

    private NettyClient(String host, int port, ChannelHandler channelHandler) {
        this.host = host;
        this.port = port;
        this.channelHandler = channelHandler;

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new BudsRpcChannelInitializer(channelHandler));

            channel = bootstrap.connect(host, port).sync().channel();

        } catch (Exception e) {
            logger.error("[BudsRPC] netty init exception.", e);
        } finally {
            group.shutdownGracefully();
        }
    }

    @Override
    public void send(Object msg) {
        channel.writeAndFlush(msg);
    }

    @Override
    public void reconnect() {
        try {
            bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            logger.error("[BudsRPC] netty init exception.", e);
        }
    }
}
