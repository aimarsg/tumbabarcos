package packModelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Flota {
	private int numBarcos;
	private ArrayList<Barco> listaBarcosIniciales;
	private ArrayList<Barco> barcosColocados;
	
	public Flota() {
		numBarcos = 10;
		listaBarcosIniciales = new ArrayList<Barco>();
		barcosColocados = new ArrayList<Barco>();
	}
	
	public ArrayList<Barco> getBarcosIniciales(){
		return listaBarcosIniciales;
	}
	
	private Iterator<Barco> iteradorColocados(){
		return barcosColocados.iterator();
	}
	private Iterator<Barco> iteradorIniciales(){
		return listaBarcosIniciales.iterator();
	}
	
	public void anadirBarco(Barco pBarco) {
		this.listaBarcosIniciales.add(pBarco);
	}
	
	public void anadirBarcoColocado(Barco pBarco) {
		this.barcosColocados.add(pBarco);
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
			this.listaBarcosIniciales.remove(b);
			return b;
		}
		return null;
	}
	public int getNumBarcos() {
		return this.numBarcos;
	}

	public Barco buscarBarco(Coordenada pcord ) {
		
		boolean enc = false;
		Barco b=null;
		Iterator<Barco> it = this.iteradorColocados();
		//barcosColocados.stream().allMatch(b -> b.tieneCordenada(pcord))S;
		
		while (!enc && it.hasNext()){
			b=it.next();
			enc=b.tieneCordenada(pcord);
		}
		if (!enc) {return null;}
		return b;
	}	

	public boolean todosColocados() {
		return listaBarcosIniciales.isEmpty();
	}
}
