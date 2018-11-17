package hmrmi.remote.archivos;

import java.rmi.*;
import java.rmi.server.*;

interface ArchivoInterface extends Remote {
    public String getLinea();
}