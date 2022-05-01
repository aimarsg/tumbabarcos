package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Flota {
	private int numBarcos;
	private ListaBarcos listaBarcosIniciales;
	private ListaBarcos barcosColocados;
	
	public Flota() {
		numBarcos = 10;
		
		listaBarcosIniciales = new ListaBarcos();
		barcosColocados = new ListaBarcos();
	}
	
	public ArrayList<Barco> getBarcosIniciales(){
		return listaBarcosIniciales.getLista();
	}
	
	/*private Iterator<Barco> iteradorColocados(){
		return barcosColocados.iterator();
	}
	private Iterator<Barco> iteradorIniciales(){
		return listaBarcosIniciales.iterator();
	}
	*/
	public void anadirBarco(Barco pBarco) {
		this.listaBarcosIniciales.anadirBarco(pBarco);
	}
	
	public void anadirBarcoColocado(Barco pBarco) {
		this.barcosColocados.anadirBarco(pBarco);
	}
	public void reducirNumBarcos(){
		numBarcos--;
	}
	
	public Barco obtenerBarcoInicial(String pTipo) {
		Iterator<Barco> it = iteradorIniciales();
		boolean enc = false;
		Barco b = null;
		while (it.hasNext() && !enc) {
			b =  it.next();
			if (b.esBarco(pTipo)) {
				enc = true;
			}
		}
		if (enc) {
			
			return b;
		}
		return null;
	}

	public void eliminarBarco(Barco pBarco){
		this.listaBarcosIniciales.eliminarBarco(pBarco);
	}

	public int getNumBarcos() {
		return this.numBarcos;
	}

	public Barco buscarBarco(Coordenada pcord ) {
		return barcosColocados.buscarBarco(pcord);
		/*boolean enc = false;
		Barco b=null;
		Iterator<Barco> it = this.iteradorColocados();
		//barcosColocados.stream().allMatch(b -> b.tieneCordenada(pcord))S;
		
		while (!enc && it.hasNext()){
			b=it.next();
			enc=b.tieneCordenada(pcord);
		}
		if (!enc) {return null;}
		return b;*/
	}	

	public boolean todosColocados() {
		return listaBarcosIniciales.isEmpty();
	}
}
