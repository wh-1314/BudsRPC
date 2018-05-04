package cn.zhengjianglong.budsrpc.remoting.serialize;

import java.io.IOException;

/**
 * @author: zhengjianglong
 * @create: 2018-05-04 15:29
 */
public interface ObjectOutput {

    void writeObject(Object obj) throws IOException;
}
