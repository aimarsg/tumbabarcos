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
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

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
	private JButton Disparar;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel texto;
	private ArrayList<JLabel> labelsUsuario;
	private ArrayList<JLabel> labelsIA;
	private JRadioButton Seleccion;
	private JCheckBox horizontal;
	private JCheckBox vertical;

	private static Coordenada coordClickada;
	private static JLabel labelClicado;
	private boolean enHorizontal = true;

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
		buttonGroup.add(PortaAviones);
		PanelBarcos.add(PortaAviones);
		PortaAviones.addActionListener(getControler());

		Submarinos = new JRadioButton("Submarino");
		buttonGroup.add(Submarinos);
		PanelBarcos.add(Submarinos);
		Submarinos.addActionListener(getControler());

		Destructores = new JRadioButton("Destructores");
		buttonGroup.add(Destructores);
		PanelBarcos.add(Destructores);
		Destructores.addActionListener(getControler());

		Fragatas = new JRadioButton("Fragatas");
		buttonGroup.add(Fragatas);
		PanelBarcos.add(Fragatas);
		Fragatas.addActionListener(getControler());

		/*
		 * Seleccion = new JRadioButton("Vertical"); PanelBarcos.add(Seleccion);
		 * Seleccion.addActionListener(getControler());
		 */

		vertical = new JCheckBox("Vertical");
		PanelBarcos.add(vertical);
		buttonGroup_1.add(vertical);
		vertical.addActionListener(getControler());

		horizontal = new JCheckBox("horizontal");
		PanelBarcos.add(horizontal);
		horizontal.setSelected(true);
		buttonGroup_1.add(horizontal);
		horizontal.addActionListener(getControler());

		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(3, 1, 0, 0));

		Disparar = new JButton("Disparar");
		panel_3.add(Disparar);
		Disparar.addActionListener(getControler());

		texto = new JLabel();
		panel_3.add(texto);

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
		// System.out.println("ha entrado");

		// para pintar los cuadrados despues de colocar el barco
		/*
		 * if (arg1 instanceof ArrayList<?>) {
		 * 
		 * for (Casilla c : (ArrayList<Casilla>)arg1) { int x = c.getPosicion().getX();
		 * int y = c.getPosicion().getY(); int index = x*10+y;
		 * labelsUsuario.get(index).setBackground(Color.BLACK);
		 * 
		 * } buttonGroup.clearSelection();
		 * 
		 * }
		 */

		if (arg1 instanceof Object[]) {

			// SObject o1 =(Object[])arg1;

			Object[] array = (Object[]) arg1;
			boolean b = (boolean) array[1];

			ArrayList<Casilla> lista = (ArrayList<Casilla>) array[0];
			// ArrayList<Casilla> lcasilla = lista[0];

			for (Casilla c : lista) {
				int x = c.getPosicion().getX();
				int y = c.getPosicion().getY();
				int index = x * 10 + y;
				System.out.println("--------------index del barco " + index);
				if (b) {
					labelsUsuario.get(index).setBackground(Color.BLACK);
				} else {
					labelsIA.get(index).setBackground(Color.BLACK);
				}
			}

		}

		// para mostrar texto
		if (arg1 instanceof String) {
			texto.setText((String) arg1);
		}
		if (arg1 instanceof Coordenada) {
			int x = ((Coordenada) arg1).getX();
			int y = ((Coordenada) arg1).getY();
			int index = x * 10 + y;
			labelsIA.get(index).setBackground(Color.GREEN);
		}

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
					if (coordClickada != null) { // el anterior
						estadoClickado = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(coordClickada.getX(),
								coordClickada.getY());
					}

					if (labelClicado != null && !estadoClickado.equals("Barco")) {
						labelClicado.setBackground(Color.cyan);
					}
					
					// el nuevo click
					labelClicado = l;
					coordClickada = new Coordenada(x, y);
					estadoClickado = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(coordClickada.getX(),
							coordClickada.getY());
					System.out.println("X:" + y);
					System.out.println("Y:" + x);
					System.out.println("Posicion clicada: " + pos);
					if (!estadoClickado.equals("Barco")) {
						l.setBackground(Color.darkGray);
					}


			}

			// Ordenador
			else {
					pos = labelsIA.indexOf(l);
		
					int x = (pos / 10);
					int y = (pos % 10);
					//
	
					if (coordClickada != null) {
						estadoClickado = Modelo.getModelo().getFlotaOrdenador()
								.obtenerEstadoCasilla(coordClickada.getX(), coordClickada.getY());
					}
	
					if (labelClicado != null && !estadoClickado.equals("Barco")) {
						labelClicado.setBackground(Color.cyan);
					}
					labelClicado = l;
					coordClickada = new Coordenada(x, y);
					estadoClickado = Modelo.getModelo().getFlotaOrdenador().obtenerEstadoCasilla(coordClickada.getX(),
							coordClickada.getY());
					System.out.println("X:" + y);
					System.out.println("Y:" + x);
					System.out.println("Posicion clicada: " + pos);
					if (!estadoClickado.equals("Barco")) {
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
				if (coordClickada != null) {
					if (!estadoCasilla.equals("Barco") && (coordClickada.getX() != X || coordClickada.getY() != Y)) {
						l.setBackground(Color.lightGray);
						// System.out.println("Posicion entrada: " + pos);
					}
				} else {
					l.setBackground(Color.lightGray);
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
					if (coordClickada != null) {
						if (!estadoCasilla.equals("Barco")
								&& (coordClickada.getX() != X || coordClickada.getY() != Y)) {
							l.setBackground(new Color(230, 150, 150));
							// System.out.println("Posicion entrada: " + pos);
						}
					} else {
						l.setBackground(new Color(255, 150, 150));
						// System.out.println("Posicion entrada: " + pos);
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
				if (coordClickada != null) {
					if (!estadoCasilla.equals("Barco") && (coordClickada.getX() != X || coordClickada.getY() != Y)) {
						l.setBackground(Color.cyan);
						// System.out.println("Posicion entrada: " + pos);
					}
				} else {
					l.setBackground(Color.cyan);
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
					if (coordClickada != null) {///
						if (!estadoCasilla.equals("tocado") && (coordClickada.getX() != X || coordClickada.getY() != Y)) {
							l.setBackground(Color.cyan);
							// System.out.println("Posicion entrada: " + pos);
						}
					} else {
						l.setBackground(Color.cyan);
						// System.out.println("Posicion entrada: " + pos);
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
				flotaU.colocarBarcos(coordClickada, "PortaAviones", enHorizontal);
			}
			if (e.getSource().equals(Submarinos)) {
				System.out.println("Se ha pulsado un submarino ");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				flotaU.colocarBarcos(coordClickada, "Submarino", enHorizontal);
			}
			if (e.getSource().equals(Destructores)) {
				System.out.println("Se ha pulsado destructores");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				flotaU.colocarBarcos(coordClickada, "Destructor", enHorizontal);
			}
			if (e.getSource().equals(Fragatas)) {
				System.out.println("Se ha pulsado una fragata");
				Flota flotaU = Modelo.getModelo().getFlotaUsuario();
				flotaU.colocarBarcos(coordClickada, "Fragata", enHorizontal);
			}
			if (e.getSource().equals(Disparar)) {
				System.out.println("Se ha añadidio Disparado");
				Flota flotaO = Modelo.getModelo().getFlotaOrdenador();
				flotaO.disparar(coordClickada);

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
