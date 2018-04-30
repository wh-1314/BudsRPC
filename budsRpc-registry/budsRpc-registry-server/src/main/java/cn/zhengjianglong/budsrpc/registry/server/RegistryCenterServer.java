package cn.zhengjianglong.budsrpc.registry.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zhengjianglong.budsrpc.remoting.server.NettyServer;

/**
 * 注册中心服务
 *
 * @author: zhengjianglong
 * @create: 2018-04-30 20:19
 */
public class RegistryCenterServer {
    private static final Logger logger = LoggerFactory.getLogger(RegistryCenterServer.class);

    private int port;

    public RegistryCenterServer(int port) {
        this.port = port;
    }

    public void bind() {
        logger.info("[BudsRPC][RegistryCenterServer] start.");
        NettyServer server = new NettyServer("", port, new RegistryServerHandler());
        server.bind();

    }

    public static void main(String[] args) {
        int port = 8500;
        RegistryCenterServer registryCenterServer = new RegistryCenterServer(port);
        registryCenterServer.bind();
    }
}
