package cn.zhengjianglong.budsrpc.rpc;

/**
 * @author: zhengjianglong
 * @create: 2018-05-01 10:21
 */
public class SimpleInvocation implements Invocation {
    private String methodName;
    private Class<?>[] paramsTypes;
    private Object[] arguments;

    public SimpleInvocation(String methodName, Class<?>[] paramsTypes, Object[] arguments) {
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
}
