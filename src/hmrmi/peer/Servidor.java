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
import hmrmi.remote.binder.*;
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
        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        final File folder = new File("../res/fuente/");
        archivos = Util.listFilesForFolder(folder, false);

        System.out.println("------------------Servidor ejecutandose------------------");

        serverID = generateString();
              

        try {
            mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            Binder binder = new Binder(ip, port);
            mRegistry.rebind(ip +":" + port + "/binder", binder);  
            //System.out.println(mRegistry);
            registerFiles();
        } catch (Exception e) {
            try {
                registerFiles();    
            } catch (Exception e1) {
                System.out.println("Esta aqui e1");
                e1.printStackTrace();
                System.out.println(e1);
            }
            
        }
    }

    private String generateString() {
        return UUID.randomUUID().toString();
    }

    private void registerFiles() throws Exception{
        System.out.println("Esta aqui");
        String[] prueba = mRegistry.list();
        BinderInterface binderInterface = (BinderInterface) this.mRegistry.lookup( ip +":" + port + "/binder");
        for (Archivo arch : archivos) {
            //mRegistry.rebind("rmi://"+ ip +":" + port + "/" +serverID + "/" +arch.getNombre(), arch);
            binderInterface.bind(arch, this.serverID);
            System.out.println("Se ha compartido rmi://"+ ip +":" + port + "/" +serverID + "/" +arch.getNombre());
        }
    }
}