package hmrmi.peer;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

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
            String file = "a.txt";
            List<String> urls = new ArrayList<String>();
            urls.add("rmi://127.0.0.1:80/aaa/a.txt");
            urls.add("rmi://127.0.0.1:80/bbb/a.txt");
            urls.add("rmi://127.0.0.1:80/ccc/b.txt");
            urls.add("rmi://127.0.0.1:80/ddd/a.txt");
            matchFiles(urls, file);
        }
    }

    private List<String> matchFiles (List<String> urls, String file) {
        List<String> matches = new ArrayList<String>();
        for (String url : urls) {
            String[] div = url.split("/");
            if (div[div.length - 1].equals(file)){
                matches.add(url);
                System.out.println(div[div.length - 1]);
            }
        }
        return matches;
    }
}