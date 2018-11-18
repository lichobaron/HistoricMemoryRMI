package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import hmrmi.remote.archivos.Archivo;
import hmrmi.remote.archivos.ArchivoInterface;
import hmrmi.remote.nameserver.*;
import hmrmi.util.Util;

public class Cliente extends Thread{
    private String ip;
    private String port;
    private Registry mRegistry; 
    private List<Archivo> descriptores;

    Cliente(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){

        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/

        File folder = new File("../res/descriptores");
        descriptores = Util.listFilesForFolder(folder,true);

        try {
            mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            String[] urls = mRegistry.list();
            for (String url : urls) {
                System.out.println(url);
            }
            NameServerInterface nsi = (NameServerInterface) mRegistry.lookup("rmi://"+ip+":"+port+"/nameServer");
            List<Node> nodes = nsi.getNodes();
            for(Node node : nodes){
                System.out.println(node.infoNode());
            }
            

            /*mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            Thread.sleep(5000);
            System.out.println("------------------Cliente ejecutandose------------------");

            String[] urls = mRegistry.list();
            List<String> urlsList = Arrays.asList(urls);

            for (Archivo descriptor : descriptores) {
                System.out.println("---* "+descriptor.getNombre());
                Archivo compilado = new Archivo(descriptor.getNombre());
                for (String linea : descriptor.getLineas()) {
                    //System.out.println("----+"+linea);
                    List<String> filterUrls = matchFiles(urlsList, linea);
                    //System.out.println("Url Filtrado: " + filterUrls.isEmpty());
                    boolean success = false;
                    while(!success){
                        try {
                            String testUrl = filterUrls.get((int) (Math.random() * filterUrls.size()));
                            //System.out.println("-----< "+testUrl);
                            ArchivoInterface arch = (ArchivoInterface) mRegistry.lookup(testUrl);
                            String randomLine = arch.getLinea();
                            compilado.addLinea(randomLine/*descriptor.getNombre().concat(arch.getLinea()));
                            System.out.println("Se ha obtenido la linea " + randomLine+" de "+ testUrl);
                            success = true;
                        } catch (Exception e) {
                            if(filterUrls.size() <= 1){
                                success = true;
                                compilado.addLinea("No hay archivos disponibles de "+ linea);
                            }
                        }
                    }   
                }
                Util.writeReCapitulative(compilado);
                System.out.println("------------------Se  ha escrito compilado "+ descriptor.getNombre()+"------------------");
            }*/
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    private List<String> matchFiles (List<String> urls, String file) {
        List<String> matches = new ArrayList<String>();
        for (String url : urls) {
            String[] div = url.split("/");
            if (div[div.length - 1].equals(file)){
                matches.add(url);
                //System.out.println(div[div.length - 1]);
            }
        }
        return matches;
    }
}