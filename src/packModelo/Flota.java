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
		
	}

	public boolean jugarTurno() {
	
		return numBarcos==0;
	}
	
	public boolean jugarTurnoIA() {
	
		return numBarcos==0;
	}

	public void colocarBarcos(Coordenada pCord, String pTipo, boolean horizontal) {	
		Barco nuevo =this.obtenerBarco(pTipo);
		if (pCord!=null) {
			if (nuevo!=null) {
				if (nuevo.colocarBarco(pCord, !horizontal, tablero)){ //horizontal invertido porque la x y la y van al reves
					listaBarcos.remove(nuevo);
					barcosColocados.add(nuevo);
					//notifyObservers
					System.out.println("se coloca");
					setChanged();
					notifyObservers(new Object[] {nuevo.getCasillas(), true});
					setChanged();
					notifyObservers("Barco "+pTipo+" colocado.");
					//notifyObservers(nuevo.getCasillas());
					
				}else {
					System.out.println("El barco no se puede colocar en esa posicion");
					setChanged();
					notifyObservers("El barco no se puede colocar en esa posicion");
				}
			}else {//no existe el barco 
				System.out.println("No existe el barco");
				setChanged();
				notifyObservers("No queda ningun barco del tipo "+pTipo+" por colocar. Selecciona otro.");
			}
		}else {//no se ha seleccionado ninguna casilla
			System.out.println("No se ha seleccionado ninguna casilla");
			setChanged();
			notifyObservers("No se ha seleccionado ninguna casilla.");
			
		}
	}

	public boolean disparar(Coordenada pCoordenada){
		boolean tocado= false;
		if (pCoordenada!=null) {
			int x = pCoordenada.getX();
			int y = pCoordenada.getY();
			Casilla casillaActual = tablero.getCasilla(x, y);
			
			String estado=casillaActual.comprobarEstado();
			
			if (estado.equals("Disparado")||estado.equals("Tocado")||estado.equals("Hundido")) {
				setChanged();
				notifyObservers("Has disparado a una casilla ya disparada");
			}else{
				
				if(estado.equals("Barco")){
					casillaActual.cambiarEstado("Tocado");
					setChanged();
					notifyObservers("Tocado!");
					System.out.println("se esta buscando el barco de coordenadas x" + x+ " y "+y);
					Barco b = buscarBarco(pCoordenada);
					System.out.println(b.getNombre());
					System.out.println(b.tieneCordenada(pCoordenada));
					boolean hundido = b.restarCasilla();
					if (hundido){
						ArrayList<Casilla> hundidos=b.cambiarAHundido();
						setChanged();
						notifyObservers(new Object[] {hundidos, false});
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
				notifyObservers(casillaActual);				
			}
		}
		else{
			setChanged();
			notifyObservers("Selecciona una casilla");
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

	 public String obtenerEstadoCasilla(int X, int Y) {
		 return tablero.getCasilla(X,Y).comprobarEstado();
	 }
	
	
	private Iterator<Barco> obtIteradorBarcos() {
		return this.listaBarcos.iterator();
	}
	private Iterator<Barco> getIteradorColocados(){
		return this.barcosColocados.iterator();
	}
	
	
	
	
	//metodos para la flota del ordenador
	public void colocarBarcosOrdenador() {
		this.inicializarFlota();
		Random randomizer = new Random();
		int col = 0;
		int fil = 0;
		boolean colocado;
		boolean horizontal;
	
		for (Barco b : this.listaBarcos) {
			colocado = false;
			while (!colocado) {
				col= randomizer.nextInt(9);
				fil = randomizer.nextInt(9);
				horizontal = randomizer.nextBoolean();
				colocado = b.colocarBarco(new Coordenada(col, fil), horizontal, tablero);
				barcosColocados.add(b);				
			}
			//listaBarcos = new ArrayList<>();
			System.out.println(b.getNombre()+" colocado en fila "+(col +1)+", col "+(1+fil)+" ");
			System.out.println();
			//setChanged();
			//notifyObservers(new Object[] {b.getCasillas(), false});
			
		}
	}
	
}