package hmrmi.remote.archivos;

import java.rmi.*;
import java.rmi.server.*;

public interface LineaInterface extends Remote {
    public String getArchivo();
    public void setArchivo(String archivo);
    public int getIndice();
    public void setIndice(int indice);
    public String getTema();
    public void setTema(String tema);
}