package packModelo;

import java.util.Observable;

//import java.util.ArrayList;


public class Almacen extends Observable {

	private static Almacen miAlmacen;
	private ListaArmas armas;

	public static Almacen getAlmacen() {
		if(miAlmacen==null) {
			miAlmacen= new Almacen();
		}
		return miAlmacen;
	}

	private Almacen() {
		armas = new ListaArmas();
		this.inicializarAlmacen();
	}

	
	public Arma comprar(Double pSaldo,String pArma) {
		Arma arma = armas.buscarArma(pArma);
		if(arma!=null){
			Double precio = arma.getPrecio();
			if (pSaldo>=precio){
				System.out.println("Se ha comprado el arma "+ pArma);
				return(this.armas.eliminarArma(pArma));
			
			}else { // la resta, sino, si es mismo saldo, no habia saldo suficiente
				setChanged();
				notifyObservers("No tienes saldo suficiente para comprar ese arma.");
				return null; 
			}	
		}else { //no hay armas de ese tipo
			setChanged();
			notifyObservers("No quedan armas de ese tipo para comprar.");
			return null; 
		}			
	}
	
	public Arma generarArma(String pTipo) {
		return ArmaFactory.getArmaFactory().crearArma(pTipo);
	}

	public void inicializarAlmacen(){
		for(int i=0; i<10; i++){		
			armas.anadirArma(this.generarArma("Misil"));
		}
		for(int i=0; i<4; i++){
			armas.anadirArma(this.generarArma("Escudo"));
		}
		for(int i=0; i<3; i++){
			armas.anadirArma(this.generarArma("Radar"));
		}
		
	}
	public int devolverCantArmas(String pNombre){
		return armas.devolverNumArmas(pNombre);
	}
	
	

}