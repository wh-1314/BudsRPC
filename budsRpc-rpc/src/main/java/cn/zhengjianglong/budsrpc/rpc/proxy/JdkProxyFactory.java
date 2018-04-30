package cn.zhengjianglong.budsrpc.rpc.proxy;

import java.lang.reflect.Proxy;

import cn.zhengjianglong.budsrpc.rpc.Invoker;

/**
 * 通过JDK方式生成代理对象
 *
 * @author: zhengjianglong
 * @create: 2018-04-30 16:24
 */
public class JdkProxyFactory implements ProxyFactory {

    @Override
    public <T> T getProxy(Invoker<T> invoker, Class<?>[] types) {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), types, new
                InvokerInvocationHandler(invoker));
    }
}
