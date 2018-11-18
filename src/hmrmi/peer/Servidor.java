package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import hmrmi.remote.archivos.*;
import hmrmi.util.*;

public class Servidor extends Thread{

    private String ip;
    private String port;
    private String serverID;
    private Registry mRegistry;
    private List<Archivo> archivos;

    Servidor(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());

            final File folder = new File("../sources/fuente/");
            archivos = Util.listFilesForFolder(folder);
        }

        System.out.println("Ejecutando servidor");

        serverID = generateString();

        try {
            mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            registerFiles();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }
    }

    private String generateString() {
        return UUID.randomUUID().toString();
    }

    private void registerFiles() throws Exception{
        for (Archivo arch : archivos) {
            mRegistry.rebind("rmi://"+ ip +":" + port + "/" +serverID + "/" +arch.getNombre(), arch);
        }
    }
}