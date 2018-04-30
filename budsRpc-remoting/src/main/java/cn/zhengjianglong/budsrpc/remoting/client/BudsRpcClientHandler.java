package cn.zhengjianglong.budsrpc.remoting.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:52
 */
public class BudsRpcClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(s);
    }


}
