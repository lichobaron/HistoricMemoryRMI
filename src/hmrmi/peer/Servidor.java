package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import hmrmi.remote.archivos.*;

public class Servidor extends Thread{

    private String ip;
    private String port;
    private String serverID;
    private Registry mRegistry; 

    Servidor(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        System.out.println("Ejecutando servidor");

        serverID = generateString();

        List<Archivo> archivos = new ArrayList<>();
        List<String> lineas = new ArrayList<>();
        lineas.add("linea - 1");
        lineas.add("linea - 2");
        lineas.add("linea - 3");
        lineas.add("linea - 4");
        lineas.add("linea - 5");
        lineas.add("linea - 6");
        lineas.add("linea - 7");
        lineas.add("linea - 8");
        Archivo archivo1 = new Archivo("arch1");
        archivo1.setLineas(lineas);
        archivos.add(archivo1);
        Archivo archivo2 = new Archivo("arch2");
        archivo2.setLineas(lineas);
        archivos.add(archivo2);
        Archivo archivo3 = new Archivo("arch3");
        archivo3.setLineas(lineas);
        archivos.add(archivo3);
        Archivo archivo4 = new Archivo("arch4");
        archivo4.setLineas(lineas);
        archivos.add(archivo4);
        
        try {
            mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            registerFiles(archivos);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String generateString() {
        return UUID.randomUUID().toString();
    }

    private void registerFiles(List<Archivo> archivos) throws Exception{
        for (Archivo arch : archivos) {
            mRegistry.rebind("rmi://"+ ip +":" + port + "/" +serverID + "/" +arch.getNombre(), arch);
        }
    }
}