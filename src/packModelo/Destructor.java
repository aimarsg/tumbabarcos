package packModelo; 
public class Destructor extends Barco {

	public void colocarBarco() {
		// TODO - implement Destructor.colocarBarco
		throw new UnsupportedOperationException();
	}
	
	public Destructor(int numBarcos, boolean hundido, String pNom){
        super(numBarcos,hundido,pNom);
    }
	
	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] pTablero) {
		int X = pCoordenada.getX();
		int Y = pCoordenada.getY();
		for (int i = 0; i < 2; i++) {
			pTablero[X][Y].cambiarEstado("Barco");
			super.getCasillasOcupadas().add(pTablero[X][Y]);
			if (pHorizontal) {
				X++;
			}else{
				Y++;
			}
		}
		return true;		
	}
}