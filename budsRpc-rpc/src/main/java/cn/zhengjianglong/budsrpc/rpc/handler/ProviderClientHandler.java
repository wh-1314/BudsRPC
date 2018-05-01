package cn.zhengjianglong.budsrpc.rpc.handler;

import cn.zhengjianglong.budsrpc.remoting.response.DefaultFuture;
import cn.zhengjianglong.budsrpc.remoting.response.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 10:39
 */
public class ProviderClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到服务端信息：" + msg);

        Channel channel = ctx.channel();

        String[] resultArr = msg.split("##");
        long mId = Long.parseLong(resultArr[0]);
        String result = resultArr[1];
        Response response = new Response(mId, result);

        // 通知调用线程
        DefaultFuture.received(channel, response);
    }

}