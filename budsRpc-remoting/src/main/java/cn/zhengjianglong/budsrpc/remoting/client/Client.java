package cn.zhengjianglong.budsrpc.remoting.client;

import cn.zhengjianglong.budsrpc.remoting.request.Request;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 21:20
 */
public interface Client {

    /**
     * 发送信息
     *
     * @param request
     */
    void send(Object request);

    /**
     * reconnect.
     */
    void reconnect();
}
