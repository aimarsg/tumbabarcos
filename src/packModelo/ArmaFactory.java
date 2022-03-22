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
	public Arma crearArma(  String pTipo) {
		throw new UnsupportedOperationException();
	}

}