package cn.zhengjianglong.budsrpc.rpc.handler;

import cn.zhengjianglong.budsrpc.remoting.client.NettyClient;
import cn.zhengjianglong.budsrpc.remoting.request.Request;
import cn.zhengjianglong.budsrpc.remoting.response.DefaultFuture;
import cn.zhengjianglong.budsrpc.remoting.response.ResponseFuture;
import io.netty.channel.ChannelHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 12:16
 */
public class ClientHolder {
    private static NettyClient client;
    private static ChannelHandler channelHandler;
    private static ProviderClientHandler handler;
    private static Thread clientThread;

    static {
        handler = new ProviderClientHandler();
        client = new NettyClient("localhost", 8501, handler);
        /**
         * 启动服务
         */
        clientThread = new Thread(new Runnable() {
            @Override
            public void run() {
                client.start();
            }
        });
        clientThread.start();
    }

    public static ResponseFuture send(Object msg) {
        Request request = new Request();
        request.setmData(msg);

        // 同步转异步
        ResponseFuture feture = new DefaultFuture(client.getChannel(), request, 1000);
        client.send(request);

        return feture;
    }

}
