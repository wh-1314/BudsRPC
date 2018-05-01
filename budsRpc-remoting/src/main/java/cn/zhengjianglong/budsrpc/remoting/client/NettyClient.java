package cn.zhengjianglong.budsrpc.remoting.client;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.BudsRpcChannelInitializer;
import cn.zhengjianglong.budsrpc.remoting.request.Request;
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
    private ChannelHandler channelHandler;
    // 请求队列
    private BlockingQueue<Request> requestQueue = new LinkedBlockingQueue();

    public NettyClient(String host, int port, ChannelHandler channelHandler) {
        this.host = host;
        this.port = port;
        this.channelHandler = channelHandler;
    }

    public void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new BudsRpcChannelInitializer(channelHandler));
            channel = bootstrap.connect(host, port).sync().channel();
            while (true) {
                try {
                    Request request = requestQueue.take();
                    StringBuilder sb = new StringBuilder();
                    sb.append(request.getId()).append("##")
                            .append(request.getmData()).append("\r\n");

                    System.out.println("发送给服务端：" + request);
                    channel.writeAndFlush(sb.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            logger.error("[BudsRPC] netty init exception.", e);
        } finally {
            group.shutdownGracefully();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public void send(Request msg) {
        try {
            requestQueue.put(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // channel.writeAndFlush(msg);
    }

    @Override
    public void reconnect() {
        try {
            bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            logger.error("[BudsRPC] netty init exception.", e);
        }
    }

    public static void main(String[] args) {
        new NettyClient("localhost", 8500, new BudsRpcClientHandler());
    }
}
