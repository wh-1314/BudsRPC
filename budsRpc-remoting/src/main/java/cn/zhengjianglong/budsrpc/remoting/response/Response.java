package cn.zhengjianglong.budsrpc.remoting.response;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 14:13
 */
public class Response {
    // 唯一标识
    private long mId = 0;
    // 服务端响应结果
    private Object mResult;

    public Response(long mId, Object mResult) {
        this.mId = mId;
        this.mResult = mResult;
    }

    public long getId() {
        return mId;
    }

    public void setId(long mId) {
        this.mId = mId;
    }

    public Object getmResult() {
        return mResult;
    }

    public void setmResult(Object mResult) {
        this.mResult = mResult;
    }
}

