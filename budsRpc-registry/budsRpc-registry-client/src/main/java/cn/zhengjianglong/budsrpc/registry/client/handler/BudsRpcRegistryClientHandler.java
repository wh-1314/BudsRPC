package cn.zhengjianglong.budsrpc.registry.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 20:58
 */
public class BudsRpcRegistryClientHandler extends SimpleChannelInboundHandler<String> {



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {


        System.out.println("Server say : " + msg);
    }
}
