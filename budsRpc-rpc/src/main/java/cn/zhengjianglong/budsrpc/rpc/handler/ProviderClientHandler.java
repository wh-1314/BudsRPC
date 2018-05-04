package cn.zhengjianglong.budsrpc.rpc.handler;

import cn.zhengjianglong.budsrpc.remoting.response.DefaultFuture;
import cn.zhengjianglong.budsrpc.remoting.response.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 10:39
 */
public class ProviderClientHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response response = (Response) msg;
        System.out.println("收到服务端信息：" + response);
        Channel channel = ctx.channel();

        // 通知调用线程
        DefaultFuture.received(channel, response);
    }
}