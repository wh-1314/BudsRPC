package cn.zhengjianglong.budsrpc.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.request.Request;
import cn.zhengjianglong.budsrpc.remoting.response.ResponseFuture;
import cn.zhengjianglong.budsrpc.rpc.handler.ClientHolder;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:42
 */
public class BudsRpcInvoker<T> implements Invoker<T> {
    private static final Logger logger = LoggerFactory.getLogger(BudsRpcInvoker.class);
    private Class<T> interfaceClass;

    public BudsRpcInvoker(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Class<T> getInterface() {
        return interfaceClass;
    }

    @Override
    public Object invoke(Invocation invocation) {
        if (logger.isDebugEnabled()) {
            logger.debug("[BudsRPC] ready to call {}", invocation.getInterface().getName());
        }

        Request request = new Request();
        request.setData(invocation);
        ResponseFuture future = ClientHolder.send(request);
        Object object = future.get();

        if (logger.isDebugEnabled()) {
            logger.debug("[BudsRPC] finished to call {}, result={}",
                    invocation.getInterface().getName(), object);
        }

        return object;
    }
}
