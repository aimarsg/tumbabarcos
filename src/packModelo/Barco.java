package packModelo;

import java.util.ArrayList;

public abstract class Barco {

	private ArrayList<Casilla> casillasOcupadas;
	private int contLibres; //este parametro indica cuantas casillas quedan sin tocar
							// inicialmente su valor sera la longitud del barco
	private boolean hundido;
	private String nombre;

	public Barco(int numLibres, boolean hundido, String pNom) {
		this.contLibres = numLibres;
		this.hundido = hundido;
		this.nombre = pNom;
		this.casillasOcupadas = new ArrayList<>();
		
	}
	public ArrayList<Casilla> getCasillas(){
		return this.casillasOcupadas;
	}
	public String getNombre() {
		return this.nombre;
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
	public boolean esBarco(String pTipo) {
		return nombre.equals(pTipo);
	}

	public void esTocado() {
		// TODO - implement Barco.esTocado
		throw new UnsupportedOperationException();
	}
	public abstract boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Tablero pTablero);
	
	protected ArrayList<Casilla> getCasillasOcupadas(){
		return this.casillasOcupadas;
	}
	
}