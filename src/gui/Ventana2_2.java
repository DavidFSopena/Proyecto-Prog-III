package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.border.EmptyBorder;

public class Ventana2_2 extends JFrame{
	private JPanel pNorte, pCentro, pSur, pCheck, pRellenar;
	private JTextField txtMail, txtUsuario;
	private JPasswordField contrasenia1, contrasenia2;
	private JCheckBox checkMostrar;
	private JLabel lblTitulo, lblMail, lblUsuario, lblcontrasenia1, lblcontrasenia2;
	private JButton btnSiguiente, btnCancelar;

	public Ventana2_2() {
		super("Registro");
		setSize(780,520);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout(0,20));
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,102);
		getContentPane().setBackground(turquesa);
		
		pNorte = new JPanel (new GridLayout(1,1));
		pCentro = new JPanel (new FlowLayout(FlowLayout.CENTER, 0, 10));
		pSur = new JPanel (new FlowLayout(FlowLayout.CENTER,18,10));
		pCheck = new JPanel(new FlowLayout(FlowLayout.LEFT,8,0));
		pRellenar = new JPanel(new GridLayout(4,2,20,16));

		
		pNorte.setOpaque(false);
		pCentro.setOpaque(false);
		pCentro.setBorder(new EmptyBorder(60, 0, 0, 0));
		pSur.setOpaque(false);
		pCheck.setOpaque(false);
		pRellenar.setOpaque(false);
		
		txtMail = new JTextField(16);
		txtUsuario = new JTextField(16);
		contrasenia1 = new JPasswordField(16);
		contrasenia2 = new JPasswordField(16);
		checkMostrar = new JCheckBox("Mostrar contraseña");
		checkMostrar.setOpaque(false);
		
		lblTitulo = new JLabel("Registro", JLabel.CENTER);
		lblTitulo.setForeground(coral);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD,28));
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setOpaque(true);
		lblMail = new JLabel("EMAIL: ");
		lblUsuario = new JLabel("USUARIO: ");
		lblcontrasenia1 = new JLabel("CONTRASEÑA: ");
		lblcontrasenia2 = new JLabel("REPITE CONTRASEÑA: ");
		
		btnSiguiente = new JButton("SIGUIENTE");
		btnCancelar = new JButton("CANCELAR");
		botonBonito(btnSiguiente, coral);
		botonBonito(btnCancelar, coral);

		pNorte.add(lblTitulo);
		
		pCheck.add(contrasenia1);
		pCheck.add(checkMostrar);
		
		pRellenar.add(lblMail);
		pRellenar.add(txtMail);
		pRellenar.add(lblUsuario);
		pRellenar.add(txtUsuario);
		pRellenar.add(lblcontrasenia1);
		pRellenar.add(pCheck);
		pRellenar.add(lblcontrasenia2);
		pRellenar.add(contrasenia2);
		
		pCentro.add(pRellenar, BorderLayout.CENTER);
		
		pSur.add(btnSiguiente);
		pSur.add(btnCancelar);
		
		add(pNorte, BorderLayout.NORTH);
		add(pCentro, BorderLayout.CENTER);
		add(pSur, BorderLayout.SOUTH);
		
		checkMostrar.addActionListener(e->{
			if (checkMostrar.isSelected()) {
				contrasenia1.setEchoChar((char)0);
				contrasenia2.setEchoChar((char)0);
			}else {			
				contrasenia1.setEchoChar('•');
				contrasenia2.setEchoChar('•');
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
				mensaje("Contraseña no válida\nDebe tener:\n- Mínimo 8 caracteres\n- 1 mayúscula\n- 1minúscula\n- 1 número");
				
			}
			if (!Arrays.equals(c1, c2)) {
	            mensaje("Las contraseñas no coinciden.");
	            return;
			}
		});
		
		btnCancelar.addActionListener(e-> setVisible(false));

		setVisible(true);
		
	}

	private void botonBonito(JButton boton, Color coral) {
		boton.setForeground(coral);
		boton.setBackground(Color.WHITE);
		boton.setFocusPainted(false);
		boton.setFont(new Font("Segoe UI", Font.BOLD,16));
		
		boton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				boton.setForeground(coral);
				boton.setBackground(Color.WHITE);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				boton.setForeground(Color.WHITE);
				boton.setBackground(coral);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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