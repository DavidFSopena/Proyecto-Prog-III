package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
			pCentro.setOpaque(false);
			pCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			ImageIcon ruta = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
			Image img = ruta.getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(img);
			lblImagen = new JLabel(icono);
			
			lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			lblImagen.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					Ventana1.this.setVisible(false);
					Ventana3 nuevaVentana = new Ventana3();
					nuevaVentana.setVisible(true);
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			pCentro.add(lblImagen);
			add(pCentro, BorderLayout.CENTER);
			
			JButton botonImagen = new JButton(icono);
			
			setVisible(true);
		}
		
		
		
}


