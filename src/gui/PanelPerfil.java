package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.BD;
import domain.Alojamiento;
import domain.Barrio;

public class PanelPerfil extends JPanel {
	private JPanel pNorte, pSur, pOeste, pEste, pCentro, pFilaNombreEmail;
	private JLabel lblNombre, lblEmail, lblTituloTabla;
	private JTable tabla;
	private JButton btnEditarPerfil;
	private DefaultTableModel tblModelo;
	
	public PanelPerfil() {
		setBackground(Funciones.Colores.Turquesa);
		setLayout(new BorderLayout(10,10));
		setBorder(new EmptyBorder(30, 50, 30, 50));
		
		//Creamos los paneles 
		pNorte = new JPanel(new GridLayout(3,1,5,5));
		pNorte.setBackground(Funciones.Colores.Turquesa);
		pSur = new JPanel();
		pSur.setBackground(Funciones.Colores.Turquesa);
		pOeste = new JPanel();
		pOeste.setBackground(Funciones.Colores.Turquesa);
		pEste = new JPanel();
		pEste.setBackground(Funciones.Colores.Turquesa);
		pCentro = new JPanel(new BorderLayout());
		pCentro.setBackground(Funciones.Colores.Turquesa);
		pFilaNombreEmail = new JPanel(new BorderLayout());
		pFilaNombreEmail.setBackground(Funciones.Colores.Turquesa);
		
		
		//Creamos componentes
		lblNombre = new JLabel("Nombre: "+BD.usuarioLogeado.getNombre(), JLabel.LEFT);
		lblNombre.setFont(Funciones.Letra.negrita(25));
		lblNombre.setForeground(Color.WHITE);
		lblEmail = new JLabel("Email: "+BD.usuarioLogeado.getEmail(), JLabel.RIGHT);
		lblEmail.setFont(Funciones.Letra.negrita(25));
		lblEmail.setForeground(Color.WHITE);
		lblTituloTabla = new JLabel("Tus alojamientos:", JLabel.CENTER);
		lblTituloTabla.setFont(Funciones.Letra.negrita(25));
		lblTituloTabla.setForeground(Color.WHITE);
		lblTituloTabla.setBounds(630, 100, 500, 40);
		btnEditarPerfil = new JButton("Editar perfil");
		
		List<Alojamiento> alojamientosCadaUsuario = BD.obtenerListaAlojamiento(BD.usuarioLogeado.getUsuario());
		ModeloTablaMisAlojamientosUsuario modelo = new ModeloTablaMisAlojamientosUsuario(alojamientosCadaUsuario);
		tabla = new JTable(modelo);
		tabla.setGridColor(new Color(186,184,184));
		tabla.setRowHeight(30);
		tabla.getTableHeader().setBackground(Color.WHITE);
		tabla.getTableHeader().setFont(Funciones.Letra.negrita(20));
		tabla.getTableHeader().setForeground(Funciones.Colores.Coral);
		tabla.repaint();
		JScrollPane scroll = new JScrollPane(tabla);
		
		//Añadimos paneles a ventana
		add(pNorte,BorderLayout.NORTH);
		add(pSur,BorderLayout.SOUTH);
		add(pOeste,BorderLayout.WEST);
		add(pEste,BorderLayout.EAST);
		add(pCentro, BorderLayout.WEST);
				
		//Añadimos componenets a panel
		pFilaNombreEmail.add(lblNombre, BorderLayout.WEST);
		pFilaNombreEmail.add(lblEmail, BorderLayout.EAST);
		pNorte.add(pFilaNombreEmail);
		pNorte.add(lblTituloTabla);
		pSur.add(btnEditarPerfil);
		
		pCentro.add(scroll);
		
		//Listeners
		btnEditarPerfil.addActionListener( (e) -> {
			String nuevoNombre = JOptionPane.showInputDialog(this,"Nuevo nombre:",BD.usuarioLogeado.getNombre());
			if (nuevoNombre == null || nuevoNombre.isEmpty()) {
				return;
			}
			String nuevoEmail = JOptionPane.showInputDialog(this,"Nuevo email:",BD.usuarioLogeado.getEmail());
			if (nuevoEmail == null || nuevoEmail.isEmpty()) {
				return;
			}
			
			boolean actualizacionUsuarioCorrecta = BD.actualizarUsuario(BD.usuarioLogeado.getUsuario(), nuevoNombre, nuevoEmail);
			
			if(actualizacionUsuarioCorrecta) {
				lblNombre.setText("Nombre: "+BD.usuarioLogeado.getNombre());
				lblEmail.setText("Nombre: "+BD.usuarioLogeado.getEmail());
				JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
			} else {
				JOptionPane.showMessageDialog(this, "Error actualizados correctamente");
			}
		});
		
	}
}
