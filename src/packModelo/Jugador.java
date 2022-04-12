package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

public abstract class Jugador extends Observable{

	private Tablero tablero;
	private ListaArmas armamento;
	private Double presupuesto;
	private Flota flota;
	protected boolean disparado;

	public Jugador() {
		tablero= new Tablero(10,10);
		armamento= new ListaArmas();
		presupuesto= 500.00;
		flota = new Flota();
	}
	
	protected Tablero getTablero() {
		return tablero;
	}
	
	protected Flota getFlota() {
		return flota;
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


	
	
	/*
	public boolean disparar(Coordenada pCoordenada,boolean ordenador, String arma){
		boolean disparado= true;
		if (pCoordenada!=null) {
			int x = pCoordenada.getX();
			int y = pCoordenada.getY();
			Casilla casillaActual = tablero.getCasilla(x, y);
			
			String estado=casillaActual.comprobarEstado();
			
			if (((estado.equals("Disparado")||estado.equals("Tocado")||estado.equals("Hundido")) && arma.equals("bomba")) ||
					((estado.equals("Disparado") || estado.equals("Tocado")) && arma.equals("misil"))) {
				disparado=false;
				setChanged();
				notifyObservers("Has disparado a una casilla ya disparada");
			}else{
				
				if(estado.equals("Barco")){
					boolean hundido;
					Barco b = buscarBarco(pCoordenada);
					if (arma.equals("bomba")) {
						casillaActual.cambiarEstado("Tocado");
						System.out.println("se esta buscando el barco de coordenadas x" + x+ " y "+y);
						
						setChanged();
						notifyObservers("El barco "+b.getNombre()+" ha sido tocado!");
						
						System.out.println(b.getNombre());
						System.out.println(b.tieneCordenada(pCoordenada));
						hundido = b.restarCasilla();
					}else { //el arma es un misil -> lo hunde entero
						hundido = true;
					}
					
					if (hundido){
						numBarcos--;
						ArrayList<Casilla> hundidos=b.cambiarAHundido();
						setChanged();
						if (ordenador){
							notifyObservers(new Object[] {hundidos, "HundirOrdenador"});
						}
						else{
							notifyObservers(new Object[] {hundidos, "HundirUsuario"});
						}
						
						setChanged();
						notifyObservers("El barco "+b.getNombre()+" se ha hundido!!");
					}
				}else{
					System.out.println("Disparado al agua");
					casillaActual.cambiarEstado("Disparado");
					setChanged();
					notifyObservers("agua :(");
				}
				setChanged();
				ArrayList<Casilla> casiLista = new ArrayList<>();
				casiLista.add(casillaActual);
				if (ordenador) {
					notifyObservers(new Object[] {casiLista, "DispararAOrdenador"});
				}else {
					notifyObservers(new Object[] {casiLista,"DispararAUsuario"});				
				}
			}
		}
		else{
			disparado=false;
			setChanged();
			notifyObservers("Selecciona una casilla");
		}
		return disparado; 
	}
	
	private Barco buscarBarco(Coordenada pcord ) {
		
		boolean enc = false;
		Barco b=null;
		Iterator<Barco> it = this.getIteradorColocados();
		//barcosColocados.stream().allMatch(b -> b.tieneCordenada(pcord));
		
		while (!enc && it.hasNext()){
			b=it.next();
			enc=b.tieneCordenada(pcord);
		}
		if (!enc) {return null;}
		return b;
	}
	public Barco obtenerBarco(String pTipo){
		Iterator<Barco> it= this.obtIteradorBarcos();
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

	
	

	
	

	public void setDisparadoUsuario() {
		this.disparado = true;
	}
	*/
}