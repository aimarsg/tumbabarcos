package packModelo;

public class Escudo extends Arma {

	//private Coordenada ubicacion;
	private int numImpactos;

	public Escudo(double pPrecio, int pImpactos) {
		super(pPrecio);
		numImpactos= pImpactos;
		arma = new ColocarEscudo();
	}

	/*public void esAlcanzado() {
		// TODO - implement Escudo.esAlcanzado
		throw new UnsupportedOperationException();
	}*/
	public int danarEscudo(String pArma){
		if (pArma.equals("Bomba")){
			numImpactos--;
		}else{
			numImpactos=0;
		}
		
		return numImpactos;
	}

}