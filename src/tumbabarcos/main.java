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
		
		while (!modelo.getFlotaUsuario().todosColocados()) {try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} //para que espere hasta que esten todos colocados
		System.out.println(" Todos los barcos del usuario han sido colocados. FIGHT!");
		
		modelo.jugar();
	}
}
