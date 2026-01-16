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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Ventana1 extends JFrame{
		private JPanel pCentro, pSur;
		private JButton btnSalir;
		private JLabel lblImagen;
		
		public Ventana1 () {
			
			Funciones.aplicarIcono(this);
			
			setUndecorated(false);
			
			pCentro = new JPanel();
			
			getContentPane().setBackground(Funciones.Colores.Turquesa);
			setLayout(new BorderLayout());
			
			pCentro = new JPanel();
			pCentro.setOpaque(false);
			pCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			
			ImageIcon ruta = new ImageIcon("resources/images/ImagenTrans1.png");
			Image img = ruta.getImage().getScaledInstance(660, 550, Image.SCALE_SMOOTH);
			ImageIcon icono = new ImageIcon(img);
			lblImagen = new JLabel(icono);
			lblImagen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			lblImagen.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e ) {
					ImageIcon iconoIluminado = new ImageIcon(getClass().getResource("/images/ImagenTrans1.png"));
					Image iconoZoom = iconoIluminado.getImage().getScaledInstance(680, 570, Image.SCALE_SMOOTH);
					lblImagen.setIcon(new ImageIcon(iconoZoom));
				}
				
				public void mouseExited(MouseEvent e) {
					ImageIcon iconoNormal = new ImageIcon(getClass().getResource("/images/ImagenTrans1.png"));
					Image iconoZoom = iconoNormal.getImage().getScaledInstance(660, 550, Image.SCALE_SMOOTH);
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
			pSur.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
			
			
			//IAG (herramienta: Chatgpt)
			//Adaptado (hemos modificado los valores pero el action listener esta hecho por IA)
			
			JButton btnSalir = new JButton("Salir");
			btnSalir.setFont(Funciones.Letra.negrita(40));
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
			
			btnSalir.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
			});
			
			pSur.add(btnSalir);
			add(pSur, BorderLayout.SOUTH);
			
			
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
		}
}


