package hmrmi.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hmrmi.remote.archivos.Archivo;

public abstract class Util {
    public static Archivo readFile(File fileEntry, boolean esDescriptor) {
        Scanner inputFile;
        String linea;
        Archivo archivo = null;

        try {
            inputFile = new Scanner(fileEntry);
            if (esDescriptor) {
                linea = inputFile.nextLine();
                archivo = new Archivo(linea);
            } else {
                archivo = new Archivo(fileEntry.getName());
            }
            while (inputFile.hasNextLine()) {
                linea = inputFile.nextLine();
                archivo.addLinea(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return archivo;
    }
    
    public static List<Archivo> listFilesForFolder(final File folder, boolean esDescriptor) {
        List<Archivo> archivos = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, esDescriptor);
            } else {
                Archivo aux = readFile(fileEntry, esDescriptor);
                if (aux != null)
                    archivos.add(aux);
            }
        }

        return archivos;
    }

    public static void writeReCapitulative(Archivo recapitulado) {
        File file = new File("../res/fuente/" + recapitulado.getNombre());
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for ( String data : recapitulado.getLineas() )
                fr.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}