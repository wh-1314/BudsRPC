package cn.zhengjianglong.budsrpc.remoting.response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.zhengjianglong.budsrpc.remoting.exception.RemotingException;
import cn.zhengjianglong.budsrpc.remoting.request.Request;
import io.netty.channel.Channel;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 13:49
 */
public class DefaultFuture implements ResponseFuture {
    // invoke id.
    private final long id;
    private final Channel channel;
    private final Request request;
    private final int timeout;
    private final Lock lock = new ReentrantLock();
    // 用来进行控制阻塞的
    private final Condition done = lock.newCondition();
    private final long start = System.currentTimeMillis();
    private volatile long sent;
    private volatile Response response;
    // private volatile ResponseCallback callback;
    // 全局缓存
    private static final Map<Long, DefaultFuture> FUTURES = new ConcurrentHashMap<Long, DefaultFuture>();
    private static final Map<Long, Channel> CHANNELS = new ConcurrentHashMap<Long, Channel>();

    public DefaultFuture(Channel channel, Request request, int timeout) {
        this.channel = channel;
        this.request = request;
        this.timeout = timeout;
        this.id = request.getId();
        // put into waiting map.
        FUTURES.put(id, this);
        // 这个是做什么的
        // CHANNELS.put(id, channel);
    }

    /**
     * 接收数据
     *
     * @param channel
     * @param response
     */
    public static void received(Channel channel, Response response) {
        try {
            DefaultFuture future = FUTURES.remove(response.getId());
            if (future != null) {
                // 更新，并调用回调函数 callback
                future.doReceived(response);
            } else {

            }
        } finally {
            CHANNELS.remove(response.getId());
        }
    }

    private void doReceived(Response res) {
        lock.lock();
        try {
            response = res;
            if (done != null) {
                done.signal();
            }
        } finally {
            lock.unlock();
        }
        // TODO 回调
        /*if (callback != null) {
            invokeCallback(callback);
        }*/
    }

    @Override
    public Object get() throws RemotingException {
        return get(timeout);
    }

    @Override
    public Object get(int timeout) throws RemotingException {
        if (!isDone()) {
            long start = System.currentTimeMillis();
            lock.lock();
            try {
                while (!isDone()) {
                    done.await(timeout, TimeUnit.MILLISECONDS);
                    if (isDone() || System.currentTimeMillis() - start > timeout) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
            if (!isDone()) {
                // todo 超时
            }
        }
        return response.getmResult();
    }

    @Override
    public boolean isDone() {
        return response != null;
    }
}
