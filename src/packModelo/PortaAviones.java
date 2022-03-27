package packModelo; 
public class PortaAviones extends Barco {
	public PortaAviones(int numLibres, boolean hundido, String pNom) {
		super(numLibres, hundido, pNom);
		// TODO Auto-generated constructor stub
	}

	//longitud del portaaviones = 4
	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] tablero) {
		int i = pCoordenada.getX();
		int j = pCoordenada.getY();
		int cont = 0;
		while (cont < 4) {
			tablero[i][j].cambiarEstado("Barco");
			super.getCasillasOcupadas().add(tablero[i][j]);
			if (pHorizontal) {
				i++;
			}else {
				j++;
			}
			cont++;
		}
		return true;
	}

}