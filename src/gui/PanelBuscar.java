package gui;

import domain.Alojamiento;
import domain.Barrio;
import domain.Sesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import db.BD;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingUtilities;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import domain.Busqueda;


public class PanelBuscar extends JPanel {
	private JPanel pCentro, pBuscar, pResultados;
	private JComboBox<Object> cbBarrio;
	private JComboBox<Integer> cbAdultos, cbNinos;
	private JComboBox<String> cbOrden;
	private JButton btnBuscar, btnVolver, btnHistorial;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private List<Alojamiento> alojamientos = new ArrayList<>();
	private List<Alojamiento> filtrados = new ArrayList<>();

	
	public PanelBuscar() {
		setBackground(Funciones.Colores.Turquesa);
		setLayout(new BorderLayout());

		pBuscar = pBuscar();
		pResultados = pResultados();

		pCentro = new JPanel(new BorderLayout());
		pCentro.setOpaque(false);
		pCentro.add(pBuscar, BorderLayout.CENTER);
		add(pCentro, BorderLayout.CENTER);

		alojamientos = cargarAlojamientos(new File("resources/data/alojamientos.csv"));
	}

	private JPanel pBuscar() {
		JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
		p.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
		p.setOpaque(false);

		cbBarrio = new JComboBox<>();
		cbBarrio.addItem("TODOS");
		for (Barrio b : Barrio.values())
			cbBarrio.addItem(b);
		cbAdultos = new JComboBox<>();
		cbNinos = new JComboBox<>();
		for (int i = 0; i <= 5; i++) {
			cbAdultos.addItem(i);
			cbNinos.addItem(i);
		}
		JLabel lblBarrio = new JLabel("BARRIO:");
		lblBarrio.setFont(Funciones.Letra.negrita(20));
		JLabel lblAdultos = new JLabel("ADULTOS:");
		lblAdultos.setFont(Funciones.Letra.negrita(20));
		JLabel lblNinos = new JLabel("NIÑOS:");
		lblNinos.setFont(Funciones.Letra.negrita(20));

		cbBarrio.setFont(Funciones.Letra.negrita(20));
		cbAdultos.setFont(Funciones.Letra.negrita(20));
		cbNinos.setFont(Funciones.Letra.negrita(20));

		cbBarrio.setPreferredSize(new Dimension(260, 36));
		cbAdultos.setPreferredSize(new Dimension(260, 36));
		cbNinos.setPreferredSize(new Dimension(260, 36));

		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBackground(Funciones.Colores.Coral);
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFocusPainted(false);
		
		btnHistorial = new JButton("HISTORIAL");
		btnHistorial.setBackground(Funciones.Colores.Coral);
		btnHistorial.setForeground(Color.WHITE);
		btnHistorial.setFocusPainted(false);

		p.add(lblBarrio);
		p.add(cbBarrio);
		p.add(lblAdultos);
		p.add(cbAdultos);
		p.add(lblNinos);
		p.add(cbNinos);
		p.add(btnBuscar);
		p.add(btnHistorial);


		btnBuscar.addActionListener(e -> {
			Object seleccionado = cbBarrio.getSelectedItem();
			Barrio barrio = null;
			if (!"TODOS".equals(seleccionado)) {
				barrio = (Barrio) seleccionado;
			}
			int adultos = (int) cbAdultos.getSelectedItem();
			int ninos = (int) cbNinos.getSelectedItem();
			int total = adultos + ninos;

			filtrados.clear();
			for (Alojamiento a : alojamientos) {
				if ((barrio == null || a.getBarrio() == barrio) && a.getCapacidad() >= total) {
					filtrados.add(a);
				}
			}
			
			String fecha = Funciones.fechayHora();

			String textoBarrio;
			if (barrio == null) {
			    textoBarrio = "TODOS";
			} else {
			    textoBarrio = barrio.toString();
			}

			String filtros = "Barrio=" + textoBarrio + ", Adultos=" + adultos + ", Niños=" + ninos + ", Total=" + total;

			int resultados = filtrados.size();

			Sesion.getHistorial().add(new Busqueda(fecha, filtros, resultados));
			modeloTabla.setRowCount(0);
			for (Alojamiento a : filtrados) {
				modeloTabla.addRow(new Object[] { a.getId(), a.getTitulo(), a.getBarrio(), a.getCapacidad(),
						a.getPrecioNoche(), a.getRating() });
			}

			pCentro.remove(pBuscar);
			pCentro.add(pResultados, BorderLayout.CENTER);
			pCentro.revalidate();
			pCentro.repaint();
		});
		
		btnHistorial.addActionListener(e -> {
		    new VentanaHistorialBusquedas().setVisible(true);
		});
		return p;
	}

	private JPanel pResultados() {
		JPanel p = new JPanel(new BorderLayout(10, 10));
		p.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		p.setOpaque(false);

		String[] titulos = { "ID", "Título", "Barrio", "Capacidad", "€/noche", "Rating" };
		modeloTabla = new DefaultTableModel(titulos, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tabla = new JTable(modeloTabla);
		tabla.setAutoCreateRowSorter(true);
		Tabla.aplicar(tabla);
		
		tabla.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 2 && tabla.getSelectedRow() != -1) {

		            int filaVista = tabla.getSelectedRow();
		            int filaModelo = tabla.convertRowIndexToModel(filaVista);

		            String id = modeloTabla.getValueAt(filaModelo, 0).toString();
		            String titulo = modeloTabla.getValueAt(filaModelo, 1).toString();
		            String barrio = modeloTabla.getValueAt(filaModelo, 2).toString();
		            int capacidad = Integer.parseInt(modeloTabla.getValueAt(filaModelo, 3).toString());
		            double precio = Double.parseDouble(modeloTabla.getValueAt(filaModelo, 4).toString());
		            double rating = Double.parseDouble(modeloTabla.getValueAt(filaModelo, 5).toString());

		            Window parent = SwingUtilities.getWindowAncestor(PanelBuscar.this);

		            new VentanaDetalleApartamento(parent, id, titulo, barrio, capacidad, precio, rating).setVisible(true);
		        }
		    }
		});

		cbOrden = new JComboBox<>(new String[] { "Precio menor-mayor", "Precio mayor-menor", "Rating menor-mayor",
				"Rating mayor-menor" });
		cbOrden.setBackground(Funciones.Colores.Coral);
		cbOrden.setForeground(Color.WHITE);
		cbOrden.setFocusable(false);
		btnVolver = new JButton("VOLVER");
		btnVolver.setBackground(Funciones.Colores.Coral);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFocusPainted(false);

		JPanel pSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		pSur.add(cbOrden);
		pSur.add(btnVolver);
		pSur.setOpaque(false);
		pSur.setBackground(Funciones.Colores.Turquesa);
		p.add(new JScrollPane(tabla), BorderLayout.CENTER);
		p.add(pSur, BorderLayout.SOUTH);

		cbOrden.addActionListener(e -> ordenar());
		btnVolver.addActionListener(e -> {
			pCentro.remove(pResultados);
			pCentro.add(pBuscar, BorderLayout.CENTER);
			pCentro.revalidate();
			pCentro.repaint();
		});

		return p;
	}

	private void ordenar() {
		Comparator<Alojamiento> comp = null;
		String eleccion = (String) cbOrden.getSelectedItem();
		if (eleccion != null) {
			if (eleccion.equals("Precio menor-mayor")) {
				comp = (a, b) -> Double.compare(a.getPrecioNoche(), b.getPrecioNoche());
			} else if (eleccion.equals("Precio mayor-menor")) {
				comp = (a, b) -> Double.compare(b.getPrecioNoche(), a.getPrecioNoche());
			} else if (eleccion.equals("Rating menor-mayor")) {
				comp = (a, b) -> Double.compare(a.getRating(), b.getRating());
			} else if (eleccion.equals("Rating mayor-menor")) {
				comp = (a, b) -> Double.compare(b.getRating(), a.getRating());
			}
		}
		filtrados.sort(comp);

		modeloTabla.setRowCount(0);
		for (Alojamiento a : filtrados) {
			modeloTabla.addRow(new Object[] { a.getId(), a.getTitulo(), a.getBarrio(), a.getCapacidad(),
					a.getPrecioNoche(), a.getRating() });
		}
	}

	private List<Alojamiento> cargarAlojamientos(File csv) {
		List<Alojamiento> lista = new ArrayList<>();
		try {
			Scanner sc = new Scanner(csv);
			sc.nextLine();

			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				String[] campo = linea.split(";");

				Barrio barrio = null;
				try {
					barrio = Barrio.valueOf(campo[2].toUpperCase().replace(" ", "_"));
				} catch (Exception e) {
				}

				Alojamiento a = new Alojamiento(campo[0], campo[1], barrio, Integer.parseInt(campo[3]), Double.parseDouble(campo[4]), Double.parseDouble(campo[5]));
				lista.add(a);
				BD.upsertAlojamiento(a);
			}

			sc.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los alojamientos: " + e.getMessage());
		}
		return lista;
	}

}
