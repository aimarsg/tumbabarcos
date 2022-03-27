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
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class Vista extends JFrame implements Observer{

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
	private JTextField textField;
	private ArrayList<JLabel> labelsUsuario ;
	private ArrayList<JLabel> labelsIA;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		Modelo.getModelo().getFlotaUsuario().addObserver(this);
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
		PanelBarcos.setLayout(new GridLayout(2, 2, 0, 0));
		
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
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		Disparar = new JButton("Disparar");
		panel_3.add(Disparar);
		Disparar.addActionListener(getControler());
		
		textField = new JTextField();
		panel_3.add(textField);
		textField.setColumns(10);
		
		
		crearButtons();
	}

	private Iterator<JLabel> getIteradorUsuario(){
		return this.labelsUsuario.iterator();
	}

	
	
		
	
	/*private JButton cbt(int i, int j) { //par�metros de entrada
        JButton btnNewButton = new JButton(); //texto del bot�n
      btnNewButton.addActionListener(getControler());
        return btnNewButton;
        }
	*/
	private JLabel cbt(boolean usuario) { //par�metros de entrada
		JLabel nuevo = new JLabel();
		nuevo.setBorder(BorderFactory.createLineBorder(Color.white));
		nuevo.setOpaque(true);
		nuevo.setBackground(Color.cyan);
		//anadir a arraylist
		if (usuario) {
			labelsUsuario.add(nuevo);
		}else {
			labelsIA.add(nuevo);
		}
		nuevo.addMouseListener(getMouseControler());
		return nuevo;
	}
	
	private JLabel clbl(int pT) {
		 JLabel lblNewLabel;
		if (pT==0) {
			lblNewLabel = new JLabel("  ");
		}else {
			lblNewLabel = new JLabel(""+pT);
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
        int i,j;
        for(i=0; i<11;i++) {
            for(j=0; j<11;j++) {
            	if(i==0 && j==0) {
            		flotaJugador.add(clbl(0),BorderLayout.CENTER,i*11+j);
            		flotaOrdenador.add(clbl(0),BorderLayout.CENTER,i*11+j);
            	}else {
            	if(i==0 && j>0) {
            		flotaJugador.add(clbl(j),BorderLayout.CENTER,i*11+j);
            		flotaOrdenador.add(clbl(j),BorderLayout.CENTER,i*11+j);
            	}else {
            		if(j==0 && i>0) {
            			flotaJugador.add(clblLetras(i+64),BorderLayout.CENTER,i*11+j);
                		flotaOrdenador.add(clblLetras(i+64),BorderLayout.CENTER,i*11+j);
            		}else {
            			flotaJugador.add(cbt( true), BorderLayout.CENTER, i*11+j);
            			flotaOrdenador.add(cbt( false), BorderLayout.CENTER, i*11+j);
            		}
            	}
            }
           }
        }
    }

	public void update(Observable arg0, Object pCord) {
		System.out.println("ha entrado");

		if (pCord instanceof Coordenada) {
			System.out.println("ha entrado");
			int x = ((Coordenada)pCord).getX();
			int y = ((Coordenada)pCord).getY();
			int index = x*10+y;
			labelsUsuario.get(index).setBackground(Color.BLACK);
			System.out.println(index);
			PortaAviones.setSelected(false);
		}
		//if (texto instanceof String) {
			//textField.setText(texto);
		//}
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
		if (mouseC== null){
			mouseC =  new MouseControler();
		}
		return mouseC;
	}
	
	
	private Controler getControler() {
		if(controler==null) {
			controler = new Controler();
		}
		return controler;
	}
	private class MouseControler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
				int pos= labelsUsuario.indexOf(l);
				l.setBackground(Color.BLACK);
				System.out.println("Posicion clicada: " + pos);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			int pos= labelsUsuario.indexOf(l);
			l.setBackground(Color.BLACK);
			System.out.println("Posicion clicada: " + pos);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			int pos= labelsUsuario.indexOf(l);
			int X = pos/10;
			int Y = pos%10;
			String estadoCasilla = Modelo.getModelo().getFlotaUsuario().obtenerEstadoCasilla(X, Y);
			
			if (estadoCasilla != "Barco") {
				l.setBackground(Color.cyan);
				System.out.println("Posicion clicada: " + pos);
			
		
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
	private class Controler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(PortaAviones)) {
				System.out.println("Se ha ");
				Flota flotaU=Modelo.getModelo().getFlotaUsuario();
				Coordenada nueva = new Coordenada(1,2);
				flotaU.colocarBarcos(nueva, "PortaAviones", true);
			}
			if(e.getSource().equals(Submarinos)) {
				System.out.println("Se ha a�adidio submarino ");
				Flota flotaU=Modelo.getModelo().getFlotaUsuario();
				Coordenada nueva = new Coordenada(3,2);
				flotaU.colocarBarcos(nueva, "PortaAviones", true);
			}
			if(e.getSource().equals(Destructores)) {
				System.out.println("Se ha pulsado destructores");
				Flota flotaU=Modelo.getModelo().getFlotaUsuario();
				Coordenada nueva = new Coordenada(5,2);
				flotaU.colocarBarcos(nueva, "PortaAviones", true);
			}
			if(e.getSource().equals(Fragatas)) {
				System.out.println("Se ha a�adidio una fragata");
				Flota flotaU=Modelo.getModelo().getFlotaUsuario();
				Coordenada nueva = new Coordenada(4,2);
				flotaU.colocarBarcos(nueva, "PortaAviones", true);
			}
			if(e.getSource().equals(Disparar)) {
				System.out.println("Se ha a�adidio Disparado");
				Flota flotaO=Modelo.getModelo().getFlotaOrdenador();
				Coordenada nueva = new Coordenada(5,6);
				flotaO.disparar(nueva);
				/*
				Coordenada de pega para hacer pruebas
				
				*/
				
			}
			if ( e.getSource() instanceof JLabel) {
				
				}
			}
		}
}
