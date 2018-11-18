package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import hmrmi.remote.nameserver.NameServer;
import hmrmi.remote.nameserver.Node;

public class NameRegistry{

    public static void main(String[] args) {

        if (args.length!=2) {
            System.err.println("Uso: NameRegistry ipRegistro numPuertoRegistro");
            return;
        }
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            System.out.println("------------------Registry ejecutandose------------------");
            String ip = args[0];
            String port = args[1];
            Registry mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
            NameServer nameServer = new NameServer();
            nameServer.addNode(new Node(ip, Integer.parseInt(port)));
            System.out.println("rmi://"+ip +":" + port + "/nameServer");
            mRegistry.bind("rmi://"+ip +":" + port + "/nameServer", nameServer);                
        } catch (Exception e) {
            System.out.println(e);
        }    
    }
}