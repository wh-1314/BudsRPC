package cn.zhengjianglong.budsrpc.common.log;

import org.slf4j.LoggerFactory;

/**
 * @author: zhengjianglong
 * @create: 2018-05-04 20:47
 */
public class Slf4jLogger implements Logger {

    private org.slf4j.Logger logger;

    public Slf4jLogger(Class className) {
        logger = LoggerFactory.getLogger(className);
    }


}
