package cn.zhengjianglong.budsrpc.registry.server.container;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 20:39
 */
public class RegistryCenter {

    private static Map<String, Map<String, String>> registries = new ConcurrentHashMap();

}
