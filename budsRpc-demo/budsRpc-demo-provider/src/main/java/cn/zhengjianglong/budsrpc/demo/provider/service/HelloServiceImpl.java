package cn.zhengjianglong.budsrpc.demo.provider.service;

import cn.zhengjianglong.budsrpc.demo.api.service.HelloService;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 07:35
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello world!";
    }

    @Override
    public void doNothing() {
        System.out.println("===============");
    }
}
