package hmrmi.remote.nameserver;

import java.io.Serializable;

public class Node implements  Serializable{

    private String ip;
    private int port;

    public Node(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }
    
    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "{" +
            " ip='" + getIp() + "'" +
            ", port='" + getPort() + "'" +
            "}";
    }
}