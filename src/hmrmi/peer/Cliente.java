package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import hmrmi.remote.archivos.*;
import hmrmi.remote.nameserver.*;
import hmrmi.util.Util;

public class Cliente extends Thread{
    private String ip;
    private String port;
    private String mIp;
    private String mPort;
    private Registry serverRegistry;
    private Registry mRegistry; 
    private List<Archivo> descriptores;

    Cliente(String ip, String port, String mIp, String mPort){
        this.ip = ip;
        this.port = port;
        this.mIp = mIp;
        this.mPort = mPort;
    }

    public void run(){

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        File folder = new File("../res/descriptores");
        descriptores = Util.listFilesForFolder(folder,true);
        System.out.println("------------------Lectura de descriptores terminada------------------");

        try {
            serverRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));            
            NameServerInterface nsi = (NameServerInterface) serverRegistry.lookup("rmi://"+ip+":"+port+"/nameServer");
            while(!nsi.isReady()){
                Thread.sleep(3000);
            }
            System.out.println("------------------Cliente ejecutandose------------------");
            Thread.sleep(3000);
            List<Node> nodes = nsi.getNodes();
            List<String> listUrls = new ArrayList<>();
            for(Node node : nodes){
                //System.out.println(node.toString());
                for(String tema : node.getTemas()){
                    //System.out.println("rmi://"+node.getIp()+":"+node.getPort()+"/files/"+tema);
                    listUrls.add("rmi://"+node.getIp()+":"+node.getPort()+"/files/"+tema);
                }
            }
        
            for (Archivo descriptor : descriptores) {
                //System.out.println("---* "+descriptor.getNombre());
                Archivo compilado = new Archivo(descriptor.getNombre());
                for (String linea : descriptor.getLineas()) {
                    //System.out.println("----+"+linea);
                    List<String> filterUrls = Util.matchFiles(listUrls, linea);
                    //System.out.println("Url Filtrado: " + filterUrls.isEmpty());
                    boolean success = false;
                    while(!success){
                        try {
                            String testUrl = filterUrls.get((int) (Math.random() * filterUrls.size()));
                            List<String> netInfo = Util.getIpAndPort(testUrl);
                            //System.out.println("-----< "+testUrl);
                            mRegistry = LocateRegistry.getRegistry(netInfo.get(0), Integer.parseInt(netInfo.get(1)));
                            
                            ArchivoInterface arch = (ArchivoInterface) mRegistry.lookup(testUrl);
                            String randomLine = arch.getLinea();
                            compilado.addLinea(randomLine);
                            System.out.println(" - Se ha obtenido la linea " + randomLine+" de "+ testUrl);
                            success = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            if(filterUrls.size() <= 1){
                                //TODO: registry exception
                                success = true;
                                compilado.addLinea(" - No hay archivos disponibles de "+ linea);
                            }
                        }
                    }   
                }
                Util.writeReCapitulative(compilado);
                System.out.println("------------------Se  ha escrito compilado "+ descriptor.getNombre()+"------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}