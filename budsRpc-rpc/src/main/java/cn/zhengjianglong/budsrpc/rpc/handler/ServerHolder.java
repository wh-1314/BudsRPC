package cn.zhengjianglong.budsrpc.rpc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.server.NettyServer;
import cn.zhengjianglong.budsrpc.rpc.Invoker;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 12:33
 */
public class ServerHolder {
    Logger logger = LoggerFactory.getLogger(ServerHolder.class);

    private static NettyServer server;
    private static ProviderServerHandler providerServerHandler;
    private static Thread serverThread;

    static {
        providerServerHandler = new ProviderServerHandler();

        /**
         * 启动一个线程
         */
        server = new NettyServer(8501, providerServerHandler);
        serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                server.bind();
            }
        });
        serverThread.start();
    }

    public static void export(String type, Invoker invoker) {
        System.out.println("服务暴露接口:" + type);
        providerServerHandler.export(type, invoker);
    }
}
