package gui;

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana1 extends JFrame{
		private JPanel pCentro, pSur;
		private JButton btnSeguir;
		private JLabel lblImagen;
		
		public Ventana1 () {
			
			pCentro = new JPanel();
			
			
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			ImageIcon icono = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
			Image img = icono.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
			icono = new ImageIcon(img);
			
			lblImagen = new JLabel(icono);
			pCentro.add(lblImagen);
		}
	
		
}


