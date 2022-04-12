package packModelo;

public class Misil extends Arma{

	public Misil(double pPrecio) {
		super(pPrecio);
		arma = new MisilDisp();
	}

	/**
	 * 
	 * @param Coordenada
	 */
	public void lanzar(Coordenada pcord) {
		// TODO - implement Misil.lanzar
		throw new UnsupportedOperationException();
	}

}