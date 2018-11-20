package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class Peer{

    public static void main(String[] args) {

        if (args.length!=4) {
            System.err.println("Uso: Peer ipRegistro numPuertoRegistro ipCliente numPuertoCliente");
            return;
        }
        try {
            LocateRegistry.createRegistry(Integer.parseInt(args[3]));
            
            Cliente cliente;
            Servidor servidor;
            
            servidor = new Servidor(args[0],args[1],args[2],args[3]);
            cliente = new Cliente(args[0],args[1],args[2],args[3]);
    
            servidor.start();
            cliente.start();
    
            servidor.join();
            cliente.join();
        } catch (Exception e) {
            System.out.println(e);
        }    
    }
}