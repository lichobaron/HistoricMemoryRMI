package hmrmi.remote.suscripciones;

import java.rmi.server.RemoteObject;

public class Tema extends Remote {
	private String nombre;
	private String archivo;
	private int linea;

	Tema(String nombre, String archivo, int linea) throws RemoteObject {
		this.nombre = nombre;
		this.archivo = archivo;
		this.linea = linea;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public int getLinea() {
		return this.linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}
}