package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

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
		super();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		pNorte = new JPanel (new GridLayout(1,1));
		pCentro = new JPanel (new GridLayout(3,3,10,10));
		pSur = new JPanel (new GridLayout(1,2,10,0));
		
		txtUsuario = new JTextField();
		contrasenia1 = new JPasswordField();
		contrasenia2 = new JPasswordField();
		checkMostrar = new JCheckBox("Mostrar");
		
		lblTitulo = new JLabel("Registro", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
		lblUsuario = new JLabel("Usuario: ");
		lblcontrasenia1 = new JLabel("Contraseña: ");
		lblcontrasenia2 = new JLabel("Repite contraseña: ");
		
		btnSiguiente = new JButton("Siguiente");
		btnCancelar = new JButton("Cancelar");
		
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
		
		
		
		
		
		


		
		
	}
}
