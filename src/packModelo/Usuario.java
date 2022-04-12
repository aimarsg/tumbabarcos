package packModelo;

public class Usuario extends Jugador {

	public Usuario() {
		super();
	}

	public boolean jugarTurno() {
		boolean sigue = true;
		if(super.getFlota().getNumBarcos()!=0){
			//System.out.println("TURNO DEL USUARIO---------------------");
			setChanged();
			notifyObservers(" --- TURNO DEL USUARIO --- ");
			setChanged();
			notifyObservers("ActivarDisparar");//activar el boton
			
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


		
	public void colocarBarco(Coordenada pCord, String pTipo, boolean horizontal){
		Barco nuevo =super.getFlota().obtenerBarcoInicial(pTipo);
		if (pCord!=null) {
			if (nuevo!=null) {
				if (nuevo.colocarBarco(pCord, !horizontal, this.getTablero())){ //horizontal invertido porque la x y la y van al reves
					this.getFlota().anadirBarcoColocado(nuevo);
					//notifyObservers
					System.out.println("se coloca");
					
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

}