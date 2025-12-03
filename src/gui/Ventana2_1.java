package gui;

import java.awt.BorderLayout;
import domain.Usuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import db.BD;

public class Ventana2_1 extends JFrame {
	private JPanel pSur, pCentro, pCentroConLogo, pCentroRegistrarse, pNorte;
	private JLabel lblTitulo, lblNombre, lblContrasena, lblRegistrarse;
	private JButton btnIniciarSesion, btnRegistrarse, btnVolver;
	private JTextField txtEmail;
	private JPasswordField txtContrasena;
	
	public Ventana2_1() {
		setTitle("BilboBnB - Acceso");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		getContentPane().setBackground(Funciones.Colores.Turquesa);
		
		
		//Creación de paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2,2,10,10));
		pCentro.setBorder(BorderFactory.createEmptyBorder(0, 240, 240, 240));
		pCentro.setBackground(Funciones.Colores.Turquesa);
		pCentroConLogo = new JPanel();
		pCentroConLogo.setBackground(Funciones.Colores.Turquesa);
		pCentroConLogo.setLayout(new BorderLayout());
		setLayout(new BorderLayout(0,20)); 
		pCentroRegistrarse = new JPanel();
		pCentroRegistrarse.setBackground(Funciones.Colores.Turquesa);

		
		//Cración de componentes
		btnIniciarSesion = new JButton("INICIAR SESIÓN");
		btnIniciarSesion.setForeground(Funciones.Colores.Coral);
		btnIniciarSesion.setBackground(Color.WHITE);
		//btnIniciarSesion.setBorder(new LineBorder(coral));
		btnRegistrarse = new JButton("REGISTRARSE");
		btnRegistrarse.setForeground(Funciones.Colores.Coral);
		btnRegistrarse.setBackground(Color.WHITE);
		//btnRegistrarse.setBorder(new LineBorder(coral));
		btnVolver = new JButton("VOLVER");
		btnVolver.setForeground(Funciones.Colores.Coral);
		btnVolver.setBackground(Color.WHITE);
		//btnVolver.setBorder(new LineBorder(coral));
		
		lblTitulo = new JLabel("BIENVENIDO", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
		
		ImageIcon icono = new ImageIcon("resources/images/ImagenTrans1.png");
		Image imagenEscalada = icono.getImage().getScaledInstance(320, 320, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagenEscalada));
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblLogo.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		lblNombre = new JLabel("USUARIO/EMAIL: ", JLabel.CENTER);
		lblNombre.setFont(new Font("Segoe UI", Font.BOLD,20));
		lblContrasena = new JLabel("CONTRASEÑA: ", JLabel.CENTER);
		lblContrasena.setFont(new Font("Segoe UI", Font.BOLD,20));
		txtEmail = new JTextField(1);
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtEmail.setHorizontalAlignment(JTextField.CENTER);
		txtContrasena = new JPasswordField(1);
		txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtContrasena.setHorizontalAlignment(JTextField.CENTER);
		lblRegistrarse = new JLabel("NO TIENES CUENTA? REGISTRATE HOY", JLabel.CENTER);
		lblRegistrarse.setFont(new Font("Segoe UI", Font.BOLD,20));
		lblRegistrarse.setForeground(Funciones.Colores.Coral);
		Font f = lblRegistrarse.getFont().deriveFont(
			    Collections.singletonMap(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON)
			);
		lblRegistrarse.setFont(f);
		
		
		//Añadir paneles a ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentroConLogo,BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		//Añadir componentes a los paneles
		pNorte.add(lblTitulo);
		pCentroConLogo.add(lblLogo, BorderLayout.NORTH);
		pCentro.add(lblNombre);
		pCentro.add(txtEmail);
		pCentro.add(lblContrasena);
		pCentro.add(txtContrasena);
		pCentroRegistrarse.add(lblRegistrarse);
		pCentroConLogo.add(pCentro, BorderLayout.CENTER);
		pCentroConLogo.add(pCentroRegistrarse, BorderLayout.SOUTH);
		pSur.add(btnIniciarSesion);
		pSur.add(btnRegistrarse);
		pSur.add(btnVolver);

		//Listeners de los componentes
		btnIniciarSesion.addActionListener((e) -> {
			String email = txtEmail.getText();
			String contrasena = new String(txtContrasena.getPassword());
			
			if(BD.validarLogin(email, contrasena)) {
				Ventana2_1.this.setVisible(false);
				new Ventana3();
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		
		btnRegistrarse.addActionListener((e) -> {
			Ventana2_2 nuevaVentana = new Ventana2_2();
			nuevaVentana.setAlwaysOnTop(true);
			nuevaVentana.setLocationRelativeTo(Ventana2_1.this);
		});
		
		btnVolver.addActionListener((e) -> {
			Ventana2_1.this.setVisible(false);
			Ventana1 nuevaVentana = new Ventana1();
			nuevaVentana.setVisible(true);
		});
		
		lblRegistrarse.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				lblRegistrarse.setForeground(Funciones.Colores.Coral.darker());
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistrarse.setForeground(Funciones.Colores.Coral);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Ventana2_2 nuevaVentana = new Ventana2_2();
				nuevaVentana.setAlwaysOnTop(true);
				nuevaVentana.setLocationRelativeTo(Ventana2_1.this);
			}
		});
		
		KeyAdapter intro = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            btnIniciarSesion.doClick(); 
		        }
		    }
		};
		
		txtEmail.addKeyListener(intro);
		txtContrasena.addKeyListener(intro);
		
		Funciones.botonBonito(btnIniciarSesion, Funciones.Colores.Coral);
		Funciones.botonBonito(btnRegistrarse, Funciones.Colores.Coral);
		Funciones.botonBonito(btnVolver, Funciones.Colores.Coral);
		
		setVisible(true);
	}
}
