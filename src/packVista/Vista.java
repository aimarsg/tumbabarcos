package packVista;

import packModelo.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import packModelo.Coordenada;
import packModelo.Flota;
import packModelo.Modelo;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.sound.sampled.Line;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import java.awt.Font;

public class Vista extends JFrame implements Observer {

	private JPanel contentPane;
	private Controler controler = null;
	private MouseControler mouseC = null;
	private JPanel flotaJugador;
	private JPanel flotaOrdenador;
	private JRadioButton Destructores;
	private JRadioButton PortaAviones;
	private JRadioButton Submarinos;
	private JRadioButton Fragatas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton bombaBt;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel texto;
	private JLabel marcadorU;
	private JLabel marcadorO;
	//private JLabel texto2;
	private ArrayList<JLabel> labelsUsuario;
	private ArrayList<JLabel> labelsIA;
	private JRadioButton Seleccion;
	private JCheckBox horizontal;
	private JCheckBox vertical;
	private int barcosU, barcosO;
	private static Coordenada coordClickadaUsuario;
	private static Coordenada coordClickadaOrdenador;
	private static JLabel labelClicadoUsuario;
	private static JLabel labelClicadoOrdenador;
	private boolean enHorizontal = true;
	private JPanel panel;
	private JButton misilBt;
	

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Vista frame = new Vista();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Vista() {
		Modelo.getModelo().getFlotaUsuario().addObserver(this);
		Modelo.getModelo().getFlotaOrdenador().addObserver(this);
		Modelo.getModelo().addObserver(this);
		this.labelsUsuario = new ArrayList<JLabel>();
		this.labelsIA = new ArrayList<JLabel>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2, 0, 0));

		flotaJugador = new JPanel();
		contentPane.add(flotaJugador);
		flotaJugador.setLayout(new GridLayout(11, 11, 0, 0));

		flotaOrdenador = new JPanel();
		contentPane.add(flotaOrdenador);
		flotaOrdenador.setLayout(new GridLayout(11, 11, 0, 0));

		JPanel PanelBarcos = new JPanel();
		contentPane.add(PanelBarcos);
		PanelBarcos.setLayout(new GridLayout(3, 2, 0, 0));

		PortaAviones = new JRadioButton("PortaAviones");
		PortaAviones.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(PortaAviones);
		PanelBarcos.add(PortaAviones);
		PortaAviones.addActionListener(getControler());

		Submarinos = new JRadioButton("Submarino");
		Submarinos.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(Submarinos);
		PanelBarcos.add(Submarinos);
		Submarinos.addActionListener(getControler());

		Destructores = new JRadioButton("Destructores");
		Destructores.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(Destructores);
		PanelBarcos.add(Destructores);
		Destructores.addActionListener(getControler());

		Fragatas = new JRadioButton("Fragatas");
		Fragatas.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(Fragatas);
		PanelBarcos.add(Fragatas);
		Fragatas.addActionListener(getControler());

		/*
		 * Seleccion = new JRadioButton("Vertical"); PanelBarcos.add(Seleccion);
		 * Seleccion.addActionListener(getControler());
		 */

		vertical = new JCheckBox("Vertical");
		vertical.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		PanelBarcos.add(vertical);
		buttonGroup_1.add(vertical);
		vertical.addActionListener(getControler());

		horizontal = new JCheckBox("horizontal");
		horizontal.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		PanelBarcos.add(horizontal);
		horizontal.setSelected(true);
		buttonGroup_1.add(horizontal);
		horizontal.addActionListener(getControler());

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(4, 1, 0, 0));
		
		panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		bombaBt = new JButton("Disparar Bomba ");
		bombaBt.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		bombaBt.setBackground(Color.WHITE);
		bombaBt.setForeground(new Color(255, 0, 0));
		bombaBt.addActionListener(getControler());
		
		panel.add(bombaBt);
		
		misilBt = new JButton("Disparar Misil");
		misilBt.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		misilBt.setForeground(Color.RED);
		misilBt.setBackground(Color.LIGHT_GRAY);
		panel.add(misilBt);
		misilBt.addActionListener(getControler());
		misilBt.setVisible(false);

		texto = new JLabel();
		texto.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		panel_3.add(texto);

		barcosO=10;
		barcosU=10;		
		
<<<<<<< Upstream, based on origin/master
		marcadorO = new JLabel("Barcos restantes del Ordenador: "+barcosO);
=======
		marcadorO = new JLabel("Barcos restantes del ordenador: "+barcosO);
		marcadorO.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
>>>>>>> edc9abe Visita
		marcadorO.setVisible(false);
		panel_3.add(marcadorO);
<<<<<<< Upstream, based on origin/master
		marcadorU = new JLabel("Barcos restantes del Usuario: "+barcosU);
=======
		marcadorU = new JLabel("Barcos restantes del ordenador: "+barcosU);
		marcadorU.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
>>>>>>> edc9abe Visita
		marcadorU.setVisible(false);
		panel_3.add(marcadorU);
		crearButtons();
	}

	private Iterator<JLabel> getIteradorUsuario() {
		return this.labelsUsuario.iterator();
	}

	/*
	 * private JButton cbt(int i, int j) { //parámetros de entrada
	 * 
	 * 
	 * JButton btnNewButton = new JButton(); //texto del botón
	 * btnNewButton.addActionListener(getControler()); return btnNewButton; }
	 */
	private JLabel cbt(boolean usuario) { // parámetros de entrada
		JLabel nuevo = new JLabel();
		nuevo.setBorder(BorderFactory.createLineBorder(Color.white));
		nuevo.setOpaque(true);
		nuevo.setBackground(Color.cyan);
		// anadir a arraylist
		if (usuario) {
			labelsUsuario.add(nuevo);
		} else {
			labelsIA.add(nuevo);
		}
		nuevo.addMouseListener(getMouseControler());
		return nuevo;
	}

	private JLabel clbl(int pT) {
		JLabel lblNewLabel;
		if (pT == 0) {
			lblNewLabel = new JLabel("  ");
		} else {
			lblNewLabel = new JLabel("" + pT);
		}
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return lblNewLabel;
	}

	private JLabel clblLetras(int pL) {
		char asci = (char) pL;
		String ascii = String.valueOf(asci);
		JLabel lblNewLabel = new JLabel(ascii);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		return lblNewLabel;
	}

	private void crearButtons() {
		int i, j;
		for (i = 0; i < 11; i++) {
			for (j = 0; j < 11; j++) {
				if (i == 0 && j == 0) {
					flotaJugador.add(clbl(0), BorderLayout.CENTER, i * 11 + j);
					flotaOrdenador.add(clbl(0), BorderLayout.CENTER, i * 11 + j);
				} else {
					if (i == 0 && j > 0) {
						flotaJugador.add(clbl(j), BorderLayout.CENTER, i * 11 + j);
						flotaOrdenador.add(clbl(j), BorderLayout.CENTER, i * 11 + j);
					} else {
						if (j == 0 && i > 0) {
							flotaJugador.add(clblLetras(i + 64), BorderLayout.CENTER, i * 11 + j);
							flotaOrdenador.add(clblLetras(i + 64), BorderLayout.CENTER, i * 11 + j);
						} else {
							flotaJugador.add(cbt(true), BorderLayout.CENTER, i * 11 + j);
							flotaOrdenador.add(cbt(false), BorderLayout.CENTER, i * 11 + j);
						}
					}
				}
			}
		}
	}

	public void update(Observable arg0, Object arg1) { // arg2 es un boolean para indicar que es la flota del ordenador
		
		if (arg1 instanceof Object[]) {

			// SObject o1 =(Object[])arg1;

			Object[] array = (Object[]) arg1;
			String b = (String) array[1];

			ArrayList<Casilla> lista = (ArrayList<Casilla>) array[0];
			// ArrayList<Casilla> lcasilla = lista[0];

			for (Casilla c : lista) {
				int x = c.getPosicion().getX();
				int y = c.getPosicion().getY();
				int index = x * 10 + y;
				//COLOCAR BARCOS
				if (b.equals("ColocarBarco")) {
					labelsUsuario.get(index).setBackground(Color.BLACK);
					coordClickadaUsuario = null;
					
				//HUNDIR BARCOS
				} else if (b.equals("HundirOrdenador")) {
					labelsIA.get(index).setBackground(Color.decode("#7e0000"));
					coordClickadaOrdenador = null;
				}else if(b.equals("HundirUsuario")) {
					labelsUsuario.get(index).setBackground(Color.decode("#7e0000"));
					
				//DISPARAR 
				} else if (b.equals("DispararAOrdenador")) {
					if (c.comprobarEstado().equals("Tocado")){
						labelsIA.get(index).setBackground(new Color(160, 60, 210));
					}
					else if(c.comprobarEstado().equals("Disparado")) {
						labelsIA.get(index).setBackground(Color.BLUE);
					}
					coordClickadaOrdenador = null;
				}else if (b.equals("DispararAUsuario")) {
					if (c.comprobarEstado().equals("Tocado")){
						labelsUsuario.get(index).setBackground(new Color(160, 60, 210));
					}
					else if(c.comprobarEstado().equals("Disparado")) {
						labelsUsuario.get(index).setBackground(Color.BLUE);
					}
				}
				
			}
			// actualizacion del marcador cuadno se hunden los barcos
			
			if (b.equals("HundirOrdenador")) {
				barcosO--;
				marcadorO.setText("Barcos restantes del Ordenador: "+barcosO);
			}else if(b.equals("HundirUsuario")) {
				barcosU--;
				marcadorU.setText("Barcos restantes del Usuario: "+barcosU);
			}
		
			
			
			buttonGroup.clearSelection();

		}

		// para mostrar texto
		if (arg1 instanceof String) {
			if (((String)arg1).equals("ActivarDisparar")){
				bombaBt.setVisible(true);
				misilBt.setVisible(true);
			}else if(((String)arg1).equals("ActivarMarcador")){
				marcadorO.setVisible(true);
				marcadorU.setVisible(true);
			}else{
				texto.setText((String) arg1);
			}
			
		}
		
		//disparado
		/*if (arg1 instanceof Casilla) {
			Casilla c = (Casilla) arg1;
			int x = c.getPosicion().getX();
			int y = c).getPosicion().getY();
		
			int index = x * 10 + y;
			//String estado = Modelo.getModelo().getFlotaOrdenador().obtenerEstadoCasilla(x,y);
			if (c.comprobarEstado().equals("Tocado")){
				labelsIA.get(index).setBackground(new Color(160, 60, 210));
			}
			else if(c.comprobarEstado().equals("Disparado")) {
				labelsIA.get(index).setBackground(Color.GREEN);
			}
			
		}*/
		
		

	}

	public void activarDisparar(){
		bombaBt.setVisible(true);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}

	private MouseControler getMouseControler() {
		if (mouseC == null) {
			mouseC = new MouseControler();
		}
		return mouseC;
	}

	private Controler getControler() {
		if (controler == null) {
			controler = new Controler();
		}
		return controler;
	}

	private class MouseControler implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {

			JLabel l = (JLabel) e.getSource();
			int pos = labelsUsuario.indexOf(l);
			String estadoClickado = null;
			// Usuario
			if (pos != -1) {
				
					int x = (pos / 10);
					int y = (pos % 10);
					
					// la coordenada anterior
					if (coordClickadaUsuario != null) { // el anterior (la primera vez que se clica va a ser null)
						estadoClickado = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(coordClickadaUsuario.getX(),coordClickadaUsuario.getY());
					
						if (labelClicadoUsuario != null && !estadoClickado.equals("Barco") && !estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado") && !estadoClickado.equals("Hundido")) { // poner casilla anterior clicada en azul si no se ha convertido en barco
							labelClicadoUsuario.setBackground(Color.cyan);
						}
					}
					
					// el nuevo click
					labelClicadoUsuario = l;
					coordClickadaUsuario = new Coordenada(x, y);
					estadoClickado = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(coordClickadaUsuario.getX(),coordClickadaUsuario.getY());
					System.out.println("X:" + y);
					System.out.println("Y:" + x);
					//System.out.println("Posicion clicada: " + pos);
					if (!estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado") && !estadoClickado.equals("Hundido") && !estadoClickado.equals("Barco")) {
						l.setBackground(new Color(48, 225, 80));
					}
			}

			// Ordenador
			else {
					pos = labelsIA.indexOf(l);
					int x = (pos / 10);
					int y = (pos % 10);
					//el anterior
					if (coordClickadaOrdenador != null) {
						estadoClickado = Modelo.getModelo().getFlotaOrdenador().obtenerEstadoCasilla(coordClickadaOrdenador.getX(), coordClickadaOrdenador.getY());
						//
						if (labelClicadoOrdenador != null &&!estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado") && !estadoClickado.equals("Hundido")) {
							labelClicadoOrdenador.setBackground(Color.cyan);
						}
					}
					
					labelClicadoOrdenador = l;
					coordClickadaOrdenador = new Coordenada(x, y);
					estadoClickado = Modelo.getModelo().getFlotaOrdenador().obtenerEstadoCasilla(coordClickadaOrdenador.getX(),coordClickadaOrdenador.getY());
					
					System.out.println("X:" + y);
					System.out.println("Y:" + x);
					//System.out.println("Posicion clicada: " + pos);
					//
					if (!estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado") && !estadoClickado.equals("Hundido")) { //cuando solo sea agua se puede seleccionar
						l.setBackground(new Color(255, 70, 70));
					}					
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			int pos = labelsUsuario.indexOf(l);

			// Usuario
			if (pos != -1) {
				int X = pos / 10;
				int Y = pos % 10;
				String estadoCasilla = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(X, Y);
				if (coordClickadaUsuario != null) {
					if (estadoCasilla.equals("Agua") && (coordClickadaUsuario.getX() != X || coordClickadaUsuario.getY() != Y)) {
						//l.setBackground(Color.lightGray);
						l.setBackground(new Color(170, 222, 190));
						// System.out.println("Posicion entrada: " + pos);
					}
				} else {//cuando no hay nada clicado
					if (estadoCasilla.equals("Agua")) {
						l.setBackground(new Color(170, 222, 190));
					}
				}
			}

			// Ordenador
			else {
				pos = labelsIA.indexOf(l);
				if (pos != -1) {
					int X = pos / 10;
					int Y = pos % 10;
					String estadoCasilla = Modelo.getModelo().getFlotaOrdenador().obtenerEstadoCasilla(X, Y);
					if (coordClickadaOrdenador != null) {
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado")&& !estadoCasilla.equals("Hundido") && (coordClickadaOrdenador.getX() != X || coordClickadaOrdenador.getY() != Y)) {
						l.setBackground(new Color(230, 150, 150));
							// System.out.println("Posicion entrada: " + pos);
						}
					} else { 
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado")&& !estadoCasilla.equals("Hundido")) {
						l.setBackground(new Color(255, 150, 150));
						// System.out.println("Posicion entrada: " + pos);
					
						}
					}
			
			}
		}
		}
		@Override
		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			int pos = labelsUsuario.indexOf(l);

			// Usuario
			if (pos != -1) {
				int X = pos / 10;
				int Y = pos % 10;
				String estadoCasilla = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(X, Y);
				if (coordClickadaUsuario != null) {
					if (estadoCasilla.equals("Agua") && (coordClickadaUsuario.getX() != X || coordClickadaUsuario.getY() != Y)) {
						l.setBackground(Color.cyan);
						// System.out.println("Posicion entrada: " + pos);
					}
				} else {
					if (estadoCasilla.equals("Agua")) {
						l.setBackground(Color.cyan);
					}
					// System.out.println("Posicion entrada: " + pos);
				}
			}

			// Ordenador
			else {
				pos = labelsIA.indexOf(l);
				if (pos != -1) {
					int X = pos / 10;
					int Y = pos % 10;
					String estadoCasilla = Modelo.getModelo().getFlotaOrdenador().obtenerEstadoCasilla(X, Y);
					if (coordClickadaOrdenador != null) {//
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado") && !estadoCasilla.equals("Hundido") && (coordClickadaOrdenador.getX() != X || coordClickadaOrdenador.getY() != Y)) {
							l.setBackground(Color.cyan);
							// System.out.println("Posicion entrada: " + pos);
						}
					} else {
					if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado")&& !estadoCasilla.equals("Hundido")) {
						l.setBackground(Color.cyan);
						// System.out.println("Posicion entrada: " + pos);
					}
				}
			}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}


	private class Controler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(PortaAviones)) {
				System.out.println("Se ha pulsado un portaAviones ");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				//anadir if para que cuando no haya nada clicado no se ejecute
				flotaU.colocarBarcos(coordClickadaUsuario, "PortaAviones", enHorizontal);  
			}
			if (e.getSource().equals(Submarinos)) {
				System.out.println("Se ha pulsado un submarino ");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				flotaU.colocarBarcos(coordClickadaUsuario, "Submarino", enHorizontal);
			}
			if (e.getSource().equals(Destructores)) {
				System.out.println("Se ha pulsado destructores");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				flotaU.colocarBarcos(coordClickadaUsuario, "Destructor", enHorizontal);
			}
			if (e.getSource().equals(Fragatas)) {
				System.out.println("Se ha pulsado una fragata");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				flotaU.colocarBarcos(coordClickadaUsuario, "Fragata", enHorizontal);
			}
			if (e.getSource().equals(bombaBt)) {
				//System.out.println("SE llama a disparar con la coordenada x "+coordClickada.getX()+ " y "+coordClickada.getY());
				Flota flotaO = Modelo.getModelo().getFlotaOrdenador();
				
				boolean disparado=flotaO.disparar(coordClickadaOrdenador, true);
				if (disparado){
					bombaBt.setVisible(false);
					Modelo.getModelo().getFlotaUsuario().setDisparadoUsuario();
				}

			}

			if (e.getSource().equals(horizontal)) {
				enHorizontal = true;
			}
			if (e.getSource().equals(vertical)) {
				enHorizontal = false;
			}
		}

	}
}
