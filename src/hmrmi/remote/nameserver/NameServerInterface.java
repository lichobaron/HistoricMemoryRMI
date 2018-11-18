package hmrmi.remote.nameserver;

import java.rmi.*;
import java.rmi.server.*;

import java.util.List;

public interface NameServerInterface extends Remote {
    public List<Node> getNodes() throws RemoteException;
    public void addNode(Node node) throws RemoteException;
}