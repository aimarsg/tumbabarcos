package packModelo;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public abstract class Barco {

	private ArrayList<Casilla> casillasOcupadas;
	private int contLibres; //este parametro indica cuantas casillas quedan sin tocar
							// inicialmente su valor sera la longitud del barco
	private boolean hundido;
	private String nombre;
	private Escudo escudo = null;

	public Barco(int numLibres, boolean hundido, String pNom) {
		this.contLibres = numLibres;
		this.hundido = hundido;
		this.nombre = pNom;
		this.casillasOcupadas = new ArrayList<>();
		
		
	}
	/*public ArrayList<Casilla> getCasillas(){
		return this.casillasOcupadas;
	}*/
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	 * 
	 * @param Coordenada
	 */
	public boolean tieneCordenada(Coordenada pCord) {
		return this.casillasOcupadas.stream().anyMatch(c -> c.getPosicion().equals(pCord));
	}
	public ArrayList<Casilla> cambiarAHundido() {
		this.contLibres = 0;
		this.casillasOcupadas.stream().forEach(c -> c.cambiarEstado("Hundido"));
		this.hundido = true;
		return this.casillasOcupadas;
		
	}
	public void reparar() {
		this.contLibres = 0;
		this.casillasOcupadas.stream().forEach(c -> c.cambiarEstado("Barco"));
		this.hundido = true;
		
		
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

	/*public void esTocado() {
		// TODO - implement Barco.esTocado
		throw new UnsupportedOperationException();
	}*/
	public abstract boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Tablero pTablero);
	
	protected ArrayList<Casilla> getCasillasOcupadas(){
		return this.casillasOcupadas;
	}
	public boolean restarCasilla() {
		this.contLibres--;
		return this.contLibres==0;
	}
	
	public void colocarEscudo(Escudo pEscudo) {
		this.escudo = pEscudo;
		this.casillasOcupadas.stream().forEach(c -> c.cambiarEstado("Escudo"));
	}
	
	public boolean tieneEscudo() {
		return this.escudo != null;
	}
	

	public boolean danarEscudo(String pArma) {
		//false cuando sigue el escudo
		//true cuando se rompe el escudo
		int imp = this.escudo.danarEscudo(pArma);
		if (imp == 0) {
			this.escudo = null;
			this.casillasOcupadas.stream().forEach(c -> c.cambiarEstado("Barco"));
			return true;
		}else return false;
	}
	
}