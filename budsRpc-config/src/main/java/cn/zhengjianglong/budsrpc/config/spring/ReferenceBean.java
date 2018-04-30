package cn.zhengjianglong.budsrpc.config.spring;

import org.springframework.beans.factory.FactoryBean;

import cn.zhengjianglong.budsrpc.config.ReferenceConfig;
import cn.zhengjianglong.budsrpc.rpc.proxy.JdkProxyFactory;

/**
 * 引用
 *
 * @author: zhengjianglong
 * @create: 2018-04-30 15:27
 */
public class ReferenceBean<T> extends ReferenceConfig<T> implements FactoryBean {
    private JdkProxyFactory proxy;

    public Object getObject() throws Exception {
        System.out.println("[BudsRPC] get object.");
        System.out.println(getInterfaceClass());

        proxy = new JdkProxyFactory();

        return super.get();
    }

    @Override
    public Class<?> getObjectType() {
        return getInterfaceClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
