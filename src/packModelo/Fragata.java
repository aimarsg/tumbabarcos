package packModelo; 
public class Fragata extends Barco {
	
	

	public void colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] pTablero) {
		int x= pCoordenada.getX();
		int y= pCoordenada.getY();
	
		pTablero[x][y].cambiarEstado("Barco");
		
	}

}