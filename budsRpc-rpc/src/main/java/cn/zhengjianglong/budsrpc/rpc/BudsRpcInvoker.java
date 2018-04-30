package cn.zhengjianglong.budsrpc.rpc;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 18:42
 */
public class BudsRpcInvoker<T> implements Invoker<T> {
    private Class<?> interfaceClass;

    public BudsRpcInvoker(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Class<T> getInterface() {
        return null;
    }

    @Override
    public Object invoke() {
        System.out.println("[BudsRPC] 调用远程服务");

        System.out.println("[BudsRPC] 远程结果");
        return " hello";
    }
}
