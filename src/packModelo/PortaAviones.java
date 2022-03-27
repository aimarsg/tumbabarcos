package packModelo; 
public class PortaAviones extends Barco {
	public PortaAviones(int numLibres, boolean hundido, String pNom) {
		super(numLibres, hundido, pNom);
		// TODO Auto-generated constructor stub
	}

	//longitud del portaaviones = 4
	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Tablero pTablero) {
		int X = pCoordenada.getX();
		int Y = pCoordenada.getY();
		boolean puede=true;
		int j=0;
		while (puede && j < 4){
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

		if (puede){
			X = pCoordenada.getX();
			Y = pCoordenada.getY();
			for (int i = 0; i < 4; i++) {
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