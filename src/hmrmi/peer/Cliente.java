package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import hmrmi.remote.archivos.ArchivoInterface;

public class Cliente extends Thread{
    private String ip;
    private String port;
    private Registry mRegistry; 

    Cliente(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }

        try {
            mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            Thread.sleep(4000);
            System.out.println("//" + ip + ":" + port);
            String[] urls = mRegistry.list();
            for(String url : urls){
                try {
                    ArchivoInterface arch = (ArchivoInterface) mRegistry.lookup(url);
                    System.out.println(arch.getLinea());
                } catch (Exception e) {
                    mRegistry.unbind(url);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}