package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;

public class Cliente extends Thread{
    private String ip;
    private String port;

    Cliente(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
    }
}