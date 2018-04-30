package cn.zhengjianglong.budsrpc.registry.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 20:31
 */
public class BudsRpcServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel socketChannel) throws Exception {
        System.out.println("receive data");
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        // 客户端的逻辑
        pipeline.addLast("handler", new RegistryCenterHandler());
    }
}
