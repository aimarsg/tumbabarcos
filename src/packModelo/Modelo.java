package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class Modelo extends Observable {

	private Flota[] flotas;
	private static Modelo miModelo;

	public void jugar() {
		// TODO - implement Modelo.jugar
		throw new UnsupportedOperationException();
	}

	private void inicializar() {
		// TODO - implement Modelo.inicializar
		throw new UnsupportedOperationException();
	}

	public static Modelo getModelo() {
		if (Modelo.miModelo==null) {
			Modelo.miModelo = new Modelo();
		}
		return Modelo.miModelo;
	}

	private Modelo() {
		this.flotas = new Flota[2];
		for (int i=0; i<2; i++) {
			flotas[i] = new Flota();
		}
	}

	private void operation() {
		// TODO - implement Modelo.operation
		throw new UnsupportedOperationException();
	}
	public Flota getFlotaUsuario() {
		return flotas[0];
	}
	public Flota getFlotaOrdenador() {
		return flotas[1];
	}
}