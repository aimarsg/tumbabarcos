package packModelo;
import java.util.ArrayList;

public class Tablero {

	private Casilla[][] tablero;
	
	public Tablero(int pX, int pY){
		tablero= new Casilla[pX][pY];
	}
	public Casilla getCasilla(int pX, int pY){
		return tablero[pX][pY];
	}
	public void inicializarTablero(){
		for (int i = 0; i<10; i++) {
			for (int j = 0; j<10; j++) {
				
				Coordenada pCord = new Coordenada(i, j);
				tablero[i][j] = new Casilla("Agua",pCord);
							
			}
		}
	}
	
	public boolean estaRodeadoAgua(Coordenada pCoordenada){
		boolean aguita=true;
		if(!this.estaEnTablero(pCoordenada)){
			aguita=false;
			}
		else{
			int x = pCoordenada.getX()-1;
			int y = pCoordenada.getY()-1;
			int i=0;
			int j=0;
			while(aguita && i<3 ){
				j=0;
				while(aguita && j<3){
					if(this.estaEnTablero(new Coordenada(x+i,y+j))){
						if(!tablero[x+i][y+j].comprobarEstado().equals("Agua")){				
							aguita=false;
							System.out.println("esa casilla no se puede seleccionar");
							}
					}
				j++;
				}
				i++;	
			}	
		}
		return aguita;
	}
	
	public boolean estaEnTablero(Coordenada pCoordenada){
		int x = pCoordenada.getX();
		int y = pCoordenada.getY();
		boolean esta=false;
		if((x>=0 && x<10)&&(y>=0 && y<10)){
			esta=true;
		}
		return esta;
	}
	public ArrayList<Casilla> obtenerBarcosEncontradosRadar(Coordenada pCoordenada){
		int x = pCoordenada.getX()-1;
		int y = pCoordenada.getY()-1;
		ArrayList<Casilla> resultado = new ArrayList<Casilla>();
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++) {
				if(this.estaEnTablero(new Coordenada(x+i,y+j))){
					Casilla objetivo = this.tablero[x+i][y+j];
					if (objetivo.comprobarEstado().equals("Barco")) {
						resultado.add(objetivo);
						return resultado;
					}
				}
				
			}
		}
		return resultado;
	}
	public ArrayList<Coordenada> obtenerCasillasAlRededor(Coordenada pCoord){
		int x = pCoord.getX()-1;
		int y = pCoord.getY()-1;
		ArrayList<Coordenada> resultado = new ArrayList<Coordenada>();
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++) {
				if(this.estaEnTablero(new Coordenada(x+i,y+j))){
					if((i+j)%2!=0){
					Coordenada objetivo = this.tablero[x+i][y+j].getPosicion();
					resultado.add(objetivo);
					}	
				}
			}
		}
		return resultado;
	}
}
