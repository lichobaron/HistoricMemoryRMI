package hmrmi.remote.nameserver;

import java.rmi.*;
import java.rmi.server.*;

import java.util.List;

public interface NodeInterface extends Remote {
    public String infoNode() throws RemoteException;
}