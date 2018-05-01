package cn.zhengjianglong.budsrpc.demo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 07:27
 */
public class Provider {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"demo-provider.xml"});
        context.start();
    }
}
