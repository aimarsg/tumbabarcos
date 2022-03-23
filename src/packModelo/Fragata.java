package packModelo; 
public class Fragata extends Barco {
	
	public Fragata(int numLibres, boolean hundido, String pNom){
        super(numLibres,hundido,pNom);
    }

	public boolean colocarBarco(Coordenada pCoordenada, boolean pHorizontal, Casilla[][] pTablero) {
		int x= pCoordenada.getX();
		int y= pCoordenada.getY();
		boolean posible=true;
		Casilla casilla= pTablero[x][y];
		if((x>-1 && x<11) && (y>-1 && y<11)) {
			if(casilla.combrobarEstado()!="Agua") {
				if(pTablero[x+1][y].combrobarEstado()!="Agua"&&
					pTablero[x][y+1].combrobarEstado()!="Agua"&&
					pTablero[x-1][y].combrobarEstado()!="Agua"&&
					pTablero[x][y-1].combrobarEstado()!="Agua"&&
					pTablero[x+1][y+1].combrobarEstado()!="Agua"&&
					pTablero[x-1][y+1].combrobarEstado()!="Agua"&&
					pTablero[x-1][y-1].combrobarEstado()!="Agua"&&
					pTablero[x+1][y-1].combrobarEstado()!="Agua") {
					pTablero[x][y].cambiarEstado("Barco");
				}else {
					posible= false;
					System.out.println("El Barco no esta completamente rodeado de agua");
				}
			}else {
				posible= false;
				System.out.println("la casilla seleccionada no es agua");
			}
			
		}else {
			posible= false;
			System.out.println("Las coordenadas no estan en el tablero");
		}
		
		return (Boolean) null;
		
	}

}