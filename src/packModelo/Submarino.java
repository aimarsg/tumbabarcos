package packModelo; 
public class Submarino extends Barco {
	public Submarino(int numLibres, boolean hundido, String pNom){
		super(numLibres,hundido,pNom);
	}
	public boolean colocarBarco(Coordenada pCoord, boolean pHorizontal, Tablero pTablero) {
		// TODO - implement Submarino.colocarBarco
		int X = pCoord.getX();
		int Y = pCoord.getY();
		boolean puede=true;
		int j=0;
		while (puede && j < 3){
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
		X = pCoord.getX();
		Y = pCoord.getY();
		if (puede){
			for (int i = 0; i < 3; i++) {
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