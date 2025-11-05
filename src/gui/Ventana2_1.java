package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ventana2_1 extends JFrame {
	private JPanel pSur, pCentro, pCentroConLogo, pNorte;
	private JLabel lblTitulo, lblNombre, lblContrasena;
	private JButton btnIniciarSesion, btnRegistrarse, btnCancelar;
	private JTextField txtNombre;
	private JPasswordField txtContrasena;
	
	public Ventana2_1() {
		setBounds(400,200,600,400);
		setTitle("Registro e inicio de sesión");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,102);
		getContentPane().setBackground(turquesa);
		
		
		//Creación de paneles
		pNorte = new JPanel();
		pSur = new JPanel();
		pCentro = new JPanel();
		pCentro.setLayout(new GridLayout(2,2,10,15));
		pCentro.setBorder(BorderFactory.createEmptyBorder(250, 250, 250, 250));
		pCentro.setBackground(turquesa);
		pCentroConLogo = new JPanel();
		pCentroConLogo.setBackground(turquesa);
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
		
		ImageIcon icono = new ImageIcon("/images/ImagenTrans.png");
		Image imagenEscalada = icono.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		JLabel lblLogo = new JLabel(new ImageIcon(imagenEscalada));
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lblNombre = new JLabel("Introduce tu nombre: ", JLabel.CENTER);
		lblNombre.setFont(new Font("Arial", Font.BOLD,20));
		lblContrasena = new JLabel("Introduce tu contraseña: ", JLabel.CENTER);
		lblContrasena.setFont(new Font("Arial", Font.BOLD,20));
		txtNombre = new JTextField(1);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 20));
		txtNombre.setHorizontalAlignment(JTextField.CENTER);
		txtContrasena = new JPasswordField(1);
		txtContrasena.setFont(new Font("Arial", Font.PLAIN, 20));
		txtContrasena.setHorizontalAlignment(JTextField.CENTER);
		
		
		//Añadir paneles a ventana
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentroConLogo,BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		//Añadir componentes a los paneles
		pNorte.add(lblTitulo);
		pCentroConLogo.add(lblLogo);
		pCentro.add(lblNombre);
		pCentro.add(txtNombre);
		pCentro.add(lblContrasena);
		pCentro.add(txtContrasena);
		pSur.add(btnIniciarSesion);
		pSur.add(btnRegistrarse);
		pSur.add(btnCancelar);

		//Listeners de los componentes
		btnIniciarSesion.addActionListener((e) -> {
			String nombre = txtNombre.getText();
			String contrasena = new String(txtContrasena.getPassword());
			
			if(nombre.isEmpty() || contrasena.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No has escrito un usuario o contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				Ventana2_1.this.setVisible(false);
				Ventana3 nuevaVentana = new Ventana3();
				nuevaVentana.setVisible(true);
			}
		});
		
		
		btnRegistrarse.addActionListener((e) -> {
			Ventana2_1.this.setVisible(false);
			Ventana2_2 nuevaVentana = new Ventana2_2();
			nuevaVentana.setVisible(true);
		});
		
		btnCancelar.addActionListener((e) -> {
			Ventana2_1.this.setVisible(false);
			Ventana1 nuevaVentana = new Ventana1();
			nuevaVentana.setVisible(true);
		});
		
		setVisible(true);
	}
}
