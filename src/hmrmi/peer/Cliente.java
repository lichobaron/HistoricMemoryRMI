package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hmrmi.remote.archivos.Archivo;
import hmrmi.util.Util;

public class Cliente extends Thread{
    private String ip;
    private String port;

    Cliente(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());

            final File folder = new File("../sources/descriptores/");
            List<Archivo> archivos = Util.listFilesForFolder(folder);
            for( Archivo archivo : archivos) {
                System.out.println(archivo.getNombre());
                System.out.println(archivo.getLinea());
            }
        }
    }
}