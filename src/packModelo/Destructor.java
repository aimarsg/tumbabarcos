package packModelo; 
public class Destructor extends Barco {	
	public Destructor(int numBarcos, boolean hundido, String pNom){
        super(numBarcos,hundido,pNom);
    }
	
	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Tablero pTablero) {
		int X = pCoordenada.getX();
		int Y = pCoordenada.getY();
		boolean puede=true;
		int j=0;
		while (puede && j < 2){
			if(pTablero.estaRodeadoAgua(new Coordenada(X,Y))){
				if (pHorizontal) {
					X++;
				}else{
					Y++;
				}
			}else{
				puede=false;
			}	
			j++;
		}
		X = pCoordenada.getX();
		Y = pCoordenada.getY();
		if (puede){
			for (int i = 0; i < 2; i++) {
				pTablero.getCasilla(X,Y).cambiarEstado("Barco");
				super.getCasillasOcupadas().add(pTablero.getCasilla(X, Y));
				if (pHorizontal) {
					X++;
				}else{
					Y++;
				}
			}
		}
		return puede;		
	}
}