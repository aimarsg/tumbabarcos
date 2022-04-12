package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

public abstract class Jugador extends Observable{

	protected Tablero tablero;
	protected ListaArmas armamento;
	protected Double presupuesto;
	protected Flota flota;
	protected boolean disparado;

	public Jugador() {
		tablero= new Tablero(10,10);
		armamento= new ListaArmas();
		presupuesto= 500.00;
		flota = new Flota();
	}
	
	
	//public abstract void colocarBarcos(Coordenada pCord, String pTipo, boolean horizontal);
	
	public abstract boolean jugarTurno();
	
	public  void inicializarFlota() {
		this.inicializarFlota();
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("PortaAviones"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Submarino"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Submarino"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Destructor"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Destructor"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Destructor"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Fragata")); 
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Fragata"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Fragata"));
		flota.anadirBarco(BarcoFactory.getBarcoFactory().crearBarco("Fragata"));
		}
	


	public String obtenerEstadoCasilla(int X, int Y) {
		return tablero.getCasilla(X,Y).comprobarEstado();
	}

	public abstract boolean disparar(String arma, Coordenada coord);
	
	public abstract boolean recibirDisparo(Coordenada pCoordenada, String arma);
		/*
		
	private Barco buscarBarco(Coordenada pcord ) {
		
		boolean enc = false;
		Barco b=null;
		Iterator<Barco> it = this.getIteradorColocados();
		//barcosColocados.stream().allMatch(b -> b.tieneCordenada(pcord))S;
		
		while (!enc && it.hasNext()){
			b=it.next();
			enc=b.tieneCordenada(pcord);
		}
		if (!enc) {return null;}
		return b;
	}	
	

	public void setDisparadoUsuario() {
		this.disparado = true;
	}
	*/
}