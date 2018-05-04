package cn.zhengjianglong.budsrpc.rpc;

import java.io.Serializable;

/**
 * 最简单的接口定义
 *
 * @author: zhengjianglong
 * @create: 2018-05-01 10:21
 */
public class SimpleInvocation<T> implements Invocation<T>, Serializable {
    private Class<T> interfaceClass;
    private String methodName;
    private Class<?>[] paramsTypes;
    private Object[] arguments;

    public SimpleInvocation(Class<T> interfaceClass, String methodName, Class<?>[] paramsTypes, Object[] arguments) {
        this.interfaceClass = interfaceClass;
        this.methodName = methodName;
        this.paramsTypes = paramsTypes;
        this.arguments = arguments;
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public Class<?>[] getParameterTypes() {
        return paramsTypes;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Class getInterface() {
        return interfaceClass;
    }
}
