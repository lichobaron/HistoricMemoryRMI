package hmrmi.remote.archivos;

import java.rmi.*;
import java.rmi.server.*;

public interface ArchivoInterface extends Remote {
    public String getLinea() throws RemoteException;
}