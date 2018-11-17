package hmrmi.peer;

public class Peer{

    public static void main(String[] args) {

        if (args.length!=2) {
            System.err.println("Uso: Peer ipRegistro numPuertoRegistro");
            return;
        }

        Cliente cliente;
        Servidor servidor;
        
        servidor = new Servidor(args[0],args[1]);
        cliente = new Cliente(args[0],args[1]);

        servidor.start();
        cliente.start();
    
    }
}