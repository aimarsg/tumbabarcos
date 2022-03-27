package packModelo; 
public class Fragata extends Barco {
	
	public Fragata(int numLibres, boolean hundido, String pNom){
        super(numLibres,hundido,pNom);
    }

	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Tablero pTablero) {
		int X = pCoordenada.getX();
		int Y = pCoordenada.getY();
		if(pTablero.estaRodeadoAgua(new Coordenada(X,Y))){
			pTablero.getCasilla(X,Y).cambiarEstado("Barco");
			super.getCasillasOcupadas().add(pTablero.getCasilla(X, Y));
		}
		return pTablero.estaRodeadoAgua(new Coordenada(X,Y));		
	}
}