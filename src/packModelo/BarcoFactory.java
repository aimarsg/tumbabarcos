package packModelo; 
public class BarcoFactory {

	private static BarcoFactory miBarcoFactory;

	private BarcoFactory() {
		// TODO - implement BarcoFactory.BarcoFactory
		throw new UnsupportedOperationException();
	}

	public static BarcoFactory getBarcoFactory() {
		// TODO - implement BarcoFactory.getBarcoFactory
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param pTipo
	 */
	public Barco crearBarco(String pTipo) {
		if (pTipo.equals("PortaAviones")){
			return (new PortaAviones(4, false, "PortaAviones"));
		}
		else if (pTipo.equals("Submarino")){
			return (new Submarino(3, false, "Submarino"));
		}
		else if (pTipo.equals("Destructor")){
			return (new Destructor(2, false, "Destructor"));
		}
		else if (pTipo.equals("Fragata")){
			return (new Fragata(1, false, "Fragata"));
		}
	}

}