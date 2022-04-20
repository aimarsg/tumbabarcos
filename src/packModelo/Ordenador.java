package packModelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

public class Ordenador extends Jugador {
	public Stack<Coordenada> casillasPila;

	public Ordenador() {
		super();
		casillasPila= new Stack();
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
				int armaAleatoria = randomizer.nextInt(3);
				String[] armas = {"Bomba","Misil","Escudo","Radar"};
				String eleccion=armas[armaAleatoria];
				if (!eleccion.equals("Bomba")){
					if (null!=super.armamento.buscarArma(armas[armaAleatoria])){
						if (eleccion.equals("Misil")) {
							jugado = this.disparar(eleccion, new Coordenada(col, fil));
							System.out.println("ha usado un misil ");

						}else{
							if(eleccion.equals("Escudo")){
								boolean usado = false;
								Escudo esc = (Escudo)super.armamento.eliminarArma(eleccion);
								while (!usado) {
									col = randomizer.nextInt(10);
									fil = randomizer.nextInt(10);
									usado = super.colocarEscudo(new Coordenada(col, fil), esc );
									
								}
								jugado = true;
								System.out.println("se ha puesto un escudo fila "+fil+" col "+col);
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
			boolean disparado= false;
			Usuario u = (Usuario) Modelo.getModelo().getUsuario();
			if(!casillasPila.isEmpty()){
				Coordenada nueva = casillasPila.pop();
				
				if(u.recibirDisparo(nueva, arma)){
					int x= nueva.getX();
					int y= nueva.getY();
					disparado=true;
					if(u.obtenerEstadoCasilla(x, y).equals("Hundido")){
						for (Coordenada co : this.tablero.obtenerCasillasAlRededor(coord)) {
							if(co!=null){
								casillasPila.remove(co);
							}
			 				
					}
					
					if(u.obtenerEstadoCasilla(x, y).equals("Tocado")){
						for (Coordenada co : this.tablero.obtenerCasillasAlRededor(coord)) {
			 				casillasPila.push(co);
			 			}
					}
				}
			}else{
				if (arma.equals("Misil")) {this.eliminarArma(arma);}
			//System.out.println("en la llamada de ordenador el arma es "+arma);
				if(u.recibirDisparo(coord,arma)){
			 		int x= coord.getX();
			 		int y= coord.getY();
			 		disparado = true;
			 		if(u.obtenerEstadoCasilla(x, y).equals("Tocado")){
						 //metern las casillas de alrededor
			 			for (Coordenada co : this.tablero.obtenerCasillasAlRededor(coord)) {
			 				casillasPila.push(co);
			 			}
						 
					 }
				}
			}
			
			}return disparado;	
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
	public boolean consultarRadar() {
		Radar r = (Radar) this.armamento.buscarArma("Radar");
		if (r != null) {
			
			if (r.getUbi() == null){super.moverRadar();}
			int consultasRestantes = r.getNumConsultas();
			casillasPila.push(	r.consultar("Ordenador").get(0).getPosicion());
			if (consultasRestantes == 0) {
				this.armamento.eliminarArma("Radar");
			}
			return true;
		}else {
			System.out.println("no te quedan armas del tipo RADAR");
			setChanged();
			notifyObservers("No te quedan radares");
			return false;
		}
		
	}
	
	@Override
	protected void verRadar(Coordenada pCoord, boolean poner) {
		// TODO Auto-generated method stub
		
	}
}