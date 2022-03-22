package packModelo;

public class Radar extends Arma {

	private int numConsultas;
	private Coordenada ubicacion;

	public Radar(double pPrecio, Coordenada pUbicacion, int pConsultas) {
		super(pPrecio);
		ubicacion= pUbicacion;
		numConsultas= pConsultas;
	}

	public void mover() {
		// TODO - implement Radar.mover
		throw new UnsupportedOperationException();
	}

	public void activarRadar() {
		// TODO - implement Radar.activarRadar
		throw new UnsupportedOperationException();
	}

}