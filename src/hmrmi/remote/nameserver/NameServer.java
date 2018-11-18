package hmrmi.remote.nameserver;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.util.List;
import java.util.ArrayList;

import hmrmi.remote.archivos.Archivo;
import hmrmi.remote.archivos.ArchivoInterface;

public class NameServer extends UnicastRemoteObject implements NameServerInterface {

    private List<Node> nodes;

    public NameServer() throws RemoteException {
        this.nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<Node> nodes){
        this.nodes = nodes;
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }
}