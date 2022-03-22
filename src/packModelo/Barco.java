package packModelo;

import java.util.ArrayList;

public abstract class Barco {

	private ArrayList<Casilla> casillasOcupadas;
	private int contLibres; //este parametro indica cuantas casillas quedan sin tocar
							// inicialmente su valor sera la longitud del barco
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
	public abstract boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] pTablero);

}