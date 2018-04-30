package cn.zhengjianglong.budsrpc.remoting.client;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:20
 */
public interface Client {

    /**
     * 发送信息
     *
     * @param msg
     */
    void send(Object msg);

    /**
     * reconnect.
     */
    void reconnect();
}
