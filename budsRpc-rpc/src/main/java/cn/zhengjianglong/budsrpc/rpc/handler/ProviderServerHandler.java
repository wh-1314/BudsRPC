package cn.zhengjianglong.budsrpc.rpc.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.rpc.Invoker;
import cn.zhengjianglong.budsrpc.rpc.SimpleInvocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 10:14
 */
@ChannelHandler.Sharable
public class ProviderServerHandler extends SimpleChannelInboundHandler<String> {
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
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        try {
            Channel incoming = ctx.channel();
            System.out.println("服务收到的信息:" + msg + "  地址：" + incoming.remoteAddress());

            if (msg.equals("PING")) {
                ctx.writeAndFlush("SUCCESS\r\n");
                return;
            }
            String[] cmd = msg.split("##");
            String mId = cmd[0];
            String service = cmd[1];
            String method = cmd[2];
            String paramsTypes = cmd[3];
            String paramValues = cmd[4];

            logger.info("service=" + service);
            Invoker invoker = invokerMap.get(service);
            if (invoker == null) {
                ctx.writeAndFlush(mId + "##EXCEPTION:can't find\r\n");
            } else {
                String[] classStr = paramsTypes.split(" ");
                String[] argumentsStr = paramValues.split(" ");

                Class[] paramsClass = new Class[0];
                Object[] arguments = new Object[0];

                SimpleInvocation invocation = new SimpleInvocation(method, paramsClass, arguments);
                try {
                    Object object = invoker.invoke(invocation);
                    ctx.writeAndFlush(mId + "##" + object.toString() + "\r\n");
                } catch (Throwable e) {
                    e.printStackTrace();
                    ctx.writeAndFlush(mId + "##EXCEPTION:call error" + "\r\n");
                }
            }
        } catch (Exception e) {
            ctx.writeAndFlush("EXCEPTION##call error" + "\r\n");
            e.printStackTrace();
        }
    }

    public void export(String type, Invoker invoker) {
        invokerMap.put(type, invoker);
    }
}
