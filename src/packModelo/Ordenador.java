package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Ordenador extends Jugador {

	public Ordenador() {
		super();
	}

	public boolean jugarTurno() {
		boolean sigue =true;
		Random randomizer = new Random();
		boolean jugado=false;
		if (super.flota.getNumBarcos()!=0){
			setChanged();
			notifyObservers(" --- TURNO DEL ORDENADOR --- ");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int col, fil;
			while(!jugado) {
				//System.out.println("TURNO DEL ORDENADOR---------------------");
				col= randomizer.nextInt(10);
				fil = randomizer.nextInt(10);
				int armaAleatoria = randomizer.nextInt(2);
				String[] armas = {"Bomba","Misil","Escudo","Radar"};
				String eleccion=armas[armaAleatoria];
				if (!eleccion.equals("Bomba")){
					if (null!=super.armamento.buscarArma(armas[armaAleatoria])){
						if (eleccion.equals("Misil")) {
							jugado = this.disparar(eleccion, new Coordenada(col, fil));

						}else{
							if(eleccion.equals("Escudo")){
								boolean usado = false;
								
								while (!usado) {
									col= randomizer.nextInt(10);
									fil = randomizer.nextInt(10);
									usado=super.colocarEscudo(new Coordenada(col, fil), (Escudo)super.armamento.eliminarArma(eleccion));
								}
								jugado = true;
							}else { //radar
								
							}
						}
					}
				}else {//el arma seleccionada es una bomba
				System.out.println(" disparado en fila "+(col +1)+", col "+(1+fil));
				jugado = this.disparar(eleccion, new Coordenada(col, fil));
				System.out.println(jugado);
				}
			}
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			sigue=false;
		}
		return sigue;
	}

	public void colocarBarcosOrdenador() {
		this.inicializarFlota();
		Random randomizer = new Random();
		int col = 0;
		int fil = 0;
		boolean colocado;
		boolean horizontal;
	
		for (Barco b : super.flota.getBarcosIniciales()) {
			colocado = false;
			while (!colocado) {
				col= randomizer.nextInt(10);
				fil = randomizer.nextInt(10);
				horizontal = randomizer.nextBoolean();
				colocado = b.colocarBarco(new Coordenada(col, fil), horizontal, super.tablero);
				super.flota.anadirBarcoColocado(b);
				//barcosColocados.add(b);				
			}
			//listaBarcos = new ArrayList<>();
			System.out.println(b.getNombre()+" colocado en fila "+(col +1)+", col "+(1+fil)+" ");
			System.out.println();
			//setChanged();
			//notifyObservers(new Object[] {b.getCasillas(), false});
			
		}
	}
	
	public boolean disparar(String arma, Coordenada coord){
			if (arma.equals("Misil")) {this.eliminarArma(arma);}
			//System.out.println("en la llamada de ordenador el arma es "+arma);
			return Modelo.getModelo().getUsuario().recibirDisparo(coord,arma);
			
		}
	/*
	public boolean activarRadar(){
		return Modelo.getModelo().getUsuario().recibirRadar();
	}
	*/
	

	public boolean recibirDisparo(Coordenada pCoordenada, String arma){
		boolean disparado= true;
		if (pCoordenada!=null) {
			int x = pCoordenada.getX();
			int y = pCoordenada.getY();
			Casilla casillaActual = tablero.getCasilla(x, y);
			
			String estado=casillaActual.comprobarEstado();
			
			if (((estado.equals("Disparado")||estado.equals("Tocado")||estado.equals("Hundido")) && arma.equals("Bomba")) ||
					((estado.equals("Disparado") || estado.equals("Tocado")) && arma.equals("Misil"))) {
				disparado=false;
				setChanged();
				notifyObservers("Has disparado a una casilla ya disparada");
			}else{
				
				if(estado.equals("Barco")){
					boolean hundido;
					Barco b = super.flota.buscarBarco(pCoordenada);
					if (arma.equals("Bomba")) {
						casillaActual.cambiarEstado("Tocado");
						System.out.println("se esta buscando el barco de coordenadas x" + x+ " y "+y);
						
						setChanged();
						notifyObservers("El barco "+b.getNombre()+" ha sido tocado!");
						
						System.out.println(b.getNombre());
						System.out.println(b.tieneCordenada(pCoordenada));
						hundido = b.restarCasilla();
					}else { //el arma es un misil -> lo hunde entero
						hundido = true;
					}
					
					if (hundido){
						super.flota.reducirNumBarcos();
						ArrayList<Casilla> hundidos=b.cambiarAHundido();
						setChanged();
						notifyObservers(new Object[] {hundidos, "HundirOrdenador"});
						setChanged();
						notifyObservers("El barco "+b.getNombre()+" se ha hundido!!");
					}
				}else if (estado.equals("Escudo")){
					Barco b = super.flota.buscarBarco(pCoordenada);
					b.danarEscudo(arma);
					System.out.println("Escudo");
					setChanged();
					notifyObservers("Escudo :(");
					
					setChanged();
					ArrayList<Casilla> casiLista = new ArrayList<>();
					casiLista.add(casillaActual);
					notifyObservers(new Object[] {casiLista, "EscudoOrdenador"});
					
				}else {
					
					System.out.println("Disparado al agua");
					casillaActual.cambiarEstado("Disparado");
					setChanged();
					notifyObservers("agua :(");
				}
				setChanged();
				ArrayList<Casilla> casiLista = new ArrayList<>();
				casiLista.add(casillaActual);
				notifyObservers(new Object[] {casiLista, "DispararAOrdenador"});
			}
		 
		}
		else{
			disparado=false;
			setChanged();
			notifyObservers("Selecciona una casilla");
			}
			return disparado;
	}	
	@Override
	public void mostrarEscudoColocado(Coordenada pCoordenada) {
	//ESTE METODO NO TIENE QUE HACER NADA, ES SOLO PARA EL USUARIO
		// TODO Auto-generated method stub
		
	}
}