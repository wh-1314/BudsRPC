package cn.zhengjianglong.budsrpc.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import cn.zhengjianglong.budsrpc.config.spring.ReferenceBean;
import cn.zhengjianglong.budsrpc.config.spring.ServiceBean;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 15:53
 */
public class BudsRPCNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        /**
         * reference 的解析交给 BudsRPCBeanDefinitionParser
         */
        registerBeanDefinitionParser("reference", new BudsRPCBeanDefinitionParser(ReferenceBean.class));
        registerBeanDefinitionParser("service", new BudsRPCBeanDefinitionParser(ServiceBean.class));
    }
}
