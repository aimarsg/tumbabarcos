package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Flota {

	private Casilla[][] tablero;
	private ListaArmas armamento;
	private Double presupuesto;
	private int numBarcos;
	private ArrayList<Barco> listaBarcos;
	private ArrayList<Barco> barcosColocados;
	
	public Flota() {
		// TODO - implement Flota.Flota
		throw new UnsupportedOperationException();
	}

	public void jugarTurno() {
		// TODO - implement Flota.jugarTurno
		throw new UnsupportedOperationException();
	}

	public void colocarBarcos(Coordenada pCord, String pTipo, boolean horizontal) {	
		Barco nuevo =this.obtenerBarco(pTipo);
		if (nuevo!=null) {
				if (nuevo.colocarBarco(pCord, horizontal, tablero)){
					listaBarcos.remove(nuevo);
					barcosColocados.add(nuevo);
				}else {
					System.out.println("El barco no se puede colocar en esa posicion");
				}
			
		}else {
			//no existe el barco 
			System.out.println("No existe el barco");
			
		}
		
	}

	public void inicializarFlota() {
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

	private Iterator<Barco> obtIterador() {
		return this.listaBarcos.iterator();
	}

}