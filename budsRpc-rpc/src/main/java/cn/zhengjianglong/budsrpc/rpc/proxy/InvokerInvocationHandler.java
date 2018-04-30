package cn.zhengjianglong.budsrpc.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.zhengjianglong.budsrpc.rpc.Invoker;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:28
 */
public class InvokerInvocationHandler implements InvocationHandler {
    private Invoker invoker;

    public InvokerInvocationHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("[BudsRPC] pre invoke");

        Object result = invoker.invoke();

        System.out.println("[BudsRPC] after invoke");
        return result;
    }
}
