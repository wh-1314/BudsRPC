package cn.zhengjianglong.budsrpc.registry.server;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.common.ActionEnum;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 22:45
 */
public class RegistryServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger logger = LoggerFactory.getLogger(RegistryCenterServer.class);
    // 所有实例
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 订阅
    private static Map<String, ChannelGroup> subscribMap = new ConcurrentHashMap<>();

    // 注册
    private static Map<String, Set<Channel>> registryMap = new ConcurrentHashMap<>();

    // provider 地址和提供服务之间的关系, 一个节点可能提供多个服务
    private static Map<String, Set<String>> providerMap = new ConcurrentHashMap<>();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        /**
         * 加入
         */
        Channel incoming = ctx.channel();
        logger.info("[BudsRpc ] node[{}] join.", incoming.remoteAddress());
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        String remoteAddress = incoming.remoteAddress().toString();
        logger.info("[BudsRpc ] node[{}] leave.", remoteAddress);

        if (providerMap.containsKey(remoteAddress)) {
            // 说明是一个提供者，需要广播所有的 订阅者，更新
            Set<String> serviceSet = providerMap.get(remoteAddress);
            for (String service : serviceSet) {
                ChannelGroup channelGroup = subscribMap.get(service);
                // 广播给所有订阅者
                channelGroup.writeAndFlush("OFFLINE:" + remoteAddress + " " + service);
            }
        }

    }

    /**
     * ACTION\r\nSERVICE\r\nADDRESS
     * <p>
     * ACTION:
     * 1. REGISTRY
     * 2. UNREGISTRY
     * 3. SUBSCRIBE
     * 3. UNSUBSCRIBE
     *
     * @param ctx
     * @param msg
     *
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception { // (4)
        Channel incoming = ctx.channel();

        String remoteAddress = incoming.remoteAddress().toString();
        logger.info("[BudsRpc ][Registry-server] receive data=[{}]  from {}", msg, remoteAddress);

        String[] cmd = msg.split("\r\n");
        ActionEnum action = ActionEnum.getAction(cmd[0]);
        String service = cmd[1];
        String address = cmd[2];
        String port = cmd[3];

        switch (action) {
            case ACTION_REGISTRY:
                Set<Channel> channelSet = registryMap.get(service);
                if (channelSet == null) {
                    channelSet = new HashSet<>();
                    registryMap.put(service, channelSet);
                }
                channelSet.add(incoming);

                Set<String> serviceSet = providerMap.get(remoteAddress);
                if (serviceSet == null) {
                    serviceSet = new HashSet<>();
                    providerMap.put(remoteAddress, serviceSet);
                }
                serviceSet.add(service);

                // 通知订阅者
                notify(service);

            case ACTION_SUBSCRIBE:
                ChannelGroup channelGroup = subscribMap.get(service);
                if (channelGroup == null) {
                    channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                    subscribMap.put(service, channelGroup);
                }
                channelGroup.add(incoming);
                break;

        }

        for (Channel channel : channels) {
            if (channel != incoming) {
                channel.writeAndFlush("[" + incoming.remoteAddress() + "]" + msg + "\n");
            } else {
                channel.writeAndFlush("[you]" + msg + "\n");
            }
        }
    }

    private void notify(String service) {
        Set<Channel> channelSet = registryMap.get(service);
        ChannelGroup channelGroup = subscribMap.get(service);
        StringBuilder stringBuilder = new StringBuilder();

        for (Channel channel : channelSet) {

        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (7)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:" + incoming.remoteAddress() + "异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}