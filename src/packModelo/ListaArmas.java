package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaArmas {

	private ArrayList<Arma> listaArmas;

	public ListaArmas() {
		listaArmas=new ArrayList<Arma>();
	}

	public ArrayList<Arma> getListaArmas(){
		return listaArmas;
	}
	public void anadirArma( Arma pArma) {
		listaArmas.add(pArma);
	}

	private Iterator<Arma> getIterador() {
		return this.listaArmas.iterator();
		}

	

	/**
	 * 
	 * @param String
	 */
	public Arma buscarArma(String pTipo) {
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
			System.out.println("No quedan armas del tipo " + pTipo);
			return null;
		}
	}
	public Arma eliminarArma(String pArma){
		Arma arma=buscarArma(pArma);
		if (arma!=null) {listaArmas.remove(arma);}
		return arma;
	}
	


}