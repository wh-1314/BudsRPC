package cn.zhengjianglong.budsrpc.remoting.response;

import java.io.Serializable;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 14:13
 */
public class Response implements Serializable {
    // 唯一标识
    private long id = 0;
    // 服务端响应结果
    private Object result;

    public Response(long id) {
        this.id = id;
    }

    public Response(long mId, Object result) {
        this.id = mId;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

