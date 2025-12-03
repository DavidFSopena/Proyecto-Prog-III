package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domain.Alojamiento;
import domain.Barrio;

public class PanelPerfil extends JPanel {
	private JPanel pNorte, pSur, pOeste, pEste, pCentro;
	private JLabel lblNombre, lblEmail, lblTituloTabla;
	private JTable tabla;
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
		
		//Creamos componentes
		lblNombre = new JLabel("Nombre: nombre de prueba", JLabel.LEFT);
		lblNombre.setFont(Funciones.Letra.negrita(25));
		lblNombre.setForeground(Color.WHITE);
		lblEmail = new JLabel("Email: emaildeprueba@gmail.com", JLabel.LEFT);
		lblEmail.setFont(Funciones.Letra.negrita(25));
		lblEmail.setForeground(Color.WHITE);
		lblTituloTabla = new JLabel("Tus alojamientos:", JLabel.CENTER);
		lblTituloTabla.setFont(Funciones.Letra.negrita(25));
		lblTituloTabla.setForeground(Color.WHITE);
		lblTituloTabla.setBounds(630, 100, 500, 40);
		
		String[] columnas = {"ID", "Título", "Barrio", "Capacidad", "Precio/Noche", "Rating"};
		tblModelo = new DefaultTableModel(columnas, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tabla = new JTable(tblModelo);
		tabla.setGridColor(new Color(186, 184, 184));
		tabla.setRowHeight(30);
		
		tabla.getTableHeader().setBackground(Color.WHITE);
		tabla.setSelectionForeground(Funciones.Colores.Coral);
		tabla.setOpaque(true);
		tabla.setBackground(Color.WHITE);
		tabla.getTableHeader().setFont(Funciones.Letra.negrita(26));
		tabla.getTableHeader().setForeground(Funciones.Colores.Coral);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBorder(null);
		scroll.setPreferredSize(new Dimension(1100,50));
		scroll.setBackground(Funciones.Colores.Turquesa);
		scroll.getViewport().setBackground(Funciones.Colores.Turquesa);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		
		//Añadimos paneles a ventana
		add(pNorte,BorderLayout.NORTH);
		add(pSur,BorderLayout.SOUTH);
		add(pOeste,BorderLayout.WEST);
		add(pEste,BorderLayout.EAST);
		add(pCentro, BorderLayout.WEST);
				
		//Añadimos componenets a panel
		pNorte.add(lblNombre);
		pNorte.add(lblEmail);
		pNorte.add(lblTituloTabla);
		
		pCentro.add(scroll);
		
		cargarAlojamientosDesdeCSV(new File("resources/data/alojamientos.csv"));
	}
	
	private void cargarAlojamientosDesdeCSV(File csv) {
		List<Alojamiento> lista = new ArrayList<>();
		try (Scanner sc = new Scanner(csv)) {
			sc.nextLine(); 
			
			int contador = 0;
			
			while (sc.hasNextLine() && contador < 2) { 
				String linea = sc.nextLine();
				String[] campo = linea.split(";");

				Barrio barrio = null;
				try {
					barrio = Barrio.valueOf(campo[2].toUpperCase().replace(" ", "_"));
				} catch (Exception e) {
				}

				Alojamiento a = new Alojamiento(campo[0], campo[1], barrio, Integer.parseInt(campo[3]),
						Double.parseDouble(campo[4]), Double.parseDouble(campo[5]));
				lista.add(a);
				contador++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar alojamientos: " + e.getMessage());
		}

		// Añadimos los alojamientos al modelo
		for (Alojamiento a : lista) {
			Object[] fila = { a.getId(), a.getTitulo(), a.getBarrio(), a.getCapacidad(), a.getPrecioNoche(),
					a.getRating() };
			tblModelo.addRow(fila);
		}
	}
}
