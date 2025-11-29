package gui;

import java.awt.BorderLayout;
import domain.Usuario;
import java.awt.Color;
import java.awt.Component;
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

public class Ventana2_1 extends JFrame {
	private JPanel pSur, pCentro, pCentroConLogo, pNorte;
	private JLabel lblTitulo, lblNombre, lblContrasena;
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
		pCentro.setLayout(new GridLayout(2,2,10,15));
		pCentro.setBorder(BorderFactory.createEmptyBorder(12, 240, 240, 240));
		pCentro.setBackground(Funciones.Colores.Turquesa);
		pCentroConLogo = new JPanel();
		pCentroConLogo.setBackground(Funciones.Colores.Turquesa);
		pCentroConLogo.setLayout(new BorderLayout());
		setLayout(new BorderLayout(0,20)); 
		
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
		lblLogo.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
		
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
		pCentroConLogo.add(pCentro, BorderLayout.CENTER);
		pSur.add(btnIniciarSesion);
		pSur.add(btnRegistrarse);
		pSur.add(btnVolver);

		//Listeners de los componentes
		btnIniciarSesion.addActionListener((e) -> {
			String email = txtEmail.getText();
			String contrasena = new String(txtContrasena.getPassword());
			
			if(email.isEmpty() || contrasena.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No has escrito un usuario o contraseña", "ERROR", JOptionPane.ERROR_MESSAGE);
			} else {
				Ventana2_1.this.setVisible(false);
				Ventana3 nuevaVentana = new Ventana3();
				nuevaVentana.setVisible(true);
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
