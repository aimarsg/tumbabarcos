package packModelo;

import java.util.ArrayList;

public abstract class Barco {

	private ArrayList<Casilla> casillasOcupadas;
	private int contLibres;
	private boolean hundido;
	private String nombre;

	public Barco() {
		// TODO - implement Barco.Barco
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Coordenada
	 */
	public boolean tieneCordenada(Coordenada pCord) {
		// TODO - implement Barco.tieneCordenada
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Double
	 */
	public double reparBarco(Double pSaldo) {
		// TODO - implement Barco.reparBarco
		throw new UnsupportedOperationException();
	}

	public void esTocado() {
		// TODO - implement Barco.esTocado
		throw new UnsupportedOperationException();
	}
	public abstract void colocarBarco();

}