package packModelo;
import java.util.ArrayList;
import java.util.Iterator;

public class Usuario extends Jugador {

	public Usuario() {
		super();
	}

	public boolean jugarTurno() {
		boolean sigue = true;
		if(super.flota.getNumBarcos()!=0){
			//System.out.println("TURNO DEL USUARIO---------------------");
			setChanged();
			notifyObservers(" --- TURNO DEL USUARIO --- ");
			setChanged();
			notifyObservers("ActivarDisparar");//activar el boton
			//notifyObservers("ActivarComprar");
			while (!super.disparado) {
				try {Thread.sleep(1000);}
				catch (InterruptedException e) {// TODO Auto-generated catch block
				e.printStackTrace();}
			} //para que espere hasta que este disparado
			super.disparado = false;//se ha desactivado el boton y se pone false para la siguiente 
			
			
		}else{//si ha perdido acaba directamente
			sigue = false;
		}
		
		return sigue;
	}

	public void pruebasColocarBarcos() {
		this.colocarBarco(new Coordenada(1,1), "PortaAviones", true);
		this.colocarBarco(new Coordenada(3,1), "Submarino", true);
		this.colocarBarco(new Coordenada(5,1), "Submarino", true);
		this.colocarBarco(new Coordenada(7,1), "Destructor", true);
		this.colocarBarco(new Coordenada(9,1), "Destructor", true);
		this.colocarBarco(new Coordenada(1,7), "Destructor", true);
		this.colocarBarco(new Coordenada(3,7), "Fragata", true);
		this.colocarBarco(new Coordenada(5,7), "Fragata", true);
		this.colocarBarco(new Coordenada(7,7), "Fragata", true);
		this.colocarBarco(new Coordenada(9,7), "Fragata", true);
		
	}
		
	public void colocarBarco(Coordenada pCord, String pTipo, boolean horizontal){
		Barco nuevo =super.flota.obtenerBarcoInicial(pTipo);
		if (pCord!=null) {
			if (nuevo!=null) {
				if (nuevo.colocarBarco(pCord, !horizontal, super.tablero)){ //horizontal invertido porque la x y la y van al reves
					super.flota.anadirBarcoColocado(nuevo);
					//notifyObservers
					System.out.println("se coloca");
					this.flota.eliminarBarco(nuevo);
					setChanged();
					//notifyObservers(new Object[] {nuevo.getCasillas(), true}); este es si pasamos boolean
					notifyObservers(new Object[] {nuevo.getCasillas(), "ColocarBarco"});// este para String
					setChanged();
					notifyObservers("Barco "+pTipo+" colocado.");
					//notifyObservers(nuevo.getCasillas());
					
				}else {
					System.out.println("El barco no se puede colocar en esa posicion");
					setChanged();
					notifyObservers("El barco no se puede colocar en esa posicion");
				}
			}else {//no existe el barco 
				System.out.println("No existe el barco");
				setChanged();
				notifyObservers("No queda ningun barco del tipo "+pTipo+" por colocar. Selecciona otro.");
			}
		}else {//no se ha seleccionado ninguna casilla
			System.out.println("No se ha seleccionado ninguna casilla");
			setChanged();
			notifyObservers("No se ha seleccionado ninguna casilla.");			
		}
	}

	public boolean disparar(String arma, Coordenada coord){
		//comprobar sin quedan armas
		if (super.armamento.buscarArma(arma)!=null) {
			if (arma.equals("Misil")) {this.eliminarArma(arma);}
			return (Modelo.getModelo().getOrdenador().recibirDisparo(coord,arma));
		}else{
			setChanged();
			notifyObservers("No te quedan armas del tipo "+arma);
			return false;
		}
	}
		
		
		
	public boolean activarRadar(){
		boolean recibido=false;
		
		
		return recibido;
	}
	
	public boolean recibirDisparo(Coordenada pCoordenada,String arma){
		boolean disparado= true;
		if (pCoordenada!=null) {
			int x = pCoordenada.getX();
			int y = pCoordenada.getY();
			Casilla casillaActual = tablero.getCasilla(x, y);
			
			String estado=casillaActual.comprobarEstado();
			
			if ( ((estado.equals("Disparado")||estado.equals("Tocado")||estado.equals("Hundido")) && arma.equals("Bomba")) ||
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
						notifyObservers(new Object[] {hundidos, "HundirUsuario"});
						
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
					notifyObservers(new Object[] {casiLista, "EscudoUsuario"});
					
				}else{
					System.out.println("Disparado al agua");
					casillaActual.cambiarEstado("Disparado");
					setChanged();
					notifyObservers("agua :(");
				}
				setChanged();
				ArrayList<Casilla> casiLista = new ArrayList<>();
				casiLista.add(casillaActual);
				notifyObservers(new Object[] {casiLista,"DispararAUsuario"});				
				
			}
		}else{
			disparado=false;
			setChanged();
			notifyObservers("Selecciona una casilla");
		}
		return disparado;
	}
	public boolean todosColocados() {
		return flota.todosColocados();
	}
	public void setDisparadoUsuario() {
		this.disparado = true;
	}
	@Override
	public void mostrarEscudoColocado(Coordenada pCoordenada) {
		ArrayList<Casilla> c  = flota.buscarBarco(pCoordenada).getCasillasOcupadas();
		setChanged();
		notifyObservers(new Object[] {c, "PintarEscudo"});
		
	}
	
	
}