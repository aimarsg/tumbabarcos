package tumbabarcos;

import packModelo.*;
import packVista.*;

public class main {
	//un comentario
	
	//xddx
	//otro comment
     // va
	public static void main(String args[]) {
		Modelo modelo= Modelo.getModelo();
		modelo.inicializar();
		Vista vista = new Vista();
		vista.setVisible(true);
	}
}
