package hmrmi.remote.nameserver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements  Serializable{

    private String ip;
    private int port;
    private List<String> temas;

    public Node(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.temas = new ArrayList<>();
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

    public List<String> getTemas() {
        return this.temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    @Override
    public String toString() {
        return "{" +
            " ip='" + getIp() + "'" +
            ", port='" + getPort() + "'" +
            "}";
    }
}