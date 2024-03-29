package packModelo;

import java.util.ArrayList;
import java.util.Observable;

import packVista.Sonido;
import tumbabarcos.main;

public class Modelo extends Observable {

	private Jugador[] jugadores;
	private static Modelo miModelo;

	public void jugar() {
		System.out.println("JUGANDO------------------------");
		boolean acabarO = false;
		boolean acabarU = false;
		//activar los labels del marcador
		setChanged();
		notifyObservers("ActivarMarcador");
		
		
		while(!acabarO && !acabarU){
			System.out.println("--------------turno del usuario----------------");
			if(!this.getUsuario().jugarTurno()){
				
				acabarU=true;
			}
			System.out.println("--------------turno del ordenador---------------");
			if(!acabarU){
				if(!this.getOrdenador().jugarTurno()){
				acabarO=true;
				}
			}
			
		}
		try {Thread.sleep(2500);}//PARA ESPERAR DOS SEGUNDO Y QUE NO SE SOLAPEN LOS AUDIOS
		catch (InterruptedException e) {// TODO Auto-generated catch block
		e.printStackTrace();}
		if(acabarO){
			System.out.println("Usuario has ganado ");
			setChanged();
			notifyObservers("Has ganadoooo!!!");
			setChanged();
			notifyObservers("GanaUsuario");
			Sonido.getMiSonido().ReproducirSonido("Resources/Fin.wav");
		}else{
			System.out.println("Ordenador has ganado ");
			setChanged();
			notifyObservers("El ordenador ha ganadoooo!!");
			setChanged();
			notifyObservers("GanaOrdenador");
			Sonido.getMiSonido().ReproducirSonido("Resources/Fin.wav");
		}
		/*try {Thread.sleep(2500);}//PARA ESPERAR DOS SEGUNDO Y QUE NO SE SOLAPEN LOS AUDIOS
		catch (InterruptedException e) {// TODO Auto-generated catch block
		e.printStackTrace();}
		main.main(null);*/
		
	}

	public void inicializar() {
		((Ordenador)this.getOrdenador()).colocarBarcosOrdenador();
		this.getUsuario().inicializarFlota();
		//para hacer pruebas
		//((Usuario)this.getUsuario()).pruebasColocarBarcos();
		//this.getFlotaUsuario().colocarBarcosOrdenador();
		
	}

	public static Modelo getModelo() {
		if (Modelo.miModelo==null) {
			Modelo.miModelo = new Modelo();
		}
		return Modelo.miModelo;
	}

	private Modelo() {
		this.jugadores = new Jugador[2];
		jugadores[0] = new Usuario();
		jugadores[1] = new Ordenador();
	}
	public Jugador getUsuario() {
		return jugadores[0];
	}
	public Jugador getOrdenador() {
		return jugadores[1];
	}
}