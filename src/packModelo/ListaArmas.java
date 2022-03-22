package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaArmas {

	private ArrayList<Arma> listaArmas;

	public ListaArmas() {
		listaArmas=new ArrayList<Arma>();
	}

	/**
	 * 
	 * @param Arma
	 */
	public void anadirArma( Arma pArma) {
		// TODO - implement ListaArmas.anadirArma
		throw new UnsupportedOperationException();
	}

	private Iterator<Arma> obtIterador() {
		// TODO - implement ListaArmas.obtIterador
		return this.listaArmas.iterator();
		}

	/**
	 * 
	 * @param Double
	 * @param String
	 */
	public double comprar( Double pSaldo,  String arma) {
		// TODO - implement ListaArmas.comprar
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param String
	 */
	private Arma buscarArma( String nombre) {
		// TODO - implement ListaArmas.buscarArma
		throw new UnsupportedOperationException();
	}

}