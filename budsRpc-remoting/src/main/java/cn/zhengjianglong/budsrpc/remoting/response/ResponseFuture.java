package cn.zhengjianglong.budsrpc.remoting.response;

import cn.zhengjianglong.budsrpc.remoting.exception.RemotingException;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 13:48
 */
public interface ResponseFuture {

    Object get() throws RemotingException;

    Object get(int timeoutInMillis) throws RemotingException;

    // void setCallback(ResponseCallback callback);

    boolean isDone();
}
