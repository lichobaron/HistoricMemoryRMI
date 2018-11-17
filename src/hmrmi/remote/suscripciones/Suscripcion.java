package hmrmi.remote.suscripciones;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class Suscripcion implements SuscripcionInterface {
	private List<Tema> temas;

	Suscripcion() throws RemoteException {
		this.super();
		temas = new ArrayList<>();
	}

	public List<Tema> getTemas() {
		return this.temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}

	public void addTema(Tema tema) {
		this.temas.add(tema);
	}
}