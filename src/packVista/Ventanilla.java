package packVista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tumbabarcos.main;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Ventanilla extends JDialog {
	
	private static final long serialVersionUID = -6918195177272277457L;
	private ControlerVentanilla controlerVentanilla = null;
	private JPanel contentPanel;
	JButton salirBtn, iniciarBtn;

	/*public static void main(String args[]) {
		Ventanilla v = new Ventanilla();
	}*/
	
	public Ventanilla() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 661);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPanel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPanel.add(panel);
		
		 iniciarBtn = new JButton("INICIAR NUEVA PARTIDA");
		iniciarBtn.setBounds(75, 142, 223, 82);
		iniciarBtn.setBackground(new Color(0, 255, 102));
		iniciarBtn.setForeground(Color.BLACK);
		iniciarBtn.addActionListener(getControler());
		
		 salirBtn = new JButton("SALIR DEL JUEGO");
		salirBtn.setBounds(395, 141, 223, 82);
		salirBtn.setBackground(new Color(255, 51, 51));
		salirBtn.addActionListener(getControler());
		panel.setLayout(null);
		panel.add(iniciarBtn);
		panel.add(salirBtn);
		
		JLabel lblNewLabel = new JLabel("");
		panel_1.add(lblNewLabel);
		
		Icon icon = new ImageIcon("Resources/tumbabarcos.png");                
		if(icon != null){
    	//Agrega Icono
    		lblNewLabel.setIcon(icon);
		}
		setVisible(true);
	}

	private ControlerVentanilla getControler() {
		if (controlerVentanilla == null) {
			controlerVentanilla = new ControlerVentanilla();
		}
		return controlerVentanilla;
	}
	
	private class ControlerVentanilla implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(iniciarBtn)){
				//System.out.println("Click1!");
				
				//dispose();
				setVisible(false);
				Vista v = new Vista();
				main.iniciarPartida();
			
				
			}
			if (e.getSource().equals(salirBtn)){
				//System.out.println("Click2!");
				dispose();
			}
		}
	} 

}
