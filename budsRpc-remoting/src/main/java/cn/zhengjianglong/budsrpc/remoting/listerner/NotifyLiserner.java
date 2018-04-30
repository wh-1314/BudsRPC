package cn.zhengjianglong.budsrpc.remoting.listerner;

import cn.zhengjianglong.budsrpc.common.Path;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 23:29
 */
public interface NotifyLiserner {

    void notify(Path path);
}
