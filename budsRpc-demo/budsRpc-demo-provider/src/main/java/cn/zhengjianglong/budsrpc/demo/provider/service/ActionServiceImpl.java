package cn.zhengjianglong.budsrpc.demo.provider.service;

import cn.zhengjianglong.budsrpc.demo.api.service.ActionService;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 07:36
 */
public class ActionServiceImpl implements ActionService {

    @Override
    public int add(int numA, int numB) {
        return numA + numB;
    }
}
