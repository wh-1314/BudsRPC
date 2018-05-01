package cn.zhengjianglong.budsrpc.config;

import cn.zhengjianglong.budsrpc.rpc.Invoker;
import cn.zhengjianglong.budsrpc.rpc.handler.ServerHolder;
import cn.zhengjianglong.budsrpc.rpc.proxy.JdkProxyFactory;
import cn.zhengjianglong.budsrpc.rpc.proxy.ProxyFactory;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 07:44
 */
public class ServiceConfig<T> {
    // 配置对应的接口 class
    private Class<?> interfaceClass;
    // 接口代理类引用
    private volatile T ref;
    private String id;

    private volatile boolean init = false;

    private ProxyFactory proxyFactory = new JdkProxyFactory();

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }


    protected void export() {
        /**
         * 本地暴露
         */
        exportLocal();

        // 远程暴露
        Invoker<T> invoker = proxyFactory.getInvoker(ref, interfaceClass);
        ServerHolder.export(interfaceClass.getName(), invoker);
    }


    private void exportLocal() {
        /**
         * 本地暴露
         */
    }

    public T getRef() {
        return ref;
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
