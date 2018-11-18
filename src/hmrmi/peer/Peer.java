package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;

public class Peer{

    public static void main(String[] args) {

        if (args.length!=2) {
            System.err.println("Uso: Peer ipRegistro numPuertoRegistro");
            return;
        }
        try {

            Cliente cliente;
            Servidor servidor;
            
            servidor = new Servidor(args[0],args[1]);
            cliente = new Cliente(args[0],args[1]);
    
            servidor.start();
            cliente.start();
    
            servidor.join();
            cliente.join();
        } catch (Exception e) {
            System.out.println(e);
        }    
    }
}