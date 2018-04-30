package cn.zhengjianglong.budsrpc.remoting;

import cn.zhengjianglong.budsrpc.remoting.client.Client;
import cn.zhengjianglong.budsrpc.remoting.exception.RemotingException;
import cn.zhengjianglong.budsrpc.remoting.server.Server;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:26
 */
public class NettyTransporter implements Transporter {
    @Override
    public Server bind() throws RemotingException {
        return null;
    }

    @Override
    public Client connect() throws RemotingException {
        return null;
    }
}
