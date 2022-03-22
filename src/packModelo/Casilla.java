package packModelo;

public class Casilla {

	private String estado;
	private Coordenada posicion;

	public Casilla(Coordenada pCoordenada) {
		posicion= pCoordenada;
	}

	public String CombrobarEstado() {
		return estado;
	}
	public void CambiarEstado(String pEstado){
		estado= pEstado;
	}
}