package tumbabarcos;

import packModelo.*;
import packVista.*;

public class main {
	//un comentario
	
	//xddx
	//otro comment
     // va
	public static void main(String args[]) {
		Vista vista = new Vista();
		vista.setVisible(true);
		Modelo modelo= Modelo.getModelo();
		modelo.inicializar();
		//modelo.jugar();
	}
}
