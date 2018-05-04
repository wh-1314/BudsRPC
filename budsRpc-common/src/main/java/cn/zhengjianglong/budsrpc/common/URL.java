package cn.zhengjianglong.budsrpc.common;

import java.io.Serializable;
import java.util.Map;

/**
 * 为什么要采用url? 为了更好的统一 http请求方式和service接口调用方式。
 * 1. http接口是不需要指定执行的方法的，只有调用方式 GET和POST
 *
 * @author: zhengjianglong
 * @create: 2018-05-04 19:03
 */
public class URL implements Serializable {
    private String protocol = null;

    // by default, host to registry
    private String host;

    // by default, port to registry
    private int port;

    private final String path;
    private String method;

    private volatile transient String full;

    public URL() {
        this.protocol = null;
        this.host = null;
        this.port = 0;
        this.path = null;
    }

    public URL(String path) {
        this.path = path;
    }

    public URL(String path, String method) {
        this.path = path;
        this.method = method;

        // protocol://username:password@host:port/path?key=value&key=value
        StringBuilder builder = new StringBuilder();
        builder.append("path=").append(path).append("?");
        builder.append("method=").append(method);
        full = builder.toString();
    }

    public URL(String path, String methodName, Map<String, String> params) {
        this.path = path;

        // protocol://username:password@host:port/path?key=value&key=value

        StringBuilder builder = new StringBuilder();
        builder.append("path=").append(path).append("?");
        builder.append("method=").append(methodName);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append("&")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }
        full = builder.toString();
    }

    public String getFull() {
        return full;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
