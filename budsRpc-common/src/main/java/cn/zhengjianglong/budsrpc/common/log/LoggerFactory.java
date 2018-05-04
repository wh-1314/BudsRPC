package cn.zhengjianglong.budsrpc.common.log;

/**
 * @author: zhengjianglong
 * @create: 2018-05-04 20:46
 */
public class LoggerFactory {
    /**
     * 返回一个logger
     *
     * @return
     */
    public static Logger getLogger(Class<?> className) {

        return new Slf4jLogger(className);
    }
}
