package packModelo;
public abstract class Arma {

	protected Double precio;

	public Arma(Double pPrec) {
		precio= pPrec;
		
	}

	public double getPrecio() {
		// TODO - implement Arma.getPrecio
		return precio;
	}

}