package hmrmi.remote.binder;

import java.rmi.*;
import java.rmi.server.*;

import hmrmi.remote.archivos.Archivo;

import java.rmi.registry.*;

public interface BinderInterface extends Remote {
    public void bind(Archivo arch, String serverID) throws RemoteException;
}