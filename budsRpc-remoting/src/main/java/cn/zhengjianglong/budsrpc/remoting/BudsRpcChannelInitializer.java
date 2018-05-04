package cn.zhengjianglong.budsrpc.remoting;

import cn.zhengjianglong.budsrpc.remoting.handler.NettyCodecAdapter;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:38
 */
public class BudsRpcChannelInitializer extends ChannelInitializer<SocketChannel> {

    public ChannelHandler channelHandler;

    public BudsRpcChannelInitializer(ChannelHandler channelHandler) {
        this.channelHandler = channelHandler;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        NettyCodecAdapter adapter = new NettyCodecAdapter();

        pipeline.addLast("decoder", adapter.getDecoder());
        pipeline.addLast("encoder", adapter.getEncoder());
        pipeline.addLast("handler", channelHandler);
    }
}
