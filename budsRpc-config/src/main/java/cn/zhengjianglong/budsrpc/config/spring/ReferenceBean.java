package cn.zhengjianglong.budsrpc.config.spring;

import org.springframework.beans.factory.FactoryBean;

import cn.zhengjianglong.budsrpc.config.ReferenceConfig;
import cn.zhengjianglong.budsrpc.rpc.proxy.BudsRpcProxy;
import cn.zhengjianglong.budsrpc.rpc.proxy.jdk.JdkProxyFactory;

/**
 * 引用
 *
 * @author: zhengjianglong
 * @create: 2018-04-30 15:27
 */
public class ReferenceBean<T> extends ReferenceConfig<T> implements FactoryBean {
    private JdkProxyFactory proxy;

    @Override
    public Object getObject() throws Exception {
        System.out.println("[BudsRPC] get object.");

        System.out.println(getInterfaceClass());
        proxy = new JdkProxyFactory();

        // proxy.getProxy(new Object(), new Class[] {getInterfaceClass()});
        return new BudsRpcProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return BudsRpcProxy.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
