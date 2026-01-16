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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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
		
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        wrapper.add(pCentro, BorderLayout.CENTER);
        add(wrapper, BorderLayout.CENTER);

		alojamientos = cargarAlojamientos(new File("resources/data/alojamientos.csv"));
	}
	
	 private JPanel crearCard() {
	        JPanel card = new JPanel(new GridBagLayout());
	        card.setBackground(new Color(245, 247, 250));
	        card.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createLineBorder(new Color(220, 225, 230), 1),
	                BorderFactory.createEmptyBorder(22, 26, 22, 26)
	        ));
	        return card;
	    }
	 
	  private void estiloCombo(JComboBox<?> cb) {
	        cb.setFont(Funciones.Letra.negrita(16));
	        cb.setPreferredSize(new Dimension(320, 40));
	        cb.setMaximumSize(new Dimension(320, 40));
	        cb.setFocusable(false);
	    }

	    private void estiloBoton(JButton b) {
	        b.setBackground(Funciones.Colores.Coral);
	        b.setForeground(Color.WHITE);
	        b.setFocusPainted(false);
	        b.setFont(Funciones.Letra.negrita(16));
	        b.setPreferredSize(new Dimension(160, 42));
	    }

	    private JLabel titulo(String text) {
	        JLabel t = new JLabel(text);
	        t.setFont(Funciones.Letra.negrita(24));
	        t.setForeground(new Color(40, 40, 40));
	        return t;
	    }

	    private JLabel etiqueta(String text) {
	        JLabel l = new JLabel(text);
	        l.setFont(Funciones.Letra.negrita(16));
	        l.setForeground(new Color(60, 60, 60));
	        return l;
	    }

	private JPanel pBuscar() {
		JPanel p = new JPanel(new GridLayout(4, 2, 10, 10));
		JPanel outer = new JPanel(new GridBagLayout());
        outer.setOpaque(false);

        JPanel card = crearCard();

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = new Insets(0, 0, 18, 0);
        card.add(titulo("Buscar alojamiento"), gc);

        // combos
        cbBarrio = new JComboBox<>();
        cbBarrio.addItem("TODOS");
        for (Barrio b : Barrio.values()) cbBarrio.addItem(b);

        cbAdultos = new JComboBox<>();
        cbNinos = new JComboBox<>();
        for (int i = 0; i <= 5; i++) {
            cbAdultos.addItem(i);
            cbNinos.addItem(i);
        }

        estiloCombo(cbBarrio);
        estiloCombo(cbAdultos);
        estiloCombo(cbNinos);

        // fila 1
        gc.gridwidth = 1;
        gc.gridy = 1;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 12, 16);
        gc.anchor = GridBagConstraints.EAST;
        card.add(etiqueta("Barrio:"), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 12, 0);
        gc.anchor = GridBagConstraints.WEST;
        card.add(cbBarrio, gc);

        // fila 2
        gc.gridy = 2;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 12, 16);
        gc.anchor = GridBagConstraints.EAST;
        card.add(etiqueta("Adultos:"), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 12, 0);
        gc.anchor = GridBagConstraints.WEST;
        card.add(cbAdultos, gc);

        // fila 3
        gc.gridy = 3;
        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 18, 16);
        gc.anchor = GridBagConstraints.EAST;
        card.add(etiqueta("Niños:"), gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 18, 0);
        gc.anchor = GridBagConstraints.WEST;
        card.add(cbNinos, gc);

        // botones
        btnBuscar = new JButton("BUSCAR");
        btnHistorial = new JButton("HISTORIAL");
        estiloBoton(btnBuscar);
        estiloBoton(btnHistorial);

        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pBtns.setOpaque(false);
        pBtns.add(btnHistorial);
        pBtns.add(btnBuscar);

        gc.gridy = 4;
        gc.gridx = 0;
        gc.gridwidth = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.EAST;
        card.add(pBtns, gc);

        outer.add(card); // centra el card

        btnBuscar.addActionListener(e -> ejecutarBusquedaDesdeFiltros(true));
        btnHistorial.addActionListener(e -> new VentanaHistorialBusquedas(PanelBuscar.this).setVisible(true));

        return outer;
	}
	public void ejecutarBusquedaDesdeFiltros(boolean guardarEnHistorial) {

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

	    modeloTabla.setRowCount(0);
	    for (Alojamiento a : filtrados) {
	        modeloTabla.addRow(new Object[] { a.getId(), a.getTitulo(), a.getBarrio(), a.getCapacidad(),
	                a.getPrecioNoche(), a.getRating() });
	    }

	    if (guardarEnHistorial) {
	        String fecha = Funciones.fechayHora();

	        String textoBarrio;
	        if (barrio == null) textoBarrio = "TODOS";
	        else textoBarrio = barrio.toString();

	        String filtros = "Barrio=" + textoBarrio + ", Adultos=" + adultos + ", Niños=" + ninos + ", Total=" + total;
	        int resultados = filtrados.size();

	        Sesion.getHistorial().add(new Busqueda(fecha, filtros, resultados, textoBarrio, adultos, ninos));
	    }

	    pCentro.remove(pBuscar);
	    pCentro.add(pResultados, BorderLayout.CENTER);
	    pCentro.revalidate();
	    pCentro.repaint();
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
		
        cbOrden.setFont(Funciones.Letra.negrita(14));
        cbOrden.setBackground(Funciones.Colores.Coral);
        cbOrden.setForeground(Color.WHITE);
        cbOrden.setFocusable(false);

        btnVolver = new JButton("VOLVER");
        estiloBoton(btnVolver);
        btnVolver.setPreferredSize(new Dimension(140, 40));

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
				String id = campo[0].trim();
				if (BD.alojamientoEstaAlquilado(id)) continue;

				Barrio barrio = null;
				try {
					barrio = Barrio.valueOf(campo[2].toUpperCase().replace(" ", "_"));
				} catch (Exception e) {
				}

				Alojamiento a = new Alojamiento(id, campo[1], barrio, Integer.parseInt(campo[3]), Double.parseDouble(campo[4]), Double.parseDouble(campo[5]));
				lista.add(a);
				BD.upsertAlojamiento(a);
			}

			sc.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los alojamientos: " + e.getMessage());
		}
		return lista;
	}
	
	public void refrescar() {
	    alojamientos = cargarAlojamientos(new File("resources/data/alojamientos.csv"));
	}
	
	public void setBarrioDesdeString(String barrio) {
	    for (int i = 0; i < cbBarrio.getItemCount(); i++) {
	        Object item = cbBarrio.getItemAt(i);
	        if (item != null && item.toString().equals(barrio)) {
	            cbBarrio.setSelectedIndex(i);
	            return;
	        }
	    }
	}
	public void setAdultos(int adultos) {
	    cbAdultos.setSelectedItem(adultos);
	}
	public void setNinos(int ninos) {
	    cbNinos.setSelectedItem(ninos);
	}

}
