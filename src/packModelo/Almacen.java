package packModelo;

import java.util.ArrayList;

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
	}
	
	/**
	 * 
	 * @param Double
	 * @param String
	 */
	public double Comprar(Double pSaldo,String pArma) {
		// TODO - implement Almacen.Comprar
		throw new UnsupportedOperationException();
	}
	public void anadirArma(Arma pArma) {
		armas.add(pArma);
	}
	public Arma generarArma(String pTipo) {
		return null;
	}

}