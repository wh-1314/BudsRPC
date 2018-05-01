package cn.zhengjianglong.budsrpc.config.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.zhengjianglong.budsrpc.config.ServiceConfig;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 07:50
 */
public class ServiceBean<T> extends ServiceConfig<T>
        implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>,
        BeanNameAware {

    private transient String beanName;

    private transient ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        export(); // 在系统启动完成后暴露服务
    }



    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
