package hmrmi.remote.archivos;

import java.rmi.*;
import java.rmi.server.*;

import java.util.List;
import java.util.ArrayList;

public class Archivo extends UnicastRemoteObject implements ArchivoInterface {
    private String nombre;
    private List<String> lineas;

    public Archivo(String nombre) throws RemoteException {
        this.nombre = nombre;
        this.lineas = new ArrayList<>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getLineas() {
        return this.lineas;
    }

    public void setLineas(List<String> lineas) {
        this.lineas = lineas;
    }

    public String getLinea(){
        return lineas.get((int) (Math.random() * 8));
    }

    public void addLinea(String linea) {
        this.lineas.add(linea);
    }
}