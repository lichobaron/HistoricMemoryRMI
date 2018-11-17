package hmrmi.remote.suscripciones;

public class Tema {
	private String nombre;
	private String archivo;
	private int linea;

	Tema(String nombre, String archivo, int linea) {
		this.nombre = nombre;
		this.archivo = archivo;
		this.linea = linea;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public int getLinea() {
		return this.linea;
	}
}