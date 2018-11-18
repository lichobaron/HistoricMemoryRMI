package hmrmi.remote.binder;

import java.rmi.*;
import java.rmi.server.*;

import hmrmi.remote.archivos.Archivo;
import hmrmi.remote.archivos.ArchivoInterface;

import java.rmi.registry.*;

public interface BinderInterface extends Remote {
    public void bind(ArchivoInterface arch, String serverID) throws RemoteException;
}