package cn.zhengjianglong.budsrpc.remoting.request;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 13:28
 */
public class Request {
    private static final AtomicLong INVOKE_ID = new AtomicLong(0);
    // 请求数据
    private Object mData;
    // 表示请求的唯一Id, 用来做异步处理使用。根据这个mid去获取结果
    private final long mId;

    public Request() {
        mId = newId();
    }

    public Object getmData() {
        return mData;
    }

    public void setmData(Object mData) {
        this.mData = mData;
    }

    private static long newId() {
        // getAndIncrement() When it grows to MAX_VALUE, it will grow to MIN_VALUE, and the negative can be used as ID
        return INVOKE_ID.getAndIncrement();
    }

    public long getId() {
        return mId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "mData=" + mData +
                ", mId=" + mId +
                '}';
    }
}
