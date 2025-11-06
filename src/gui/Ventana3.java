package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana3 extends JFrame{
	private JPanel pNorte, pSur, pOeste, pCentro;
	private JLabel lblLogo;
	private JButton btnGeneral, btnBuscar, btnPerfil, btnCerrarSesion;	
	
	public Ventana3() {
		setTitle("BilboBnB");
		setExtendedState(MAXIMIZED_BOTH);
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,120);
		getContentPane().setBackground(turquesa);
		
		//Creamos los paneles
		pNorte = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
		pNorte.setBackground(Color.WHITE);
		pSur = new JPanel();
		pOeste = new JPanel(new GridLayout(4,1,0,0));
		pOeste.setPreferredSize(new Dimension(200,0));
		pCentro = new JPanel(new CardLayout());
		
		JPanel panelGeneral = new PanelGeneral();
		JPanel panelBuscar = new PanelBuscar();
		JPanel panelPerfil = new PanelPerfil();
		
		//Creación de componentes
		ImageIcon icono = new ImageIcon("resources/images/logoBilboBnBTransparenteMasPequeno.jpg");
		Image imagenEscalada = icono.getImage().getScaledInstance(240, 120, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagenEscalada));
		
		btnGeneral = new JButton("General");
		btnGeneral.setBackground(Color.WHITE);
		btnGeneral.setForeground(coral);
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setForeground(coral);
		btnPerfil = new JButton("Mi cuenta");
		btnPerfil.setBackground(Color.WHITE);
		btnPerfil.setForeground(coral);
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setForeground(coral);
		
		//Añadimos paneles a ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pOeste, BorderLayout.WEST);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//Añadimos componentes a los paneles
		pNorte.add(lblLogo);
		pOeste.add(btnGeneral);
		pOeste.add(btnBuscar);
		pOeste.add(btnPerfil);
		pOeste.add(btnCerrarSesion);
		
		pCentro.add(panelGeneral, "GENERAL");
		pCentro.add(panelBuscar, "BUSCAR");
		pCentro.add(panelPerfil, "PERFIL");
		CardLayout cardLayout= (CardLayout) pCentro.getLayout();
		
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
		
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				btnPerfil.setBackground(Color.WHITE);
				btnPerfil.setForeground(coral);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPerfil.setBackground(coral);
				btnPerfil.setForeground(Color.WHITE);
				
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
		
		btnGeneral.addActionListener((e) -> {
			cardLayout.show(pCentro, "GENERAL");
		});
		
		btnBuscar.addActionListener((e) -> {
			cardLayout.show(pCentro, "BUSCAR");
		});
		
		btnPerfil.addActionListener((e) -> {
			cardLayout.show(pCentro, "PERFIL");
		});
		
		btnCerrarSesion.addActionListener((e) -> {
			int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres cerrar sesión?", "CERRANDO SESIÓN...", JOptionPane.YES_NO_CANCEL_OPTION);
			if (opcion==JOptionPane.YES_OPTION) {
				Ventana3.this.setVisible(false);
				Ventana1 ventanaInicio = new Ventana1();
				ventanaInicio.setVisible(true);
			}
		});
		
		setVisible(true);
	}
}
