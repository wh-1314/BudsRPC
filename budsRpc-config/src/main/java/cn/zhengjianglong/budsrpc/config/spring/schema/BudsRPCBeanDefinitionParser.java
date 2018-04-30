package cn.zhengjianglong.budsrpc.config.spring.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

import cn.zhengjianglong.budsrpc.config.spring.ReferenceBean;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 15:51
 */
public class BudsRPCBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    protected Class getBeanClass(Element element) {
        return ReferenceBean.class;
    }

    protected void doParse(Element element, BeanDefinitionBuilder bean) {
        String interfaceClass = element.getAttribute("interface");
        if (StringUtils.hasText(interfaceClass)) {
            bean.addPropertyValue("interfaceClass", interfaceClass);
        }
    }

}
