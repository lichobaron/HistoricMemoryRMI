package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;

public class Servidor extends Thread{

    private String ip;
    private String port;

    Servidor(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
    }
}