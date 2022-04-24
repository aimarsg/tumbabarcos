package packModelo;

//import java.util.ArrayList;


public class Almacen {

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

	
	public double comprar(Double pSaldo,String pArma) {
		Arma arma = armas.buscarArma(pArma);
		if(arma!=null){
			Double precio = arma.getPrecio();
			if (pSaldo>=precio){
				
				this.armas.eliminarArma(pArma);
				return (pSaldo-precio);
			
			}else return pSaldo; // la resta, sino, si es mismo saldo, no habia saldo suficiente	
		}else return -1; //no hay armas de ese tipo			
	}
	
	public Arma generarArma(String pTipo) {
		return ArmaFactory.getArmaFactory().crearArma(pTipo);
	}

	public void inicializarAlmacen(){
		for(int i=0; i<6; i++){		
			armas.anadirArma(this.generarArma("Misil"));
		}
		for(int i=0; i<2; i++){
			armas.anadirArma(this.generarArma("Escudo"));
		}
		for(int i=0; i<3; i++){
			armas.anadirArma(this.generarArma("Radar"));
		}
	}
	
	
	

}