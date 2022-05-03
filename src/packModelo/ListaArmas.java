package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaArmas {

	private ArrayList<Arma> listaArmas;

	public ListaArmas() {
		listaArmas=new ArrayList<Arma>();
	}

	/*public ArrayList<Arma> getListaArmas(){
		return listaArmas;
	}*/
	public void anadirArma( Arma pArma) {
		listaArmas.add(pArma);
		System.out.println(listaArmas.size());
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
		if (arma!=null) {
			System.out.println("Se ha eliminado el arma " + pArma);
			listaArmas.remove(arma);}
		return arma;
		
	}
	public int devolverNumArmas(String pArma){
		return (int) listaArmas.stream().filter(a -> a.getClass().getSimpleName().equals(pArma)).count();
	}


}