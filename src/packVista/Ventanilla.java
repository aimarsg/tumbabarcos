package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Ventanilla extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventanilla frame = new Ventanilla();
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
	public Ventanilla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JButton iniciarBtn = new JButton("INICIAR NUEVA PARTIDA");
		iniciarBtn.setBounds(75, 142, 223, 82);
		iniciarBtn.setBackground(new Color(0, 255, 102));
		iniciarBtn.setForeground(Color.BLACK);
		
		JButton salirBtn = new JButton("SALIR DEL JUEGO");
		salirBtn.setBounds(395, 141, 223, 82);
		salirBtn.setBackground(new Color(255, 51, 51));
		panel.setLayout(null);
		panel.add(iniciarBtn);
		panel.add(salirBtn);
	}

}
