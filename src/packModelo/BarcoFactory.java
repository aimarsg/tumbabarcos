package packModelo; 
public class BarcoFactory {

	private static BarcoFactory miBarcoFactory;

	private BarcoFactory() {
		
	}

	public static BarcoFactory getBarcoFactory() {
		if(miBarcoFactory==null){
			miBarcoFactory = new BarcoFactory();
		}
		return miBarcoFactory;
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
		else return null;
	}

}