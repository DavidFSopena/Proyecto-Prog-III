package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ventana2_1 extends JFrame {
	private JPanel pSur, pCentro, pNorte;
	private JLabel lblTitulo, lblNombre, lblContrasena;
	private JButton btnIniciarSesion, btnRegistrarse, btnCancelar;
	private JTextField txtNombre;
	private JPasswordField txtContrasena;
	
	private JFrame ventanaActual;
	
	public Ventana2_1() {
		setBounds(400,200,600,400);
		setTitle("Registro e inicio de sesión");
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,102);
		getContentPane().setBackground(turquesa);
		
		
		//Creación de paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2, 2, 2, 10));
		pCentro.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));
		pCentro.setBackground(turquesa);
		setLayout(new BorderLayout(0,20)); 
		
		//Cración de componentes
		btnIniciarSesion = new JButton("INICIAR SESIÓN");
		btnRegistrarse = new JButton("REGISTRARSE");
		btnCancelar = new JButton("SALIR");
		btnIniciarSesion.setBackground(coral);
		btnRegistrarse.setBackground(coral);
		btnCancelar.setBackground(coral);
		
		lblTitulo = new JLabel("BIENVENIDO", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
		
		lblNombre = new JLabel("Introduce tu nombre: ", JLabel.CENTER);
		lblContrasena = new JLabel("Introduce tu contraseña: ", JLabel.CENTER);
		txtNombre = new JTextField(10);
		txtContrasena = new JPasswordField(10);
		
		//Añadir paneles a ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//Añadir componentes a los paneles
		pNorte.add(lblTitulo); 
		pCentro.add(lblNombre);
		pCentro.add(txtNombre);
		pCentro.add(lblContrasena);
		pCentro.add(txtContrasena);
		pSur.add(btnIniciarSesion);
		pSur.add(btnRegistrarse);
		pSur.add(btnCancelar);

		
		//Listeners de los componentes
		
		setVisible(true);
	}
}
