package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Vista extends JFrame implements Observer{

	private JPanel contentPane;
	private Controler controler = null;
	private JPanel flotaJugador;
	private JPanel flotaOrdenador;
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
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		crearButtons();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	private Controler getControler() {
		if(controler==null) {
			controler = new Controler();
		}
		return controler;
	}
	private class Controler implements ActionListener{
		public void actionPerfromed(ActionEvent e) {
			if(e.getSource().equals("Aqui hay que poner el nombre del boton")) {
				//aqui lo que pasa con el boton
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	private JButton cbt(int i, int j) { //par�metros de entrada
        JButton btnNewButton = new JButton(); //texto del bot�n
      btnNewButton.addActionListener(getControler());
        return btnNewButton;
        }
	
	
	private JLabel clbl(int pT) {
		 JLabel lblNewLabel;
		if (pT==0) {
			lblNewLabel = new JLabel("  ");
		}else {
			lblNewLabel = new JLabel("    "+pT);
		}   
       
		return lblNewLabel; 
	}
	private JLabel clblLetras(int pL) {
		char asci = (char) pL;
		String ascii = String.valueOf(asci);
		JLabel lblNewLabel = new JLabel("  "+ascii);
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
            			flotaJugador.add(cbt(i,j), BorderLayout.CENTER, i*11+j);
            			flotaOrdenador.add(cbt( i, j), BorderLayout.CENTER, i*11+j);
            		}
            	}
            }
           }
        }
    }

	

}
