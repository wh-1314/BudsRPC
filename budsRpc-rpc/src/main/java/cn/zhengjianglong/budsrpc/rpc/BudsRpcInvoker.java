package cn.zhengjianglong.budsrpc.rpc;

import cn.zhengjianglong.budsrpc.remoting.response.ResponseFuture;
import cn.zhengjianglong.budsrpc.rpc.handler.ClientHolder;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:42
 */
public class BudsRpcInvoker<T> implements Invoker<T> {
    private Class<?> interfaceClass;

    public BudsRpcInvoker(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Class<T> getInterface() {
        return null;
    }

    @Override
    public Object invoke(Invocation invocation) {
        System.out.println("[BudsRPC] 调用远程服务");

        String cmd = String.format("%s##%s##%s##%s", interfaceClass.getName(), invocation.getMethodName(),
                invocation.getParameterTypes(), invocation.getArguments());

        ResponseFuture future = ClientHolder.send(cmd);
        Object object = future.get();
        System.out.println("[BudsRPC] 远程结果:" + object);

        return object;
    }
}
