package packVista;

import packModelo.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class Vista extends JFrame implements Observer {

	private JPanel contentPane;
	private Controler controler = null;
	private MouseControler mouseC = null;
	private JPanel flotaJugador;
	private JPanel ordenadorrdenador;
	private JRadioButton Destructores;
	private JRadioButton PortaAviones;
	private JRadioButton Submarinos;
	private JRadioButton Fragatas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton dispararBtn;
	private JButton colocarRad;
	private JButton colocarAut;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel texto;
	private JLabel marcadorU;
	private JLabel marcadorO;
	// private JLabel texto2;
	private ArrayList<JLabel> labelsUsuario;
	private ArrayList<JLabel> labelsIA;
	//private JRadioButton Seleccion;
	private JCheckBox horizontal;
	private JCheckBox vertical;
	private int barcosU, barcosO;
	private static Coordenada coordClickadaUsuario;
	private static Coordenada coordClickadaOrdenador;
	private static JLabel labelClicadoUsuario;
	private static JLabel labelClicadoOrdenador;
	private boolean enHorizontal = true;
	private JPanel panel;
	//private JPanel panel_colocar;
	private JButton comprarBtn;
	private JPanel panel_1;
	private JPanel PanelBarcos; 
	private JRadioButton BombaBtn;
	private JRadioButton misilBtn;
	private JRadioButton escudoBtn;
	private JRadioButton radarBtn;
	private final ButtonGroup grupoDispararComprar = new ButtonGroup();

	private JButton repararBtn;


	//panel abajo
	private JLabel saldo;
	private JLabel udsMisil;
	private JLabel udsRadar;
	private JLabel udsEscudo;

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
		Modelo.getModelo().getUsuario().addObserver(this);
		Modelo.getModelo().getOrdenador().addObserver(this);
		Modelo.getModelo().addObserver(this);
		Almacen.getAlmacen().addObserver(this);
		
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

		ordenadorrdenador = new JPanel();
		contentPane.add(ordenadorrdenador);
		ordenadorrdenador.setLayout(new GridLayout(11, 11, 0, 0));

		PanelBarcos = new JPanel();
		contentPane.add(PanelBarcos);
		PanelBarcos.setLayout(new GridLayout(4, 2, 0, 0));
		
		

		PortaAviones = new JRadioButton("PortaAviones (1)");
		PortaAviones.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(PortaAviones);
		PanelBarcos.add(PortaAviones);
		PortaAviones.addActionListener(getControler());

		Submarinos = new JRadioButton("Submarino (2)");
		Submarinos.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(Submarinos);
		PanelBarcos.add(Submarinos);
		Submarinos.addActionListener(getControler());

		Destructores = new JRadioButton("Destructores (3)");
		Destructores.setFont(new Font("Source Code Pro Light", Font.PLAIN, 19));
		buttonGroup.add(Destructores);
		PanelBarcos.add(Destructores);
		Destructores.addActionListener(getControler());

		Fragatas = new JRadioButton("Fragatas (4)");
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
		
		colocarAut = new JButton("Colocar automaticamente");
		colocarAut.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		colocarAut.setBackground(Color.WHITE);
		colocarAut.setForeground(Color.BLACK);
		colocarAut.addActionListener(getControler());
		PanelBarcos.add(colocarAut);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(5, 1, 0, 0));

		panel = new JPanel();
		panel_3.add(panel);
		panel.setLayout(new GridLayout(1, 2, 0, 0));

		dispararBtn = new JButton("Utilizar");
		dispararBtn.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		dispararBtn.setBackground(Color.WHITE);
		dispararBtn.setForeground(new Color(255, 0, 0));
		dispararBtn.addActionListener(getControler());
		//dispararBtn.setVisible(false);

		panel.add(dispararBtn);

		comprarBtn = new JButton("Comprar");
		comprarBtn.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		comprarBtn.setForeground(Color.RED);
		comprarBtn.setBackground(Color.WHITE);
		comprarBtn.addActionListener(getControler());
		//comprarBtn.setVisible(false);

		panel.add(comprarBtn);
		
		colocarRad = new JButton("Mover radar");
		colocarRad.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		colocarRad.setForeground(Color.RED);
		colocarRad.setBackground(Color.WHITE);
		colocarRad.addActionListener(getControler());
		//colocarRad.setVisible(false);

		panel.add(colocarRad);
		
		repararBtn = new JButton("Repara Barco");
		repararBtn.setBackground(Color.WHITE);
		repararBtn.setFont(new Font("Source Code Pro Light", Font.PLAIN, 16));
		repararBtn.setForeground(Color.RED);
		panel.add(repararBtn);
		panel.setVisible(false);
		repararBtn.addActionListener(getControler());
		
		panel_1 = new JPanel();
		panel_3.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 4, 0, 0));

		BombaBtn = new JRadioButton("BOMBA");
		grupoDispararComprar.add(BombaBtn);
		panel_1.add(BombaBtn);

		misilBtn = new JRadioButton("MISIL ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Misil")+")");
		grupoDispararComprar.add(misilBtn);
		panel_1.add(misilBtn);

		escudoBtn = new JRadioButton("ESCUDO ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Escudo")+")");
		grupoDispararComprar.add(escudoBtn);
		panel_1.add(escudoBtn);

		radarBtn = new JRadioButton("RADAR ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Radar")+")");
		grupoDispararComprar.add(radarBtn);
		panel_1.add(radarBtn);
		panel_1.setVisible(false);

		texto = new JLabel();
		texto.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		panel_3.add(texto);
		
		

		barcosO = 10;
		barcosU = 10;

		marcadorO = new JLabel("Barcos restantes del Ordenador: " + barcosO);
		marcadorO.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
		marcadorO.setVisible(false);
		panel_3.add(marcadorO);
		marcadorU = new JLabel("Barcos restantes del Usuario: " + barcosU);
		marcadorU.setFont(new Font("Mongolian Baiti", Font.PLAIN, 14));
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
		JLabel nuevo = new JLabel("", SwingConstants.CENTER);
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
					ordenadorrdenador.add(clbl(0), BorderLayout.CENTER, i * 11 + j);
				} else {
					if (i == 0 && j > 0) {
						flotaJugador.add(clbl(j), BorderLayout.CENTER, i * 11 + j);
						ordenadorrdenador.add(clbl(j), BorderLayout.CENTER, i * 11 + j);
					} else {
						if (j == 0 && i > 0) {
							flotaJugador.add(clblLetras(i + 64), BorderLayout.CENTER, i * 11 + j);
							ordenadorrdenador.add(clblLetras(i + 64), BorderLayout.CENTER, i * 11 + j);
						} else {
							flotaJugador.add(cbt(true), BorderLayout.CENTER, i * 11 + j);
							ordenadorrdenador.add(cbt(false), BorderLayout.CENTER, i * 11 + j);
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
				// COLOCAR BARCOS
				if (b.equals("ColocarBarco")) {
					labelsUsuario.get(index).setBackground(Color.BLACK);
					coordClickadaUsuario = null;

					// HUNDIR BARCOS
				} else if (b.equals("HundirOrdenador")) {
					labelsIA.get(index).setBackground(Color.decode("#7e0000"));
					Sonido.getMiSonido().ReproducirSonido("Resources/Impacto_misil.wav");
					coordClickadaOrdenador = null;
				} else if (b.equals("HundirUsuario")) {
					labelsUsuario.get(index).setBackground(Color.decode("#7e0000"));
					Sonido.getMiSonido().ReproducirSonido("Resources/Impacto_misil.wav");

					// DISPARAR
				} else if (b.equals("DispararAOrdenador")) {
					if (c.comprobarEstado().equals("Tocado")) {
						labelsIA.get(index).setBackground(new Color(160, 60, 210));
						Sonido.getMiSonido().ReproducirSonido("Resources/Impacto_Arma.wav");
					} else if (c.comprobarEstado().equals("Disparado")) {
						labelsIA.get(index).setBackground(Color.BLUE);
						Sonido.getMiSonido().ReproducirSonido("Resources/0008368.wav");
						
					}
					coordClickadaOrdenador = null;
				} else if (b.equals("DispararAUsuario")) {
					if (c.comprobarEstado().equals("Tocado")) {
						labelsUsuario.get(index).setBackground(new Color(160, 60, 210));
						Sonido.getMiSonido().ReproducirSonido("Resources/Impacto_Arma.wav");
					} else if (c.comprobarEstado().equals("Disparado")) {
						labelsUsuario.get(index).setBackground(Color.BLUE);
						Sonido.getMiSonido().ReproducirSonido("Resources/0008368.wav");
						
					}
				}
				else if (b.equals("EscudoUsuario")) {// cuando se dispara
				
					labelsUsuario.get(index).setBackground(Color.orange);
					Sonido.getMiSonido().ReproducirSonido("Resources/Impacto_Arma.wav");
				}
				else if (b.equals("EscudoOrdenador")) {//cuando se dispara
				
					labelsIA.get(index).setBackground(Color.orange);
					Sonido.getMiSonido().ReproducirSonido("Resources/Impacto_Arma.wav");
				}
				else if (b.equals("PintarEscudo")) {
				
					labelsUsuario.get(index).setBackground(Color.yellow);
					Sonido.getMiSonido().ReproducirSonido("Resources/Escudo.wav");
					
				}
				else if (b.equals("ConsultaRadar")){
					JLabel casilla = labelsIA.get(index);
					casilla.setText("X");
					casilla.setFont(new Font(casilla.getFont().getName(), Font.PLAIN, 18));
					casilla.setForeground(Color.RED);
					
				}
				else if(b.equals("VerRadar")){
					JLabel casilla = labelsIA.get(index);
					casilla.setText("¤");
					casilla.setFont(new Font(casilla.getFont().getName(), Font.PLAIN, 24));
					casilla.setForeground(Color.RED);
				}
				else if(b.equals("QuitarRadar")){
					JLabel casilla = labelsIA.get(index);
					if (!casilla.getText().equals("X")){
						casilla.setText(null);
						casilla.setFont(new Font(casilla.getFont().getName(), Font.PLAIN, 24));
					}
					
					//casilla.setForegroud(Color.GREEN);
				
				
				}
				else if(b.equals("RepararBarcoOrdenador")){
					labelsIA.get(index).setBackground(Color.cyan);
				}
				

			}
			// actualizacion del marcador cuadndo se hunden los barcos

			if (b.equals("HundirOrdenador")) {
				barcosO--;
				marcadorO.setText("Barcos restantes del Ordenador: " + barcosO);
			} else if (b.equals("HundirUsuario")) {
				barcosU--;
				marcadorU.setText("Barcos restantes del Usuario: " + barcosU);
			}

			buttonGroup.clearSelection();
			//buttonGroup_1.clearSelection();
			//BombaBtn.isSelected();

		}

		// para mostrar texto
		if (arg1 instanceof String) {
			if (((String) arg1).equals("ActivarDisparar")) {
				panel.setVisible(true);
				panel_1.setVisible(true);
			} else if (((String) arg1).equals("ActivarMarcador")) {
				marcadorO.setVisible(true);
				marcadorU.setVisible(true);
				//colocarAut.setVisible(false);
				colocarRad.setVisible(true);
				//PanelBarcos.setVisible(false);
				//panelAlmacen.setVisible(true);
				this.intercambioDePaneles(); //quita lo de los barcos y pone lo del almacen y lo de comprar
			} else if (((String) arg1).equals("PortaAviones"))  {
				String barco = (String) arg1;
			
				int num = Character.getNumericValue((PortaAviones.getText().charAt(PortaAviones.getText().length() - 2)));
				
				PortaAviones.setText("PortaAviones (" + (num - 1) + ")");
			} else if (((String) arg1).equals("Submarino"))  {
				String barco = (String) arg1;
			
				int num = Character.getNumericValue((Submarinos.getText().charAt(Submarinos.getText().length() - 2)));
				
				Submarinos.setText("Submarinos (" + (num - 1) + ")");
			} else if (((String) arg1).equals("Destructor"))  {
				String barco = (String) arg1;
			
				int num = Character.getNumericValue((Destructores.getText().charAt(Destructores.getText().length() - 2)));
				
				Destructores.setText("Destructores (" + (num - 1) + ")");
			}else if (((String) arg1).equals("Fragata"))  {
				String barco = (String) arg1;
			
				int num = Character.getNumericValue((Fragatas.getText().charAt(Fragatas.getText().length() - 2)));
				
				Fragatas.setText("Fragatas (" + (num - 1) + ")");
			}else if(((String) arg1).equals("Comprado")){
				udsRadar.setText(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Radar")));
				udsEscudo.setText(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Escudo")));
				udsMisil.setText(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Misil")));
			}
			else {
				texto.setText((String) arg1);
			}
			

		}
		/*
		if (arg1 instanceof Coordenada) {
			Coordenada c = (Coordenada) arg1;
			int x = c.getX();
			int y = c.getY();
			int index = x * 10 + y;
			JLabel casilla = labelsIA.get(index);
			casilla.setText("¤");
			casilla.setFont(new Font(casilla.getFont().getName(), Font.PLAIN, 24));
			casilla.setForegroud(Color.GREEN);
		}
		*/

		// disparado
		/*
		 * if (arg1 instanceof Casilla) { Casilla c = (Casilla) arg1; int x =
		 * c.getPosicion().getX(); int y = c).getPosicion().getY();
		 * 
		 * int index = x * 10 + y; //String estado =
		 * Modelo.getModelo().getOrdenador().obtenerEstadoCasilla(x,y); if
		 * (c.comprobarEstado().equals("Tocado")){ labelsIA.get(index).setBackground(new
		 * Color(160, 60, 210)); } else if(c.comprobarEstado().equals("Disparado")) {
		 * labelsIA.get(index).setBackground(Color.GREEN); }
		 * 
		 * }
		 */

	}

	public void activarDisparar() {
		dispararBtn.setVisible(true);
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
					estadoClickado = Modelo.getModelo().getUsuario()
							.obtenerEstadoCasilla(coordClickadaUsuario.getX(), coordClickadaUsuario.getY());

					if (labelClicadoUsuario != null && !estadoClickado.equals("Barco")
							&& !estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado")
							&& !estadoClickado.equals("Hundido") && !estadoClickado.equals("Escudo")) { // poner casilla anterior clicada en azul si no se
																	// ha convertido en barco
						labelClicadoUsuario.setBackground(Color.cyan);
					}
				}

				// el nuevo click
				labelClicadoUsuario = l;
				coordClickadaUsuario = new Coordenada(x, y);
				estadoClickado = Modelo.getModelo().getUsuario().obtenerEstadoCasilla(coordClickadaUsuario.getX(),
						coordClickadaUsuario.getY());
				System.out.println("X:" + y);
				System.out.println("Y:" + x);
				// System.out.println("Posicion clicada: " + pos);
				if (!estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado")
						&& !estadoClickado.equals("Hundido") && !estadoClickado.equals("Barco") && !estadoClickado.equals("Escudo")) {
					l.setBackground(new Color(48, 225, 80));
				}
			}

			// Ordenador
			else {
				pos = labelsIA.indexOf(l);
				int x = (pos / 10);
				int y = (pos % 10);
				// el anterior
				if (coordClickadaOrdenador != null) {
					estadoClickado = Modelo.getModelo().getOrdenador().obtenerEstadoCasilla(coordClickadaOrdenador.getX(), coordClickadaOrdenador.getY());
					if (labelClicadoOrdenador != null && !estadoClickado.equals("Tocado")
							&& !estadoClickado.equals("Disparado") && !estadoClickado.equals("Hundido") && !labelClicadoOrdenador.getBackground().equals(Color.orange)) {
						labelClicadoOrdenador.setBackground(Color.cyan);
					}
				}

				labelClicadoOrdenador = l;
				coordClickadaOrdenador = new Coordenada(x, y);
				estadoClickado = Modelo.getModelo().getOrdenador()
						.obtenerEstadoCasilla(coordClickadaOrdenador.getX(), coordClickadaOrdenador.getY());

				System.out.println("X:" + y);
				System.out.println("Y:" + x);
				// System.out.println("Posicion clicada: " + pos);
				//
				if (!estadoClickado.equals("Tocado") && !estadoClickado.equals("Disparado") && !l.getBackground().equals(Color.orange)
						&& !estadoClickado.equals("Hundido")) { // cuando solo sea agua se puede seleccionar
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
				String estadoCasilla = Modelo.getModelo().getUsuario().obtenerEstadoCasilla(X, Y);
				if (coordClickadaUsuario != null) {
					if (estadoCasilla.equals("Agua")
							&& (coordClickadaUsuario.getX() != X || coordClickadaUsuario.getY() != Y)) {
						// l.setBackground(Color.lightGray);
						l.setBackground(new Color(170, 222, 190));
						// System.out.println("Posicion entrada: " + pos);
					}
				} else {// cuando no hay nada clicado
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
					String estadoCasilla = Modelo.getModelo().getOrdenador().obtenerEstadoCasilla(X, Y);
					if (coordClickadaOrdenador != null) {
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado")
								&& !estadoCasilla.equals("Hundido") && !l.getBackground().equals(Color.orange)
								
								&& (coordClickadaOrdenador.getX() != X || coordClickadaOrdenador.getY() != Y)) {
							l.setBackground(new Color(230, 150, 150));
							// System.out.println("Posicion entrada: " + pos);
						}
					} else {
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado") && !l.getBackground().equals(Color.orange)
								&& !estadoCasilla.equals("Hundido")) {
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
				String estadoCasilla = Modelo.getModelo().getUsuario().obtenerEstadoCasilla(X, Y);
				if (coordClickadaUsuario != null) {
					if (estadoCasilla.equals("Agua") 
							&& (coordClickadaUsuario.getX() != X || coordClickadaUsuario.getY() != Y)) {
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
					String estadoCasilla = Modelo.getModelo().getOrdenador().obtenerEstadoCasilla(X, Y);
					if (coordClickadaOrdenador != null) {//
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado") && !l.getBackground().equals(Color.orange)
								&& !estadoCasilla.equals("Hundido")
								&& (coordClickadaOrdenador.getX() != X || coordClickadaOrdenador.getY() != Y)) {
							l.setBackground(Color.cyan);
							// System.out.println("Posicion entrada: " + pos);
						}
					} else {
						if (!estadoCasilla.equals("Tocado") && !estadoCasilla.equals("Disparado") && !l.getBackground().equals(Color.orange)
								&& !estadoCasilla.equals("Hundido")) {
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
			Usuario usuario = (Usuario)Modelo.getModelo().getUsuario();
			Ordenador ordenador = (Ordenador)Modelo.getModelo().getOrdenador();
			boolean finTurno = false;
			
			//PARA COLOCAR LOS BARCOS
			if (e.getSource().equals(PortaAviones)) {
				System.out.println("Se ha pulsado un portaAviones ");
				// anadir if para que cuando no haya nada clicado no se ejecute
				
				usuario.colocarBarco(coordClickadaUsuario, "PortaAviones", enHorizontal);
			}
			if (e.getSource().equals(Submarinos)) {
				System.out.println("Se ha pulsado un submarino ");
				usuario.colocarBarco(coordClickadaUsuario, "Submarino", enHorizontal);
			}
			if (e.getSource().equals(Destructores)) {
				System.out.println("Se ha pulsado destructores");
				usuario.colocarBarco(coordClickadaUsuario, "Destructor", enHorizontal);
			}
			if (e.getSource().equals(Fragatas)) {
				System.out.println("Se ha pulsado una fragata");
				usuario.colocarBarco(coordClickadaUsuario, "Fragata", enHorizontal);
			}
			if (e.getSource().equals(horizontal)) {
				enHorizontal = true;
			}
			if (e.getSource().equals(vertical)) {
				enHorizontal = false;
			}
			if (e.getSource().equals(colocarAut)) {
				((Usuario)Modelo.getModelo().getUsuario()).pruebasColocarBarcos();
			}
			//PARA REPARAR BARCO DE USUARIO
			if (e.getSource().equals(repararBtn)){
				if (coordClickadaUsuario!=null) {
					finTurno = usuario.repararBarco(coordClickadaUsuario);
					if(finTurno){
						saldo.setText(Double.toString(usuario.getSaldo()));
					}
				}
			}
			
			//PARA DISPARAR
			if (e.getSource().equals(dispararBtn)) {
				// System.out.println("SE llama a disparar con la coordenada x
				// "+coordClickada.getX()+ " y "+coordClickada.getY());
				if (BombaBtn.isSelected()) {
					finTurno = usuario.disparar("Bomba", coordClickadaOrdenador);
					if (finTurno) {
						saldo.setText(Double.toString(usuario.getSaldo()));
					}
					
				} else if (misilBtn.isSelected()) {
					finTurno = usuario.disparar("Misil", coordClickadaOrdenador);
					if (finTurno) {
						misilBtn.setText("MISIL ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Misil")+")");
						saldo.setText(Double.toString(usuario.getSaldo()));
					}
					
				} else if (escudoBtn.isSelected()) {
					Escudo escudo=(Escudo)usuario.buscarArma("Escudo");
					if (coordClickadaUsuario != null) {
						if (escudo!=null){
							if (usuario.colocarEscudo(coordClickadaUsuario,escudo)) {
								texto.setText("Escudo colocado protegiendo tu barco ;)");
								((Usuario)Modelo.getModelo().getUsuario()).setDisparadoUsuario();
								System.out.println("se ha utilizado un escudo");
								finTurno = true;
								escudoBtn.setText("ESCUDO ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Escudo")+")");
								//usuario.eliminarArma("Escudo");
							}else {
								//NO SE HA UTILIZADO EL ESCUDO 					
							}
						}else {
						//no hay armas de tipo escudo
						texto.setText("No te quedan escudos, utiliza otra arma o compra uno!");
						System.out.println("no quedan escudos");
						}
					}else {
						System.out.println("no hay nada clickado");		
						texto.setText("no hay ninguna casilla clicada ");
					}
				} else if (radarBtn.isSelected()) {
					finTurno = usuario.consultarRadar();
					if (finTurno) {
						radarBtn.setText("RADAR ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Radar")+")");
					}
					
				}
			}
			//PARA COMPRAR
			if (e.getSource().equals(comprarBtn)) {
				if (BombaBtn.isSelected()) {
					usuario.comprarArma("Bomba");
					
					
				} else if (misilBtn.isSelected()) {
					usuario.comprarArma("Misil");
					udsMisil.setText(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Misil")));
					misilBtn.setText("MISIL ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Misil")+")");
				} else if (escudoBtn.isSelected()) {
					usuario.comprarArma("Escudo");
					udsEscudo.setText(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Escudo")));
					escudoBtn.setText("ESCUDO ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Escudo")+")");
				} else if (radarBtn.isSelected()) {
					usuario.comprarArma("Radar");
					udsRadar.setText(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Radar")));
					radarBtn.setText("RADAR ("+((Usuario)Modelo.getModelo().getUsuario()).cantidadArmasTipo("Radar")+")");
				}
				saldo.setText(Double.toString(usuario.getSaldo()));
			}
			//PADRA MOVER EL ESCUDO
			if (e.getSource().equals(colocarRad)) {
				usuario.moverRadar();
			}
			
			//cuando se ha disparado / comprado ya para que se detecte que se ha acabado el turno
			if (finTurno) {
				
				panel.setVisible(false);
				panel_1.setVisible(false);
				//
				((Usuario)Modelo.getModelo().getUsuario()).setDisparadoUsuario();

			}	
		}
		
		
	}
	
	private void intercambioDePaneles() {
		//este metodo pretende cambiar el panel de colocar los barcos por el de almacen
		
		PanelBarcos.removeAll();
		PanelBarcos.setLayout(new GridLayout(5, 2, 0, 0));
		PanelBarcos.updateUI();
		
		//saldo
		JLabel textoSaldo = new JLabel("Saldo restante del usuario: ");
		saldo = new JLabel(Double.toString(((Usuario) Modelo.getModelo().getUsuario()).getSaldo()));
		PanelBarcos.add(textoSaldo);
		PanelBarcos.add(saldo);
		//
		JLabel titulo1 = new JLabel("ARMAS EN ALMACEN      PRECIO ");
		JLabel titulo2 = new JLabel("STOCK DISPONIBLE");
		PanelBarcos.add(titulo1);
		PanelBarcos.add(titulo2);
		//misil
		JLabel textoMisil = new JLabel ("MISIL.                         Precio: 200.");
		udsMisil = new JLabel(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Misil")));
		PanelBarcos.add(textoMisil);
		PanelBarcos.add(udsMisil);
		//radar
		JLabel textoRadar = new JLabel ("RADAR.                       Precio: 150.");
		udsRadar = new JLabel(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Radar")));
		PanelBarcos.add(textoRadar);
		PanelBarcos.add(udsRadar);
		//escudo
		JLabel textoEscudo = new JLabel("ESCUDO.                    Precio: 100.");
		udsEscudo = new JLabel(Integer.toString(Almacen.getAlmacen().devolverCantArmas("Escudo")));
		PanelBarcos.add(textoEscudo);
		PanelBarcos.add(udsEscudo);
	}
}
