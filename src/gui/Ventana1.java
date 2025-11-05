package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.border.LineBorder;

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
			setLayout(new BorderLayout());
			
			pCentro = new JPanel();
			pCentro.setOpaque(false);
			pCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			ImageIcon ruta = new ImageIcon(getClass().getResource("/images/ImagenTrans.png"));
			Image img = ruta.getImage().getScaledInstance(570, 470, Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(img);
			lblImagen = new JLabel(icono);
			lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			JPanel contenedorCasa = new JPanel();
			contenedorCasa.setOpaque(true);
			contenedorCasa.setBackground(Color.WHITE);
			contenedorCasa.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			contenedorCasa.add(lblImagen);
			contenedorCasa.setPreferredSize(new Dimension(570, 470));
			
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
			
			pCentro.add(contenedorCasa);
			add(pCentro, BorderLayout.CENTER);
			
			pSur = new JPanel();
			pSur.setOpaque(false);
			pSur.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
			
			
			
			JButton btnSalir = new JButton("Salir");
			btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 40));
			btnSalir.setForeground(new Color(240, 80, 80));
			btnSalir.setPreferredSize(new Dimension(180, 70));
			btnSalir.setBackground(Color.WHITE);
			btnSalir.setFocusPainted(false);
			btnSalir.setBorder(new LineBorder(Color.RED, 2, true));
			btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			btnSalir.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					btnSalir.setBackground(new Color(240, 80, 80));
					btnSalir.setForeground(Color.WHITE);
				}
				
				public void mouseExited(MouseEvent e) {
					btnSalir.setBackground(Color.WHITE);
					btnSalir.setForeground(new Color(240, 80, 80));
				}
			});
			
			btnSalir.addActionListener(e -> System.exit(0));
			
			pSur.add(btnSalir);
			add(pSur, BorderLayout.SOUTH);
			
			
			setVisible(true);
		}
}


