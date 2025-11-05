package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class Ventana3 extends JFrame{
	private JPanel pNorte, pSur, pOeste, pCentro;
	private JLabel lblLogo;
	private JButton btnGeneral, btnBuscar, btnMiCuenta, btnCerrarSesion;	
	
	public Ventana3() {
		setTitle("BilboBnB");
		setExtendedState(MAXIMIZED_BOTH);
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,120);
		getContentPane().setBackground(turquesa);
		
		//Creamos los paneles
		pNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pSur = new JPanel();
		pOeste = new JPanel(new GridLayout(4,1,0,0));
		pOeste.setPreferredSize(new Dimension(200,0));
		pCentro = new JPanel();
		
		pNorte.setBackground(Color.WHITE);
		
		//Creación de componentes
		ImageIcon icono = new ImageIcon("resources/images/logoBilboBnBTransparenteMasPequeno.jpg");
		Image imagenEscalada = icono.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagenEscalada));
		
		btnGeneral = new JButton("General");
		btnGeneral.setBackground(Color.WHITE);
		btnGeneral.setForeground(coral);
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setForeground(coral);
		btnMiCuenta = new JButton("Mi cuenta");
		btnMiCuenta.setBackground(Color.WHITE);
		btnMiCuenta.setForeground(coral);
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setForeground(coral);
		
		//Añadimos paneles a ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		//Añadimos componentes a los paneles
		pNorte.add(lblLogo);
		pOeste.add(btnGeneral);
		pOeste.add(btnBuscar);
		pOeste.add(btnMiCuenta);
		pOeste.add(btnCerrarSesion);
		
		//Listeners de los botones
		btnGeneral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnGeneral.setBackground(Color.WHITE);
				btnGeneral.setForeground(coral);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGeneral.setBackground(coral);
				btnGeneral.setForeground(Color.WHITE);
				
			}
		});
		
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(Color.WHITE);
				btnBuscar.setForeground(coral);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(coral);
				btnBuscar.setForeground(Color.WHITE);
				
			}
		});
		
		btnMiCuenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnMiCuenta.setBackground(Color.WHITE);
				btnMiCuenta.setForeground(coral);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMiCuenta.setBackground(coral);
				btnMiCuenta.setForeground(Color.WHITE);
				
			}
		});
		
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnCerrarSesion.setBackground(Color.WHITE);
				btnCerrarSesion.setForeground(coral);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCerrarSesion.setBackground(coral);
				btnCerrarSesion.setForeground(Color.WHITE);
				
			}
		});
		
		btnCerrarSesion.addActionListener((e) -> {
			Ventana3.this.setVisible(false);
			Ventana1 ventanaInicio = new Ventana1();
			ventanaInicio.setVisible(true);
		});
	}
	
}
