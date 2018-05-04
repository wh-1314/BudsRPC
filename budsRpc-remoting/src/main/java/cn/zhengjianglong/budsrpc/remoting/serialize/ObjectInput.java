package cn.zhengjianglong.budsrpc.remoting.serialize;

import java.io.IOException;

/**
 * @author: zhengjianglong
 * @create: 2018-05-04 15:31
 */
public interface ObjectInput {

    /**
     * read object.
     *
     * @return object.
     */
    Object readObject() throws IOException, ClassNotFoundException;
}
