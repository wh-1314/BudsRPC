package cn.zhengjianglong.budsrpc.remoting.serialize.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.OutputStream;

import cn.zhengjianglong.budsrpc.remoting.serialize.ObjectOutput;
import cn.zhengjianglong.budsrpc.remoting.serialize.Serialization;

/**
 * @author: zhengjianglong
 * @create: 2018-05-04 13:57
 */
public class JavaSerialization implements Serialization {

    @Override
    public byte getContentTypeId() {
        return 1;
    }

    @Override
    public ObjectOutput serialize(OutputStream output) throws IOException {
        return new JavaObjectOutput(output);
    }

    @Override
    public ObjectInput deserialize(InputStream input) throws IOException {
        return null;
    }
}
