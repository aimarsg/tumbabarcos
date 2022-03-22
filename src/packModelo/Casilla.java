package packModelo;

public class Casilla {

	private String estado;
	private Coordenada posicion;

	public Casilla(Coordenada pCoordenada) {
		posicion= pCoordenada;
	}

	public boolean esDisparada() {
		return estado.equals("aqui hay que poner que estado esta");
	}
	public void ColocarBarco(){
		estado= "barco";
	}
}