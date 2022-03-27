package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

public class Flota extends Observable{

	private Tablero tablero;
	private ListaArmas armamento;
	private Double presupuesto;
	private int numBarcos;
	private ArrayList<Barco> listaBarcos;
	private ArrayList<Barco> barcosColocados;
	
	public Flota() {
		tablero= new Tablero(10,10);
		armamento= new ListaArmas();
		presupuesto= 500.00;
		listaBarcos= new ArrayList<Barco>();
		barcosColocados= new ArrayList<Barco>();
		this.inicializarFlota();
	}

	public void jugarTurno() {
		// TODO - implement Flota.jugarTurno
		throw new UnsupportedOperationException();
	}

	public void colocarBarcos(Coordenada pCord, String pTipo, boolean horizontal) {	
		Barco nuevo =this.obtenerBarco(pTipo);
		if (nuevo!=null) {
				if (nuevo.colocarBarco(pCord, !horizontal, tablero)){ //horizontal invertido porque la x y la y van al reves
					listaBarcos.remove(nuevo);
					barcosColocados.add(nuevo);
					//notifyObservers
					System.out.println("se coloca");
					setChanged();
					notifyObservers(nuevo.getCasillas());
					
				}else {
					System.out.println("El barco no se puede colocar en esa posicion");
				}
		}else {
			//no existe el barco 
			System.out.println("No existe el barco");
			setChanged();
			notifyObservers("No queda ningun barco del tipo "+pTipo+" por colocar. Selecciona otro.");
		}
	}

	public boolean disparar(Coordenada pCoordenada){
		boolean tocado= false;
		
		Casilla casillaActual = tablero.getCasilla(pCoordenada.getX(),pCoordenada.getY());
		
		String estado=casillaActual.comprobarEstado();
		
		if (estado.equals("Disparado")||estado.equals("Tocado")) {
			setChanged();
			notifyObservers("Has disparado a una casilla ya disparada");
		}else{
			
		if(estado.equals("Barco")){
			casillaActual.cambiarEstado("Tocado");
			setChanged();
			notifyObservers(pCoordenada);
		}else{
			casillaActual.cambiarEstado("Disparado");
			}
		}
		
		return tocado; 
	}
	public void inicializarFlota() {
		
		tablero.inicializarTablero();
		
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("PortaAviones"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Submarino"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Submarino"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Destructor"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Destructor"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Destructor"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Fragata")); 
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Fragata"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Fragata"));
		listaBarcos.add(BarcoFactory.getBarcoFactory().crearBarco("Fragata"));
	}

	/**
	 * 
	 * @param Coordenada
	 */
	private Barco buscarBarco(Coordenada pcord ) {
		int x = pcord.getX();
		int y = pcord.getY();
		
		return null;
	}
	public Barco obtenerBarco(String pTipo){
		Iterator<Barco> it= this.obtIterador();
		Barco nuevo= null;
		boolean enc=false;
		while(it.hasNext()&& !enc){
			nuevo= it.next();
			if(nuevo.esBarco(pTipo)){
				enc=true;
			}
		}
		if(!enc){
			nuevo=null;
		}
		return nuevo;
	}

	 public String obtenerEstadoCasilla(int X, int Y) {
		 return tablero.getCasilla(X,Y).comprobarEstado();
	 }
	
	
	private Iterator<Barco> obtIterador() {
		return this.listaBarcos.iterator();
	}

	
	
	
	
	//metodos para la flota del ordenador
	public void colocarBarcosOrdenador() {
		this.inicializarFlota();
		Random randomizer = new Random();
		int col;
		int fil;
		boolean colocado;
		boolean horizontal;
		for (Barco b : this.listaBarcos) {
			colocado = false;
			while (!colocado) {
				col= randomizer.nextInt(9);
				fil = randomizer.nextInt(9);
				horizontal = randomizer.nextBoolean();
				colocado = b.colocarBarco(new Coordenada(col, fil), horizontal, tablero);
				colocado = true;
			}
			setChanged();
			notifyObservers(b.getCasillas());
		}
	}
}