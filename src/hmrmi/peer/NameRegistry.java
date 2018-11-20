package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;

import java.rmi.registry.*;

import hmrmi.remote.nameserver.NameServer;
import hmrmi.remote.nameserver.Node;

public class NameRegistry{

    public static void main(String[] args) {

        if (args.length!=3) {
            System.err.println("Uso: NameRegistry ipRegistro numPuertoRegistro numClientes");
            return;
        }
        try {
            /*if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }*/
            System.out.println("------------------Registry ejecutandose------------------");
            String ip = args[0];
            String port = args[1];
            
            LocateRegistry.createRegistry(Integer.parseInt(port));
            Registry mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            NameServer nameServer = new NameServer();
            mRegistry.rebind("rmi://"+ip +":" + port + "/nameServer", nameServer);
            System.out.println("Registry disponible en rmi://"+ip +":" + port + "/nameServer");
            
            int numClientes = Integer.parseInt(args[2]);
            while(numClientes != nameServer.getNodes().size()){
                System.out.println(" - El servidor de nombres no esta listo para arrancar");
                Thread.sleep(3000);
            }
            Thread.sleep(6000);
            nameServer.setReady(true);
            System.out.println("---> El servidor de nombres ha iniciado la ejecucion");

        } catch (Exception e) {
            System.out.println(e);
        }    
    }
}