package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
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

import javax.swing.BorderFactory;
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
	
	private JButton btnSeleccionado;
	
	public Ventana3() {
		
		Funciones.aplicarIcono(this);
		
		setTitle("BilboBnB");
		setExtendedState(MAXIMIZED_BOTH);
		
		getContentPane().setBackground(Funciones.Colores.Turquesa);
		
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
		btnGeneral.setForeground(Funciones.Colores.Coral);
		btnGeneral.setFocusPainted(false);
		btnGeneral.setFont(Funciones.Letra.negrita(18));
		btnGeneral.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setForeground(Funciones.Colores.Coral);
		btnBuscar.setFocusPainted(false);
		btnBuscar.setFont(Funciones.Letra.negrita(18));
		btnBuscar.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
		
		btnPerfil = new JButton("Mi cuenta");
		btnPerfil.setBackground(Color.WHITE);
		btnPerfil.setForeground(Funciones.Colores.Coral);
		btnPerfil.setFocusPainted(false);
		btnPerfil.setFont(Funciones.Letra.negrita(18));
		btnPerfil.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
		
		btnCerrarSesion = new JButton("Cerrar sesión");
		btnCerrarSesion.setBackground(Color.WHITE);
		btnCerrarSesion.setForeground(Funciones.Colores.Coral);
		btnCerrarSesion.setFocusPainted(false);
		btnCerrarSesion.setFont(Funciones.Letra.negrita(18));
		btnCerrarSesion.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
		
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
				
				if(btnSeleccionado != btnGeneral) {
					
					btnGeneral.setBackground(Color.WHITE);
					btnGeneral.setForeground(Funciones.Colores.Coral);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if(btnSeleccionado != btnGeneral) {
					
					btnGeneral.setBackground(Funciones.Colores.Coral);
					btnGeneral.setForeground(Color.WHITE);
				}
			}
		});
		
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (btnSeleccionado != btnBuscar){
					
					btnBuscar.setBackground(Color.WHITE);
					btnBuscar.setForeground(Funciones.Colores.Coral);
					
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if (btnSeleccionado != btnBuscar){
					
					btnBuscar.setBackground(Funciones.Colores.Coral);
					btnBuscar.setForeground(Color.WHITE);
					
				}	
			}
		});
		
		btnPerfil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (btnSeleccionado != btnPerfil) {
					
					btnPerfil.setBackground(Color.WHITE);
					btnPerfil.setForeground(Funciones.Colores.Coral);
					}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if (btnSeleccionado != btnPerfil) {
					
				btnPerfil.setBackground(Funciones.Colores.Coral);
				btnPerfil.setForeground(Color.WHITE);
				}
				
			}
		});
		
		btnCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				
				if (btnSeleccionado != btnCerrarSesion) {
					
					btnCerrarSesion.setBackground(Color.WHITE);
					btnCerrarSesion.setForeground(Funciones.Colores.Coral);
					}	
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				if (btnSeleccionado != btnCerrarSesion) {
					
					btnCerrarSesion.setBackground(Funciones.Colores.Coral);
					btnCerrarSesion.setForeground(Color.WHITE);
				
			}
			}
		});
		
		btnGeneral.addActionListener((e) -> {
			cardLayout.show(pCentro, "GENERAL");
			seleccionarBoton(btnGeneral, Funciones.Colores.Coral);
		});
		
		btnBuscar.addActionListener((e) -> {
			cardLayout.show(pCentro, "BUSCAR");
			seleccionarBoton(btnBuscar, Funciones.Colores.Coral);
		});
		
		btnPerfil.addActionListener((e) -> {
			((PanelPerfil) panelPerfil).actualizarDatos();
			cardLayout.show(pCentro, "PERFIL");
			seleccionarBoton(btnPerfil, Funciones.Colores.Coral);
		});
		
		btnCerrarSesion.addActionListener((e) -> {
			seleccionarBoton(btnCerrarSesion, Funciones.Colores.Coral);
			int opcion = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres cerrar sesión?", "CERRANDO SESIÓN...", JOptionPane.YES_NO_OPTION);
			if (opcion==JOptionPane.YES_OPTION) {
				Ventana3.this.setVisible(false);
				Ventana1 ventanaInicio = new Ventana1();
				ventanaInicio.setVisible(true);
			}
		});
		
		seleccionarBoton(btnGeneral, Funciones.Colores.Coral);
		
		setVisible(true);
	}
	
	private void seleccionarBoton(JButton btn, Color coral) {
		JButton [] botones = {btnGeneral, btnBuscar, btnPerfil, btnCerrarSesion};
		for (JButton b : botones ) {
			b.setBackground(Color.WHITE);
			b.setForeground(coral);
		}
		
		btn.setBackground(coral);
		btn.setForeground(Color.WHITE);
		btnSeleccionado = btn;
	}
	
	
}
