package cn.zhengjianglong.budsrpc.remoting.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.OutputStream;

/**
 * 序列化方式
 *
 * @author: zhengjianglong
 * @create: 2018-05-04 13:54
 */
public interface Serialization {

    /**
     * 序列化类型
     *
     * @return content type id
     */
    byte getContentTypeId();


    /**
     * create serializer
     *
     * @param output
     * @return serializer
     * @throws IOException
     */
    ObjectOutput serialize(OutputStream output) throws IOException;

    /**
     * create deserializer
     *
     * @param input
     * @return deserializer
     * @throws IOException
     */
    ObjectInput deserialize(InputStream input) throws IOException;

}
