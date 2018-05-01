package cn.zhengjianglong.budsrpc.rpc;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:18
 */
public interface Invoker<T> {
    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();


    Object invoke(Invocation invocation) throws Throwable;
}
