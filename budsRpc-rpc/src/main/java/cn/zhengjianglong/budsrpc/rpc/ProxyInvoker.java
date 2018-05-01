package cn.zhengjianglong.budsrpc.rpc;

import java.lang.reflect.Method;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 09:28
 */
public class ProxyInvoker<T> implements Invoker<T> {
    private final T proxy;

    private final Class<T> type;

    public ProxyInvoker(T proxy, Class<T> type) {
        if (proxy == null) {
            throw new IllegalArgumentException("proxy == null");
        }
        if (type == null) {
            throw new IllegalArgumentException("interface == null");
        }
        if (!type.isInstance(proxy)) {
            throw new IllegalArgumentException(proxy.getClass().getName() + " not implement interface " + type);
        }
        this.proxy = proxy;
        this.type = type;
    }

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public Object invoke(Invocation invocation) throws Throwable {

        System.out.println("调用接口：" + type);
        System.out.println("调用方法：" + invocation.getMethodName());

        // 调用Person类中的run方法
        Method method = type.getMethod(invocation.getMethodName());
        return method.invoke(proxy);
    }

}
