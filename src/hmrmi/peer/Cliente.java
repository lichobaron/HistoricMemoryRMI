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

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        File folder = new File("../sources/descriptores");
        descriptores = Util.listFilesForFolder(folder);

        for (Archivo descriptor : descriptores) {
            System.out.println(descriptor.getNombre());
            System.out.println(descriptor.getLinea());
        }

        try {
            mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            Thread.sleep(2000);

            String[] urls = mRegistry.list();
            List<String> urlsList = Arrays.asList(urls);

            for (Archivo descriptor : descriptores) {
                for (String linea : descriptor.getLineas()) {
                    List<String> filterUrls = matchFiles(urlsList, linea);
                    boolean success = true;
                    while(!success){
                        String testUrl = filterUrls.get((int) (Math.random() * filterUrls.size()));
                        try {
                            ArchivoInterface arch = (ArchivoInterface) mRegistry.lookup(testUrl);
                            System.out.println("-----> Linea: " + arch.getLinea());
                            success = true;
                        } catch (Exception e) {
                            if(filterUrls.size() <= 1){
                                success = true;
                                System.out.println("No hay archivos disponibles de "+ linea);
                            }
                        }
                    }
                }
            }
            
            for(String url : filterUrls){
                try {
                    ArchivoInterface arch = (ArchivoInterface) mRegistry.lookup(url);
                    System.out.println("-----> Linea: " + arch.getLinea());
                } catch (Exception e) {
                    mRegistry.unbind(url);
                }
            }
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