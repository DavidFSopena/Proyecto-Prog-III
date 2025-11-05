package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Ventana2_2 extends JFrame{
	private JPanel pNorte, pCentro, pSur;
	private JTextField txtMail, txtUsuario;
	private JPasswordField contrasenia1, contrasenia2;
	private JCheckBox checkMostrar;
	private JLabel lblTitulo, lblMail, lblUsuario, lblcontrasenia1, lblcontrasenia2;
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

		txtMail = new JTextField();
		txtUsuario = new JTextField();
		contrasenia1 = new JPasswordField();
		contrasenia2 = new JPasswordField();
		checkMostrar = new JCheckBox("Mostrar");
		
		lblTitulo = new JLabel("Registro", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
		lblMail = new JLabel("Correo eletrónico: ");
		lblUsuario = new JLabel("Usuario: ");
		lblcontrasenia1 = new JLabel("Contraseña: ");
		lblcontrasenia2 = new JLabel("Repite contraseña: ");
		
		btnSiguiente = new JButton("SIGUIENTE");
		btnSiguiente.setBackground(coral);
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBackground(coral);

		pNorte.add(lblTitulo);
		
		pCentro.add(lblMail);
		pCentro.add(txtMail);
		pCentro.add(new JLabel());
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
		
		checkMostrar.addActionListener(e->{
			if (!checkMostrar.isSelected()) {
				contrasenia1.setEchoChar('•');
				contrasenia2.setEchoChar('•');
			}else {
				contrasenia1.setEchoChar((char)0);
				contrasenia2.setEchoChar((char)0);
			}
		});
		
		btnSiguiente.addActionListener(e->{
			String usuario = txtUsuario.getText();
			char[] c1 = contrasenia1.getPassword();
			char[] c2 = contrasenia2.getPassword();
			
			if(usuario.isEmpty()) {
				mensaje("Debes introducir un nombre de usuario");
			}
			if(!cumpleRequisitos(new String(c1))) {
				mensaje("Contraseña no válida\nDebe tener:\n-Mínimo 8 caracteres\n-1 mayúscula\n-1minúscula\n-1 número");
				
			}
			if (!Arrays.equals(c1, c2)) {
	            mensaje("Las contraseñas no coinciden.");
	            return;
			}
		});

		setVisible(true);
		
	}

	private boolean cumpleRequisitos(String c1) {
		if(c1.length()<8)
			return false;
		boolean may = false, min = false, num = false;
        for (char c : c1.toCharArray()) {
            if (Character.isUpperCase(c)) may = true;
            else if (Character.isLowerCase(c)) min = true;
            else if (Character.isDigit(c)) num = true;
        }
        return may && min && num;
		
	}

	private void mensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje,"Registro",JOptionPane.WARNING_MESSAGE);
	}
}