package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelGeneral extends JPanel {
	private JPanel pNorte, pSur, pOeste, pEste, pCentro;
	private DefaultTableModel tblModelo;
	private JTable tabla;
	
	public PanelGeneral() {
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,120);
		
		setBackground(turquesa);
		setLayout(new BorderLayout());
		
		pNorte = new JPanel(new GridLayout());
		pNorte.setBackground(turquesa);
		pNorte.setPreferredSize(new Dimension(0, 100));
		
		pSur = new JPanel();
		pOeste = new JPanel();
		pOeste.setBackground(turquesa);
		
		pEste = new JPanel();
		pEste.setBackground(turquesa);
		
		pCentro = new JPanel(new BorderLayout());
		pCentro.setBackground(turquesa);
		
		JLabel sombra = new JLabel("Mejores Alojamientos");
		sombra.setFont(new Font("Segoe UI", Font.BOLD, 40));
		sombra.setForeground(coral);
		sombra.setBounds(2, 2, 600, 50);
		
		JPanel panelTitulo = new JPanel(null);
		panelTitulo.setOpaque(false); 
		panelTitulo.setPreferredSize(new Dimension(600, 60));
				
		JLabel titulo = new JLabel("Mejores Alojamientos");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 40));
		titulo.setForeground(Color.BLACK);
		titulo.setBounds(0, 0, 600, 50);
		
		panelTitulo.add(sombra);
		panelTitulo.add(titulo);
		
		pNorte.add(panelTitulo);
		pNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
		
		add(pNorte,BorderLayout.NORTH);
		add(pSur,BorderLayout.SOUTH);
		add(pOeste,BorderLayout.WEST);
		add(pEste,BorderLayout.EAST);
		add(pCentro, BorderLayout.CENTER);
		
		String[] columnas = {"ID", "Título", "Barrio", "Capacidad", "Precio/Noche", "Rating"};
		tblModelo = new DefaultTableModel(columnas, 0) {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tabla = new JTable(tblModelo);
		tabla.setAutoCreateRowSorter(true);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(1200, 550));
		scroll.setBackground(turquesa);
		scroll.getViewport().setBackground(turquesa);
		pCentro.add(scroll, BorderLayout.CENTER);
		
		tabla.setBackground(Color.WHITE);
		tabla.setSelectionForeground(Color.WHITE);
		tabla.setOpaque(true);
		
		tabla.setShowVerticalLines(true);
		tabla.setGridColor(new Color (186, 184, 184));
		
		tabla.getTableHeader().setReorderingAllowed(false);
		tabla.getTableHeader().setBackground(Color.WHITE);
		tabla.getTableHeader().setForeground(coral);
		tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 26));
		tabla.setRowHeight(30);
		
		cargarTopDesdeCSV("resources/data/alojamientos.csv", 20);
	}
	
	private void cargarTopDesdeCSV(String ruta, int cuantos) {
		List<String[]> filas = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea = null;
			boolean primera = true;
			while ((linea = br.readLine()) != null) {
				if (primera) {
					primera = false;
					continue;
				}
				
				String [] partes = linea.split(";");
				if (partes.length == 6) {
					filas.add(partes);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("No se ha podido leer el CSV: " + ruta);
		}
		
		filas.sort(new Comparator<String []>() {
			public int compare (String[] o1, String[] o2) {
				double r1 = Double.parseDouble(o1[5]);
				double r2 = Double.parseDouble(o2[5]);
				return Double.compare(r2, r1);
			}
		});
		
		int limite = Math.min(cuantos, filas.size());
		for (int i = 0; i < limite; i++) {
			tblModelo.addRow(filas.get(i));
		}
	}

}
