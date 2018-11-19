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
import hmrmi.remote.nameserver.*;
import hmrmi.util.*;

public class Servidor extends Thread{

    private String ip;
    private String port;
    private String mIp;
    private String mPort;
    private Registry mRegistry;
    private Registry serverRegistry;
    private List<Archivo> archivos;
    private List<String> temas;

    Servidor(String ip, String port, String mIp, String mPort){
        this.ip = ip;
        this.port = port;
        this.mIp = mIp;
        this.mPort = mPort;
    }

    public void run(){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        final File folder = new File("../res/fuente/");
        archivos = Util.listFilesForFolder(folder, false);
        System.out.println("------------------Lectura de fuentes terminada------------------\n");
        System.out.println("------------------Servidor ejecutandose------------------");            
        try {
            serverRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            NameServerInterface nsi = (NameServerInterface) serverRegistry.lookup("rmi://"+ip+":"+port+"/nameServer");
            Node node = new Node(mIp, Integer.parseInt(mPort));
            temas = new ArrayList<>();
            mRegistry = LocateRegistry.getRegistry(mIp, Integer.parseInt(mPort));
            registerFiles();
            node.setTemas(temas);
            nsi.addNode(node);
            System.out.println("Servidor " +mIp+":"+mPort+" registrado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);            
        }
    }

    private void registerFiles() throws Exception{
        for (Archivo arch : archivos) {
            mRegistry.rebind("rmi://"+ mIp +":" + mPort + "/files/" +arch.getNombre(), arch);
            System.out.println(" - Se ha compartido rmi://"+ mIp +":" + mPort + "/files/" +arch.getNombre());
            temas.add(arch.getNombre());
        }
    }
}