package packModelo; 
public class ArmaFactory {

	private static ArmaFactory mArmaFactory;

	private ArmaFactory() {
		
	}

	public static ArmaFactory getArmaFactory() {
		if (mArmaFactory==null){
			mArmaFactory= new ArmaFactory();
		}
		return mArmaFactory;
	}

	/**
	 * 
	 * @param pTipo
	 */
	public Arma crearArma(String pTipo) {
		if (pTipo.equals("Bomba")) {
			return (new Bomba(0));
			
		}else if (pTipo.equals("Misil")) {
			return (new Misil(200));
			
		}else if (pTipo.equals("Escudo")) {
			return (new Escudo(100, 2));
			
		}else { //RADAR
			return (new Radar(150, 2));
		}
	}

}