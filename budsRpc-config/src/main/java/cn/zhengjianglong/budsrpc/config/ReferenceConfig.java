package cn.zhengjianglong.budsrpc.config;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 15:27
 */
public class ReferenceConfig<T>  {
    // 配置对应的接口 class
    private Class<?> interfaceClass;
    // 接口代理类引用
    private transient volatile T ref;

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }
}
