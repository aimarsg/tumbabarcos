package packModelo;

//import java.util.Observable;

public abstract class Arma {

	protected Double precio;

	public Arma(Double pPrec) {
		precio= pPrec;
		
	}

	public double getPrecio() {
		
		return precio;
	}
	
	
	

}