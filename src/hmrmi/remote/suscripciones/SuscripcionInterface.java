package hmrmi.remote.suscripciones;

import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

interface SuscripcionInterface extends Remote {
    public List<Tema> getTemas();
	public void setTemas(List<Tema> temas);
	public void addTema(Tema tema);
}