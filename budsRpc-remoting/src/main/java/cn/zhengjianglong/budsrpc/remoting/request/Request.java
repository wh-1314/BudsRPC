package cn.zhengjianglong.budsrpc.remoting.request;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 13:28
 */
public class Request implements Serializable{
    private static final AtomicLong INVOKE_ID = new AtomicLong(0);
    // 表示请求的唯一Id, 用来做异步处理使用。根据这个mid去获取结果
    private final long id;
    // 请求数据
    private Object data;

    public Request() {
        id = newId();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object Data) {
        this.data = Data;
    }

    private static long newId() {
        // getAndIncrement() When it grows to MAX_VALUE, it will grow to MIN_VALUE, and the negative can be used as ID
        return INVOKE_ID.getAndIncrement();
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "mData=" + data +
                ", mId=" + id +
                '}';
    }
}
