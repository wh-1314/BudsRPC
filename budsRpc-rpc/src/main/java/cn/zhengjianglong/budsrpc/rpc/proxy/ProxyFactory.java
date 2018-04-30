package cn.zhengjianglong.budsrpc.rpc.proxy;

import cn.zhengjianglong.budsrpc.rpc.Invoker;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:23
 */
public interface ProxyFactory {

    public abstract <T> T getProxy(Invoker<T> invoker, Class<?>[] types);
}
