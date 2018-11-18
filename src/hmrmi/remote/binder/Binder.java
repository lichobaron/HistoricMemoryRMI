package hmrmi.remote.binder;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import java.util.List;

import hmrmi.remote.archivos.Archivo;
import hmrmi.remote.archivos.ArchivoInterface;

import java.util.ArrayList;

public class Binder extends UnicastRemoteObject implements BinderInterface {

    private Registry mRegistry;
    private String ip;
    private String port;

    public Binder(String ip, String port) throws RemoteException {
        mRegistry = LocateRegistry.getRegistry(ip, Integer.parseInt(port));
        this.ip = ip;
        this.port = port;
    }

    public void bind(ArchivoInterface arch, String serverID) {
        try {
            mRegistry.rebind("rmi://"+ ip +":" + port + "/" +serverID + "/" +arch.getNombre(), arch);
            System.out.println("Se ha compartido rmi://"+ ip +":" + port + "/" +serverID + "/" +arch.getNombre());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}