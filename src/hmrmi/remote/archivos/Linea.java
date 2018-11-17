package hmrmi.remote.archivos;

import java.rmi.*;
import java.rmi.server.*;

public class Linea implements LineaInterface {
    private String archivo;
    private int indice;
    private String tema;

    public Linea(String archivo, int indice, String tema) {
        this.archivo = archivo;
        this.indice = indice;
        this.tema = tema;
    }

    public String getArchivo() {
        return this.archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public int getIndice() {
        return this.indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getTema() {
        return this.tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}