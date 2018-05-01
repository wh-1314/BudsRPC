package cn.zhengjianglong.budsrpc.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 09:40
 */
public class ProxyActionHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object object =method.invoke(proxy, args);

        return object;
    }
}
