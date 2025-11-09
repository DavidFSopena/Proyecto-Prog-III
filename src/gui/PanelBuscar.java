package gui;

import domain.Alojamiento;
import domain.Barrio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class PanelBuscar extends JPanel {
	private JPanel pCentro, pBuscar, pResultados;
	private JComboBox<Object> cbBarrio;
	private JComboBox<Integer> cbAdultos, cbNinos;
	private JComboBox<String> cbOrden;
	private JButton btnBuscar, btnVolver;
	private JTable tabla;
	private DefaultTableModel modeloTabla;
	private List<Alojamiento> alojamientos = new ArrayList<>();
	private List<Alojamiento> filtrados = new ArrayList<>();

	public PanelBuscar() {
		setLayout(new BorderLayout());
		setOpaque(false);

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
		for (Barrio b : Barrio.values()) cbBarrio.addItem(b);
		cbAdultos = new JComboBox<>();
		cbNinos = new JComboBox<>();
		for (int i = 0; i <= 5; i++) {
			cbAdultos.addItem(i);
			cbNinos.addItem(i);
		}

		btnBuscar = new JButton("Buscar");

		p.add(new JLabel("Barrio:"));
		p.add(cbBarrio);
		p.add(new JLabel("Adultos:"));
		p.add(cbAdultos);
		p.add(new JLabel("Niños:"));
		p.add(cbNinos);
		p.add(new JLabel(""));
		p.add(btnBuscar);

		btnBuscar.addActionListener(e -> {
			Object seleccionado = cbBarrio.getSelectedItem();
			Barrio barrio = null;
			if(!"TODOS".equals(seleccionado)) {
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
		return p;
	}

	private JPanel pResultados() {
		JPanel p = new JPanel(new BorderLayout(10, 10));
		p.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
		p.setOpaque(false);

		String[] titulos = { "ID", "Título", "Barrio", "Capacidad", "€/noche", "Rating" };
		modeloTabla = new DefaultTableModel(titulos, 0) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}
		};
		tabla = new JTable(modeloTabla);

		cbOrden = new JComboBox<>(new String[] { "Precio menor-mayor", "Precio mayor-menor", "Rating menor-mayor", "Rating mayor-menor" });
		btnVolver = new JButton("Volver");

		JPanel pSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		pSur.add(cbOrden);
		pSur.add(btnVolver);

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

				Alojamiento a = new Alojamiento(campo[0], campo[1], barrio, Integer.parseInt(campo[3]),
						Double.parseDouble(campo[4]), Double.parseDouble(campo[5]));
				lista.add(a);
			}

			sc.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los alojamientos: " + e.getMessage());
		}
		return lista;
	}

}
