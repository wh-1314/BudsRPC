package cn.zhengjianglong.budsrpc.config.spring.schema;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import cn.zhengjianglong.budsrpc.config.spring.ReferenceBean;
import cn.zhengjianglong.budsrpc.config.spring.ServiceBean;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 15:51
 */
public class BudsRPCBeanDefinitionParser implements BeanDefinitionParser {
    private final Class<?> beanClass;

    public BudsRPCBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String id = element.getAttribute("id");

        /**
         * rpc框架中provider的service是没有配置id的。但是service必须注册，不然后续接口暴露都无法执行
         */
        if ((id == null || id.length() == 0)) {
            String generatedBeanName = element.getAttribute("interface");
            id = generatedBeanName;
            int counter = 2;
            while (parserContext.getRegistry().containsBeanDefinition(id)) {
                id = generatedBeanName + (counter++);
            }
        }
        //
        /**
         * 注册, 必须要注册进去。不然spring找不到这个bean
         * spring在容器加载完成，会先注册监听器，只有注册到了spring容器中，才能被找到。这样就可以在完成容器加载后执行方法暴露
         */
        parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
        beanDefinition.getPropertyValues().addPropertyValue("id", id);

        if (ServiceBean.class.equals(beanClass)) {
            /**
             * 解析service
             */
            String interfaceClass = element.getAttribute("interface");
            beanDefinition.getPropertyValues().addPropertyValue("interfaceClass", interfaceClass);

            String ref = element.getAttribute("ref");
            ref = ref.trim();
            BeanDefinition refBean = parserContext.getRegistry().getBeanDefinition(ref);
            beanDefinition.getPropertyValues().addPropertyValue("ref", refBean);

        } else if (ReferenceBean.class.equals(beanClass)) {

            /**
             * 解析reference
             */
            String interfaceClass = element.getAttribute("interface");
            if (StringUtils.hasText(interfaceClass)) {
                beanDefinition.getPropertyValues().addPropertyValue("interfaceClass", interfaceClass);
            }
        }
        return beanDefinition;
    }
}
