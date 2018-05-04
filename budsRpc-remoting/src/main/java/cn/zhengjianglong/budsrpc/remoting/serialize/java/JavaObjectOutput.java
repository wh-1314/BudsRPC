package cn.zhengjianglong.budsrpc.remoting.serialize.java;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import cn.zhengjianglong.budsrpc.remoting.serialize.ObjectOutput;

/**
 * @author: zhengjianglong
 * @create: 2018-05-04 15:26
 */
public class JavaObjectOutput implements ObjectOutput {
    private final ObjectOutputStream outputStream;

    public JavaObjectOutput(OutputStream os) throws IOException {
        outputStream = new ObjectOutputStream(os);
    }

    @Override
    public void writeObject(Object obj) throws IOException {
        if (obj == null) {
            outputStream.writeObject(obj);
        }
    }
}
