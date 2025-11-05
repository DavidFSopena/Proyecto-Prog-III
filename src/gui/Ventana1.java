package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana1 extends JFrame{
		private JPanel pCentro, pSur;
		private JButton btnSalir;
		private JLabel lblImagen;
		
		public Ventana1 () {
			
			pCentro = new JPanel();
			
			
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setUndecorated(true);
			
			Color TurquesaC = new Color(96, 198, 194);
			getContentPane().setBackground(TurquesaC);
			
			pCentro = new JPanel();
			pCentro.setOpaque(false);
			pCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			ImageIcon ruta = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
			Image img = ruta.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(img);
			lblImagen = new JLabel(icono);
			
			lblImagen.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e ) {
					ImageIcon iconoIluminado = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
					Image iconoZoom = iconoIluminado.getImage().getScaledInstance(630, 520, Image.SCALE_SMOOTH);
					lblImagen.setIcon(new ImageIcon(iconoZoom));
				}
				
				public void mouseExited(MouseEvent e) {
					ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
					Image iconoZoom = iconoNormal.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
					lblImagen.setIcon(new ImageIcon(iconoZoom));
				}
				
				public void mouseClicked(MouseEvent e) {
					Ventana1.this.setVisible(false);
					Ventana2_1 nuevaVentana = new Ventana2_1();
					nuevaVentana.setVisible(true);
				}
			});
			
			pCentro.add(lblImagen);
			add(pCentro, BorderLayout.CENTER);
			
			pSur = new JPanel();
			pSur.setOpaque(false);
			pSur.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			
			
			JButton btnSalir = new JButton("Salir");
			btnSalir.setFont(new Font("Arial", Font.BOLD, 20));
			btnSalir.setForeground(new Color(240, 80, 80));
			btnSalir.setBackground(Color.WHITE);
			
			
			add(pSur, BorderLayout.SOUTH);
			
			
			setVisible(true);
		}
		
		
		
}


