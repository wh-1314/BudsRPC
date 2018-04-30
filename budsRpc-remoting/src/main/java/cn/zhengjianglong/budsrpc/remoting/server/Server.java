package cn.zhengjianglong.budsrpc.remoting.server;

import java.net.InetSocketAddress;
import java.nio.channels.Channel;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:21
 */
public interface Server {

    /**
     * get channel.
     *
     * @param remoteAddress
     *
     * @return channel
     */
    Channel getChannel(InetSocketAddress remoteAddress);
}
