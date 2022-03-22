package packModelo; 
public class Submarino extends Barco {
	public Submarino(int numLibres, boolean hundido, String pNom){
		super(numLibres,hundido,pNom);
	}
	public boolean colocarBarco(Coordenada pCoord, boolean pHorizontal, Casilla[][] pTablero) {
		// TODO - implement Submarino.colocarBarco
		boolean colocado=true;
	
		for (int i=0;i<3; i++) {
			if (pHorizontal) {
				pTablero[pCoord.getX()+i][pCoord.getY()].cambiarEstado("Barco");;
			}
			else {
				pTablero[pCoord.getX()][pCoord.getY()+i].cambiarEstado("Barco");;
			}
		}
		return colocado; 
		
	}

}