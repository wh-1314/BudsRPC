package cn.zhengjianglong.budsrpc.config;

import cn.zhengjianglong.budsrpc.rpc.BudsRpcInvoker;
import cn.zhengjianglong.budsrpc.rpc.Invoker;
import cn.zhengjianglong.budsrpc.rpc.proxy.JdkProxyFactory;
import cn.zhengjianglong.budsrpc.rpc.proxy.ProxyFactory;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 15:27
 */
public class ReferenceConfig<T> {
    // 配置对应的接口 class
    private Class<?> interfaceClass;
    // 接口代理类引用
    private transient volatile T ref;
    private String id;

    private transient volatile Invoker<?> invoker;
    // 代理工厂
    private static final ProxyFactory proxyFactory = new JdkProxyFactory();

    public T get() {
        if (ref == null) {
            /**
             * 初始化
             */
            init();
        }
        return ref;
    }

    /**
     * 初始化
     */
    private void init() {
        /**
         * 创建代理
         */
        ref = createProxy();
    }

    /**
     * 创建代理
     *
     * @return
     */
    private T createProxy() {
        boolean isJvmRefer = false;
        if (isJvmRefer) {
            // TODO

        } else {
            invoker = new BudsRpcInvoker(interfaceClass);
        }

        /**
         * 创建代理
         */
        // create service proxy
        return (T) proxyFactory.getProxy(invoker, new Class[] {interfaceClass});
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public T getRef() {
        return ref;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
