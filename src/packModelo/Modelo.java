package packModelo;

import java.util.ArrayList;
import java.util.Observable;

public class Modelo extends Observable {

	private Flota[] flotas;
	private static Modelo miModelo;

	public void jugar() {
		System.out.println("JUGANDO------------------------");
		boolean acabarO = false;
		boolean acabarU = false;
		
		while(!acabarO && !acabarU){
			if(!this.getFlotaUsuario().jugarTurno()){
				
				acabarU=true;
			}
			if(!acabarU){
				if(!this.getFlotaOrdenador().jugarTurnoIA()){
				acabarO=true;
				}
			}
			
		}
		if(acabarO){
			System.out.println("Usuario has ganado ");
			setChanged();
			notifyObservers("Has ganadoooo!!!");
		}else{
			System.out.println("Ordenador has ganado ");
			setChanged();
			notifyObservers("El ordenador ha ganadoooo!!");
		}
	}

	public void inicializar() {
		this.getFlotaOrdenador().colocarBarcosOrdenador();
		this.getFlotaUsuario().inicializarFlota();
		//para hacer pruebas
		//this.getFlotaUsuario().colocarBarcosOrdenador();
		
	}

	public static Modelo getModelo() {
		if (Modelo.miModelo==null) {
			Modelo.miModelo = new Modelo();
		}
		return Modelo.miModelo;
	}

	private Modelo() {
		this.flotas = new Flota[2];
		for (int i=0; i<2; i++) {
			flotas[i] = new Flota();
		}
	}
	public Flota getFlotaUsuario() {
		return flotas[0];
	}
	public Flota getFlotaOrdenador() {
		return flotas[1];
	}
}