package cn.zhengjianglong.budsrpc.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zhengjianglong.budsrpc.demo.api.service.ActionService;
import cn.zhengjianglong.budsrpc.demo.api.service.HelloService;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 17:53
 */
public class Consumer {
    public static void main(String[] args) {
        // 完成配置的加载， 并记载完成后调用接口暴露逻辑 export
        ApplicationContext ctx = new ClassPathXmlApplicationContext("demo-consumer.xml");
        HelloService demoService = (HelloService) ctx.getBean("helloService");
        String result = demoService.sayHello();
        System.out.println(result);

        // ActionService actionService = (ActionService) ctx.getBean("actionService");
        // actionService.add(4, 5);
    }
}
