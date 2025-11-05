package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ventana2_2 extends JFrame{
	private JPanel pNorte, pCentro, pSur;
	private JTextField txtUsuario;
	private JPasswordField contrasenia1, contrasenia2;
	private JCheckBox checkMostrar;
	private JLabel lblTitulo, lblUsuario, lblcontrasenia1, lblcontrasenia2;
	private JButton btnSiguiente, btnCancelar;

	public Ventana2_2() {
		super("Registro");
		setSize(720,460);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(0,20));
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,102);
		getContentPane().setBackground(turquesa);
		
		pNorte = new JPanel (new GridLayout(1,1));
		pCentro = new JPanel (new GridLayout(3,3,15,15));
		pSur = new JPanel (new GridLayout(1,2,10,0));
		pNorte.setBorder(BorderFactory.createEmptyBorder(10,40,0,40));
		pCentro.setBorder(BorderFactory.createEmptyBorder(10,60,10,60));
		pSur.setBorder(BorderFactory.createEmptyBorder(0,60,20,60));

		txtUsuario = new JTextField(1);
		contrasenia1 = new JPasswordField(1);
		contrasenia2 = new JPasswordField(10);
		checkMostrar = new JCheckBox("Mostrar");
		
		lblTitulo = new JLabel("Registro", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
		lblUsuario = new JLabel("Usuario: ");
		lblcontrasenia1 = new JLabel("Contraseña: ");
		lblcontrasenia2 = new JLabel("Repite contraseña: ");
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBackground(coral);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(coral);

		pNorte.add(lblTitulo);
		
		pCentro.add(lblUsuario);
		pCentro.add(txtUsuario);
		pCentro.add(new JLabel());
		pCentro.add(lblcontrasenia1);
		pCentro.add(contrasenia1);
		pCentro.add(checkMostrar);
		pCentro.add(lblcontrasenia2);
		pCentro.add(contrasenia2);
		pCentro.add(new JLabel());
		
		pSur.add(btnSiguiente);
		pSur.add(btnCancelar);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pSur, BorderLayout.SOUTH);
		

		setVisible(true);
		
	}
	public static void main(String[] args) {
		new Ventana2_2();
	}
}
