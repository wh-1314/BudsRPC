package cn.zhengjianglong.budsrpc.rpc.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过JDK方式生成代理对象
 *
 * @author: zhengjianglong
 * @create: 2018-04-30 16:24
 */
public class JdkProxyFactory implements InvocationHandler {

    public <T> T getProxy(Object object, Class<?>[] interfaces) {
        /**
         *  代理执行InvokerInvocationHandler
         */
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行前");


        System.out.println("执行后");
        return null;
    }
}
