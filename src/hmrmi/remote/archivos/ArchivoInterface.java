package hmrmi.remote.archivos;

import java.rmi.*;
import java.rmi.server.*;

public interface ArchivoInterface extends Remote {
    public String getLinea() throws RemoteException;
    public Archivo hello() throws RemoteException;
    public String getNombre() throws RemoteException;
}