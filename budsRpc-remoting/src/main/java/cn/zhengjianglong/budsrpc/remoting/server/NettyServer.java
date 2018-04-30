package cn.zhengjianglong.budsrpc.remoting.server;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.BudsRpcChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:26
 */
public class NettyServer implements Server {

    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private ServerBootstrap bootstrap;
    private InetSocketAddress bindAddress;

    // 对应的处理器
    private ChannelHandler channelHandler;
    private String bindIp;
    private int bindPort;

    public NettyServer(String bindIp, int bindPort, ChannelHandler channelHandler) {
        bindAddress = new InetSocketAddress(bindIp, bindPort);
        this.channelHandler = channelHandler;
        this.bindIp = bindIp;
        this.bindPort = bindPort;
        // bind();
    }

    public void bind() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new BudsRpcChannelInitializer(channelHandler))  //(4)
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(bindPort).sync(); // (7)
            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            logger.error("[]", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("SimpleChatServer 关闭了");
        }
    }

    @Override
    public Channel getChannel(InetSocketAddress remoteAddress) {
        return null;
    }
}
