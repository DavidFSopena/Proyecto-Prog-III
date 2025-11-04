package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
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
			setUndecorated(true);
			
			Color TurquesaC = new Color(96, 198, 194);
			getContentPane().setBackground(TurquesaC);
			
			pCentro = new JPanel();
			pCentro.setOpaque(true);
			pCentro.setLayout(new GridLayout());
			
			ImageIcon ruta = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
			Image img = ruta.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(img);
			lblImagen = new JLabel(icono);
			
			pCentro.add(lblImagen);
			add(pCentro, BorderLayout.CENTER);
			
			setVisible(true);
		}
		
		
		
}


