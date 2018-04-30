package cn.zhengjianglong.budsrpc.rpc;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 19:53
 */
public interface Exporter<T> {

    /**
     * get invoker.
     *
     * @return invoker
     */
    Invoker<T> getInvoker();

    /**
     * unexport.
     * <p>
     * <code>
     * getInvoker().destroy();
     * </code>
     */
    void unexport();

}
