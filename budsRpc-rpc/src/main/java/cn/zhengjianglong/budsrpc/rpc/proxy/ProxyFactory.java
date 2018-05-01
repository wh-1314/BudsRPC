package cn.zhengjianglong.budsrpc.rpc.proxy;

import cn.zhengjianglong.budsrpc.rpc.Invoker;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:23
 */
public interface ProxyFactory<T> {

    public abstract <T> T getProxy(Invoker<T> invoker, Class<?>[] types);

    /**
     * 用于服务暴露
     *
     * @param proxy
     * @param type
     * @param <T>
     *
     * @return
     */
    public <T> Invoker<T> getInvoker(T proxy, Class<T> type);
}
