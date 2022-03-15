package packModelo;

public class Coordenada {

	private int coordX;
	private int coordY;

	public Coordenada(int pX, int pY) {
		
		this.coordX = pX;
		this.coordY = pY;
	}

	public int getX() {
		return this.coordX;
	}

	public int getY() {
		return this.coordY;
	}

	public void setX(int pX) {
		this.coordX = pX;
	}

	public void setY(int pY) {
		this.coordY = pY;
	}

}