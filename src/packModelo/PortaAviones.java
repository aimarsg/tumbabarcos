package packModelo; 
public class PortaAviones extends Barco {
	//longitud del portaaviones = 4
	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] tablero) {
		int i = pCoordenada.getX();
		int j = pCoordenada.getY();
		int cont = 0;
		while (cont < 4) {
			tablero[i][j].cambiarEstado("Barco");
			if (pHorizontal) {
				i++;
			}else {
				j++;
			}
			cont++;
		}
	}

}