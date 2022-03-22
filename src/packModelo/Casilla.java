package packModelo;

public class Casilla {

	private String estado;
	private Coordenada posicion;

	public Casilla(Coordenada pCoordenada) {
		posicion= pCoordenada;
	}

	public String combrobarEstado() {
		return estado;
	}
	public void cambiarEstado(String pEstado){
		estado= pEstado;
	}
}