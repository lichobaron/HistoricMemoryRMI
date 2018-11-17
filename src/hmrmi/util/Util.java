package hmrmi.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hmrmi.remote.archivos.Archivo;

public abstract class Util {
    public static Archivo readFile(File fileEntry) {
        Scanner inputFile;
        String linea;
        Archivo archivo = null;

        try {
            archivo = new Archivo(fileEntry.getName());
            inputFile = new Scanner(fileEntry);
            while (inputFile.hasNextLine()) {
                linea = inputFile.nextLine();
                archivo.addLinea(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return archivo;
    }
    
    public static List<Archivo> listFilesForFolder(final File folder) {
        List<Archivo> archivos = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                Archivo aux = readFile(fileEntry);
                if (aux != null)
                    archivos.add(aux);
            }
        }

        return archivos;
    }
}