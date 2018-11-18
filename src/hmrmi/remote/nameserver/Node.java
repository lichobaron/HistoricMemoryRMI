package hmrmi.remote.nameserver;

import java.rmi.*;
import java.rmi.server.*;

public class Node extends UnicastRemoteObject implements NodeInterface{

    private String ip;
    private int port;

    public Node(String ip, int port) throws RemoteException{
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

    public String infoNode() {
        return "{" +
            " ip='" + getIp() + "'" +
            ", port='" + getPort() + "'" +
            "}";
    }
}