package packModelo;
import java.util.*;

import java.util.ArrayList;

public class ListaBarcos {
	private ArrayList<Barco> lista;
	
	public ListaBarcos() {
		this.lista = new ArrayList<Barco>();
	}

	private Iterator<Barco> obtenerIterador() {
		return this.lista.iterator();
	}

	public ArrayList<Barco> getLista() {
		return lista;
	}


	public Barco buscarBarco(String pBarco) {
		for (Barco b : this.lista){
			if(pBarco.equals(b.getNombre())){
				return b;
			}
		}
		return null;			
	}
	
	
	public void eliminarBarco(Barco pBarco) {
		lista.remove(pBarco);
	}

	public void anadirBarco(Barco pBarco) {
		lista.add(pBarco);
	}

	public Barco buscarBarcoCord(Coordenada pCord) {
		boolean enc = false;
		Barco b=null;
		Iterator<Barco> it = this.obtenerIterador();
		//barcosColocados.stream().allMatch(b -> b.tieneCordenada(pcord))S;
		
		while (!enc && it.hasNext()){
			b=it.next();
			enc=b.tieneCordenada(pCord);
		}
		if (!enc) {return null;}
		return b;
	}
	
	public boolean isEmpty(){
		return lista.isEmpty();
	}

}