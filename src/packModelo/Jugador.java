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
		//
		tablero.inicializarTablero();
		this.inicializarArmamento();
	}
	
	
	//public abstract void colocarBarcos(Coordenada pCord, String pTipo, boolean horizontal);
	
	public abstract boolean jugarTurno();
	
	public void inicializarArmamento(){
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Bomba"));
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Misil"));
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Misil"));
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Escudo"));
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Escudo"));
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Radar"));
		armamento.anadirArma(ArmaFactory.getArmaFactory().crearArma("Radar"));


	}

	public  void inicializarFlota() {
		
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
	
	public Arma eliminarArma(String pArma) {
		return this.armamento.eliminarArma(pArma);
	}

	public Arma buscarArma(String pArma){
		return this.armamento.buscarArma(pArma);
	}
	
	public boolean colocarEscudo(Coordenada pCoord, Escudo pEscudo) {
	
		boolean utilizado=false;
		int x = pCoord.getX();
		int y = pCoord.getY();
		Casilla c = this.tablero.getCasilla(x, y);
		if(c.comprobarEstado().equals("Barco")){
			Barco b = this.flota.buscarBarco(pCoord);
			if (!b.tieneEscudo()) {
				b.colocarEscudo(pEscudo);
				utilizado = true;
				this.mostrarEscudoColocado(pCoord);
			}else {return false;}
		}		
		return utilizado;
	}
	public abstract void mostrarEscudoColocado(Coordenada pCoordenada);		/*
		

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