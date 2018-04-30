package cn.zhengjianglong.budsrpc.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author: zhengjianglong
 * @create: 2018-04-30 17:53
 */
public class Consumer {
    public static void main(String[] args) {
        // 完成配置的加载， 并记载完成后调用接口暴露逻辑 export
        ApplicationContext ctx = new ClassPathXmlApplicationContext("demo-consumer.xml");
        ctx.getBean("demoService");
    }
}
