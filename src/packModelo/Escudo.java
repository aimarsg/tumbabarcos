package packModelo;

public class Escudo extends Arma {

	private Coordenada ubicacion;
	private int numImpactos;

	public Escudo(double pPrecio, Coordenada pUbicacion, int pImpactos) {
		super(pPrecio);
		ubicacion= pUbicacion;
		numImpactos= pImpactos;
	}

	public void esAlcanzado() {
		// TODO - implement Escudo.esAlcanzado
		throw new UnsupportedOperationException();
	}

}