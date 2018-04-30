package cn.zhengjianglong.budsrpc.remoting;

import cn.zhengjianglong.budsrpc.remoting.client.Client;
import cn.zhengjianglong.budsrpc.remoting.exception.RemotingException;
import cn.zhengjianglong.budsrpc.remoting.server.Server;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:22
 */
public interface Transporter {

    /**
     * 绑定某端口，启动服务端
     *
     * @return
     *
     * @throws RemotingException
     */
    Server bind() throws RemotingException;

    /**
     * 连接到服务端
     *
     * @return
     *
     * @throws RemotingException
     */
    Client connect() throws RemotingException;
}
