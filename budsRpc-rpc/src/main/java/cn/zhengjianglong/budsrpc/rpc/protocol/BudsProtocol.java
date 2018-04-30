package cn.zhengjianglong.budsrpc.rpc.protocol;

import cn.zhengjianglong.budsrpc.rpc.BudsRpcException;
import cn.zhengjianglong.budsrpc.rpc.Exporter;
import cn.zhengjianglong.budsrpc.rpc.Invoker;
import cn.zhengjianglong.budsrpc.rpc.Protocol;

/**
 * 默认的协议
 *
 * @author: zhengjianglong
 * @create: 2018-04-30 19:56
 */
public class BudsProtocol<T> implements Protocol<T>{

    @Override
    public <T1> Invoker<T1> refer(Class<T1> type) throws BudsRpcException {

        return null;
    }


    @Override
    public <T1> Exporter<T1> export(Invoker<T1> invoker) throws BudsRpcException {
        return null;
    }
}
