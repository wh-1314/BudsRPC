package cn.zhengjianglong.budsrpc.registry.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import cn.zhengjianglong.budsrpc.registry.client.handler.BudsRpcRegistryClientHandler;
import cn.zhengjianglong.budsrpc.remoting.client.NettyClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 20:42
 */
public class RegistryClient {

    private String host;
    private int port;

    private RegistryClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void bind() {

        NettyClient client = new NettyClient("localhost", 8500);
    }

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8500;
        RegistryClient client = new RegistryClient(host, port);
        client.bind();

    }
}
