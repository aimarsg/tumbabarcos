package packModelo;

import java.util.Random;

public class Ordenador extends Jugador {

	public Ordenador() {
		super();
	}

	public boolean jugarTurno() {
		boolean sigue =true;
		Random randomizer = new Random();
		boolean jugado=false;
		if (getFlota().getNumBarcos()!=0){
			setChanged();
			notifyObservers(" --- TURNO DEL ORDENADOR --- ");
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int col, fil;
			while(!jugado) {
				//System.out.println("TURNO DEL ORDENADOR---------------------");
				col= randomizer.nextInt(10);
				fil = randomizer.nextInt(10);
				int armaAleatoria = randomizer.nextInt(2);
				String[] armas = {"bomba","misil"};
				System.out.println(" disparado en fila "+(col +1)+", col "+(1+fil));
				jugado = Modelo.getModelo().getFlotaUsuario().disparar(new Coordenada(col, fil), false,armas[armaAleatoria]);
				System.out.println(jugado);
			}
			try {
				Thread.sleep(750);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			sigue=false;
		}
		return sigue;
	}

	public void colocarBarcosOrdenador() {
		//this.inicializarFlota();
		Random randomizer = new Random();
		int col = 0;
		int fil = 0;
		boolean colocado;
		boolean horizontal;
	
		for (Barco b : this.getFlota().getBarcosIniciales()) {
			colocado = false;
			while (!colocado) {
				col= randomizer.nextInt(10);
				fil = randomizer.nextInt(10);
				horizontal = randomizer.nextBoolean();
				colocado = b.colocarBarco(new Coordenada(col, fil), horizontal, this.getTablero());
				this.getFlota().anadirBarcoColocado(b);
				//barcosColocados.add(b);				
			}
			//listaBarcos = new ArrayList<>();
			System.out.println(b.getNombre()+" colocado en fila "+(col +1)+", col "+(1+fil)+" ");
			System.out.println();
			//setChanged();
			//notifyObservers(new Object[] {b.getCasillas(), false});
			
		}
	}

}