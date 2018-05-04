package cn.zhengjianglong.budsrpc.rpc.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.request.Request;
import cn.zhengjianglong.budsrpc.remoting.response.Response;
import cn.zhengjianglong.budsrpc.rpc.Invocation;
import cn.zhengjianglong.budsrpc.rpc.Invoker;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 10:14
 */
@ChannelHandler.Sharable
public class ProviderServerHandler extends SimpleChannelInboundHandler<Request> {
    private static final Logger logger = LoggerFactory.getLogger(ProviderServerHandler.class);
    private Map<String, Invoker> invokerMap = new ConcurrentHashMap<>();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
        Channel incoming = ctx.channel();
        logger.error("client offline, {}", incoming.remoteAddress());
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Request request) throws Exception {
        Response response = new Response(request.getId());
        try {
            Channel incoming = ctx.channel();
            System.out.println("服务收到的信息:" + request + "  地址：" + incoming.remoteAddress());

            Object data = request.getData();
            if (data instanceof Invocation) {
                try {
                    Invocation invocation = (Invocation) data;
                    System.out.println(invocation.getInterface().getName());
                    Invoker invoker = invokerMap.get(invocation.getInterface().getName());

                    if (null != invoker) {
                        Object object = invoker.invoke(invocation);
                        response.setResult(object);
                    } else {
                        response.setResult("Unfind");
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    response.setResult("EXCEPTION:call error");
                }
            } else {
                response.setResult("Unsupport.");
            }
        } catch (Exception e) {
            response.setResult("EXCEPTION##call error");
            e.printStackTrace();
        } finally {
            ctx.writeAndFlush(response);
        }
    }

    public void export(String type, Invoker invoker) {
        invokerMap.put(type, invoker);
    }
}
