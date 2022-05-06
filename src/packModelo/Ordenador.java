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
				int armaAleatoria = randomizer.nextInt(4);
				String[] armas = {"Bomba","Misil","Escudo","Radar"};
				String eleccion=armas[armaAleatoria];
				int accion= randomizer.nextInt(100);

				if (accion<60) { // UTILIZAR
					if (!eleccion.equals("Bomba")){
						if (null!=super.armamento.buscarArma(armas[armaAleatoria])){
							if (eleccion.equals("Misil")) {
								jugado = this.disparar(eleccion, new Coordenada(col, fil));
								System.out.println("ha usado un misil ");

							}else{
								if(eleccion.equals("Escudo")){
									boolean usado = false;
									Escudo esc = (Escudo)super.armamento.buscarArma(eleccion);
									while (!usado) {
										col = randomizer.nextInt(10);
										fil = randomizer.nextInt(10);
										usado = super.colocarEscudo(new Coordenada(col, fil), esc );
										
									}
									jugado = true;
									System.out.println("se ha puesto un escudo fila "+fil+" col "+col);
								}else if (eleccion.equals("Radar")) { //radar
									super.moverRadar();
									System.out.println("Ha entrado a usar un radar");
									Radar ra = (Radar) super.armamento.buscarArma("Radar");
									Coordenada coorden = ra.getUbi();
									System.out.println("Radar del ordenador colocado en fila "+(coorden.getX()+1)+" columna "+(coorden.getY()+1));
									this.consultarRadar();
									System.out.println("se ha usado el radar y ahora el numero de consultas restantes es "+ra.getNumConsultas());
									jugado = true;
								}
							}
						}
					}else {//el arma seleccionada es una bomba
						//System.out.println(" disparado en fila "+(col +1)+", col "+(1+fil));
						jugado = this.disparar(eleccion, new Coordenada(col, fil));
						System.out.println(jugado);
					}
				}else if(accion>80){ // COMPRAR
					if (!eleccion.equals("Bomba")){
						super.comprarArma(eleccion);
						//System.out.println("El ordenador ha comprado un " + eleccion);
						//setChanged();
						//notifyObservers("Comprado");
					}
					
				}else{ // REPARAR BARCO
						//boolean enc=false;
						//System.out.println(accion);
					
						System.out.println("Entra a reparar barco");
						ArrayList<Barco> barcosTocados=super.flota.devolverTocadoHundido();
						Iterator<Barco> it = barcosTocados.iterator();
						boolean reparado = false;
						Barco b = null;
						
						while(!reparado && it.hasNext()) {
							b = it.next();
							reparado = super.repararBarco(b.getCasillasOcupadas().get(0).getPosicion());
							
						}
						jugado = reparado;					
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
					if (arma.equals("Misil")) {this.eliminarArma(arma);}//
					int x= nueva.getX();
					int y= nueva.getY();
					disparado=true;
					System.out.println(" disparado en fila "+(x +1)+", col "+(1+y));
					if(u.obtenerEstadoCasilla(x, y).equals("Hundido")){
						for (Casilla casillaBarco : u.flota.buscarBarco(nueva).getCasillasOcupadas()) {
							for (Coordenada co : this.tablero.obtenerCasillasAlRededor(casillaBarco.getPosicion())) {
								if(co!=null){
									casillasPila.remove(co);
								}
							}	//para quitar de la lista las de alrededor del barco que se ha hundido
						}
					}
					if(u.obtenerEstadoCasilla(x, y).equals("Tocado") || u.obtenerEstadoCasilla(x, y).equals("Escudo")){
						if (u.obtenerEstadoCasilla(x, y).equals("Escudo")) {
							casillasPila.push(nueva);
						}
						/*for (Coordenada co : this.tablero.obtenerCasillasAlRededor(nueva)) {
			 				casillasPila.push(co);
			 			}*/
						 this.tablero.obtenerCasillasAlRededor(nueva).stream().forEach(c->casillasPila.push(c));
					}	
					
				}
			}else{
				if (arma.equals("Misil")) {this.eliminarArma(arma);}
				//System.out.println("en la llamada de ordenador el arma es "+arma);
				if(u.recibirDisparo(coord,arma)){
			 		int x= coord.getX();
			 		int y= coord.getY();
			 		disparado = true;
			 		if(u.obtenerEstadoCasilla(x, y).equals("Tocado") || u.obtenerEstadoCasilla(x, y).equals("Escudo")){
						if (u.obtenerEstadoCasilla(x, y).equals("Escudo")) {
							casillasPila.push(coord);
						}
						 //meter las casillas de alrededor
			 			/*
						for (Coordenada co : this.tablero.obtenerCasillasAlRededor(coord)) {
			 				casillasPila.push(co);
			 			}
						 */
						 this.tablero.obtenerCasillasAlRededor(coord).stream().forEach(c->casillasPila.push(c));
					 }
				}
			}
			System.out.println("se ha disparado con un(a)" +arma);
			return disparado;	
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
						Modelo.getModelo().getUsuario().aumentarSaldo(100.0);
						
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
			
			ArrayList<Casilla> resultado = 	r.consultar("Ordenador");
			if (!resultado.isEmpty()) {
				casillasPila.push(resultado.get(0).getPosicion());
			}else {
				System.out.println("no ha encontrado barco");
			}
			int consultasRestantes = r.getNumConsultas();
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
	@Override
	protected void mostrarBarcoReparado(ArrayList<Casilla> barco) {
		// 
		
		setChanged();
		notifyObservers(new Object[] {barco, "RepararBarcoOrdenador"});
		
	}
}