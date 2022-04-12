package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Almacen {

	private static Almacen miAlmacen;
	private ArrayList<Arma> armas;

	public static Almacen getAlmacen() {
		if(miAlmacen==null) {
			miAlmacen= new Almacen();
		}
		return miAlmacen;
	}

	private Almacen() {
		armas = new ArrayList<Arma>();
		this.inicializarAlmacen();
	}
	private Iterator<Arma> getIterador(){
		return this.armas.iterator();
	}
	
	public double Comprar(Double pSaldo,String pArma) {
		Arma arma = this.buscarArma(pArma);
		if(arma!=null){
			Double precio = arma.getPrecio();
			if (pSaldo>=precio){
				
				this.armas.remove(pArma);
				return (pSaldo-precio);
			
			}else return pSaldo; // la resta, sino, si es mismo saldo, no habia saldo suficiente	
		}else return -1; //no hay armas de ese tipo
		
			
			
	}
	public void anadirArma(Arma pArma) {
		armas.add(pArma);
	}
	public Arma generarArma(String pTipo) {
		return ArmaFactory.getArmaFactory().crearArma(pTipo);
	}

	public void inicializarAlmacen(){
		for(int i=0; i<6; i++){
			this.generarArma("Misil");
		}
		for(int i=0; i<2; i++){
			this.generarArma("Escudo");
		}
		for(int i=0; i<3; i++){
			this.generarArma("Radar");
		}
	}
	
	public Arma buscarArma(String pTipo){
		Arma arma=null;
		boolean enc=false;
		Iterator<Arma> it= getIterador();
		while(it.hasNext() && !enc){
			arma=it.next();
			if (arma.getClass().getSimpleName().equals(pTipo)){
					enc=true;
				}
			}
		
		if (enc){
			return arma;
		}
		else{
			System.out.println("No quedan armas del tipo " + pTipo + " para comprar.");
			return null;
		}
		
	}

}