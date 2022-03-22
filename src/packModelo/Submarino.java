package packModelo; 
public class Submarino extends Barco {

	public void colocarBarco(Coordenada pCoord, boolean pHorizontal, Casilla[][] pTablero) {
		// TODO - implement Submarino.colocarBarco
		throw new UnsupportedOperationException();
		if (pHorizontal) {
			for (int i=0;i<3; i++) {
				if (pHorizontal) {
					pTablero[pCoord.getX()+i][pCoord.getY()].cambiarEstado("Barco");;
				}
				else {
					pTablero[pCoord.getX()][pCoord.getY()-i].cambiarEstado("Barco");;
				}
			}
		}

		
	}

}