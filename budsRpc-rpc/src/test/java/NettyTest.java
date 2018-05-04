import org.junit.Test;

import cn.zhengjianglong.budsrpc.remoting.client.NettyClient;
import cn.zhengjianglong.budsrpc.remoting.server.NettyServer;
import cn.zhengjianglong.budsrpc.rpc.handler.ProviderClientHandler;
import cn.zhengjianglong.budsrpc.rpc.handler.ProviderServerHandler;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 11:27
 */
public class NettyTest {

    @Test
    public void server() {
        ProviderServerHandler
                providerServerHandler = new ProviderServerHandler();
        NettyServer server = new NettyServer(8501, providerServerHandler);
        server.bind();
    }

    @Test
    public void client() throws Exception {
        ProviderClientHandler handler = new ProviderClientHandler();
        final NettyClient client = new NettyClient("localhost", 8501, handler);

        new Thread(new Runnable() {
            @Override
            public void run() {
                client.start();
            }
        }).start();

        String cmd = String.format("%s##%s##%s##%s\r\n", "test", "test",
                "test", "test");
    }
}
