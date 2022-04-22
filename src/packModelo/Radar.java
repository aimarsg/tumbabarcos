package packModelo;
import java.util.Random;
import java.util.ArrayList;

public class Radar extends Arma  {

	private int numConsultas;
	private Coordenada ubicacion;

	public Radar(double pPrecio, int pConsultas) {
		super(pPrecio);
		//ubicacion= pUbicacion;
		numConsultas= pConsultas;
		arma= new ActRadar();
	}


	public Coordenada mover() {
		Random randomizer = new Random();
		int X = randomizer.nextInt(10);
		int Y = randomizer.nextInt(10);

		Coordenada coord = new Coordenada(X, Y);
		ubicacion = coord;
		return ubicacion;
	}

	public Coordenada getUbi() {
		return ubicacion;
	}

	public void activarRadar() {
		// TODO - implement Radar.activarRadar
		throw new UnsupportedOperationException();
	}

	public ArrayList<Casilla> consultar(String jugador) {
		ArrayList<Casilla> reconocimiento = new ArrayList<Casilla>();
		if (numConsultas != 0) {
			Jugador objetivo;
			if (jugador.equals("Usuario")) {
				objetivo = Modelo.getModelo().getOrdenador();
			}else{
				objetivo = Modelo.getModelo().getUsuario();
			}
			reconocimiento = objetivo.obtenerBarcosEncontradosRadar(ubicacion);

			numConsultas--;
		}else{
			// no quedan consultas
		}
		return reconocimiento;
	}
	public int getNumConsultas() {
		return this.numConsultas;
	}

}