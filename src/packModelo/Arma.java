package packModelo;
public abstract class Arma {

	protected Double precio;
	protected ArmaStrategy arma;

	public Arma(Double pPrec) {
		precio= pPrec;
		
	}

	public double getPrecio() {
		// TODO - implement Arma.getPrecio
		return precio;
	}
	public void Utilizar(){
		arma.utilizar();
	}
	

}