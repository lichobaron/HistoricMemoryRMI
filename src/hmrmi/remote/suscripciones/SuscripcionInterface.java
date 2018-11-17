package hmrmi.remote.suscripciones;

import java.rmi.*;
import java.rmi.server.*;

public interface SuscripcionInferface extends Remote {
    public List<Tema> getTemas();
	public void setTemas(List<Tema> temas);
	public void addTema(Tema tema);
}