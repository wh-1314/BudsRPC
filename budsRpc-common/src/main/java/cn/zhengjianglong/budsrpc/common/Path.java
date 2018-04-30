package cn.zhengjianglong.budsrpc.common;

/**
 * @author: zhengjianglong
 * @create: 2018-04-30 23:31
 */
public class Path {
    private String type;
    private String service;
    private String address;
    private String port;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
