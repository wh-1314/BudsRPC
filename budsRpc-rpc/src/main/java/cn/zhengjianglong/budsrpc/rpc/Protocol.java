package cn.zhengjianglong.budsrpc.rpc;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 19:51
 */
public interface Protocol<T> {

    <T> Invoker<T> refer(Class<T> type) throws BudsRpcException;

    <T> Exporter<T> export(Invoker<T> invoker) throws BudsRpcException;

}
