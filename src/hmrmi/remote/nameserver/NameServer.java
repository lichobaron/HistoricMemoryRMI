package hmrmi.remote.nameserver;

import java.rmi.*;
import java.rmi.server.*;

import java.util.List;
import java.util.ArrayList;

import hmrmi.remote.archivos.Archivo;
import hmrmi.remote.archivos.ArchivoInterface;

public class NameServer extends UnicastRemoteObject implements NameServerInterface {

    private List<Node> nodes;
    private boolean ready;

    public NameServer() throws RemoteException {
        this.nodes = new ArrayList<>();
        this.ready = false;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes){
        this.nodes = nodes;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void addNode(Node node){
        int pos = findNode(node);
        if(pos != -1){
            nodes.remove(pos);
        }
        this.nodes.add(node);
    }

    public int findNode(Node mNode){
        int i = 0;
        for(Node node : nodes){
            if(mNode.getIp().equals(node.getIp()) && mNode.getPort() == node.getPort()){
                return i;
            }
            i++;
        }
        return -1;
    }
}