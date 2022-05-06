package packModelo;
import java.util.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
		if (pCord==null) {return null;}
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

	public ArrayList<Barco> devolverTocadoHundido(){
		
		return (ArrayList<Barco>) lista.stream().filter( b -> b.estaTocado()>=1 ).collect(Collectors.toList());
		
		/*boolean enc = false;
		Iterator<Barco> itr = this.obtenerIterador();
		
		while(!enc && itr.hasNext()){
			barco = itr.next();
			if (barco.estaTocado()!=0){
				enc =true;
			}
		}
		
		if(enc==false){barco = null;}
		return barco;*/
		
	}

}