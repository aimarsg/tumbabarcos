package packModelo;

public class Casilla {

	private String estado;
	private Coordenada posicion;

	public Casilla(String pEstado,Coordenada pCoordenada) {
		estado= pEstado;
		posicion= pCoordenada;		
	}

	public String comprobarEstado() {
		return estado;
	}
	public void cambiarEstado(String pEstado){
		estado= pEstado;
	}
	public Coordenada getPosicion() {
		return this.posicion;
	}
	
	
}