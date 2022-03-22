package packModelo; 
public class Fragata extends Barco {
	
	public Fragata(int numLibres, boolean hundido, String pNom){
        super();
    }

	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] pTablero) {
		int x= pCoordenada.getX();
		int y= pCoordenada.getY();
	
		pTablero[x][y].cambiarEstado("Barco");
		return (Boolean) null;
		
	}

}