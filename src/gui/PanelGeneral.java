package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
		
		pNorte = new JPanel();
		pSur = new JPanel();
		pOeste = new JPanel();
		pEste = new JPanel();
		pCentro = new JPanel(new BorderLayout());
				
		JLabel titulo = new JLabel("Mejores Alojamientos");
		titulo.setForeground(coral);
		titulo.setFont(new Font("Segio UI", Font.BOLD, 20));
		titulo.setBackground(Color.WHITE);
		
		pNorte.add(titulo);
		
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
		
		JTable tabla = new JTable(tblModelo);
		tabla.setAutoCreateRowSorter(true);
		JScrollPane scroll = new JScrollPane(tabla);
		pCentro.add(scroll, BorderLayout.CENTER);
		
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
