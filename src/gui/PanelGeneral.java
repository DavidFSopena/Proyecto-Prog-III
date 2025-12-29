package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class PanelGeneral extends JPanel {
	private JPanel pNorte, pSur, pOeste, pEste, pCentro;
	private DefaultTableModel tblModelo;
	private JTable tabla;
	
	public PanelGeneral() {
		
		setBackground(Funciones.Colores.Turquesa);
		setLayout(new BorderLayout());
		
		pNorte = new JPanel(new GridLayout());
		pNorte.setBackground(Funciones.Colores.Turquesa);
		pNorte.setPreferredSize(new Dimension(0, 100));
		
		pSur = new JPanel();
		pOeste = new JPanel();
		pOeste.setBackground(Funciones.Colores.Turquesa);
		
		pEste = new JPanel();
		pEste.setBackground(Funciones.Colores.Turquesa);
		
		pCentro = new JPanel(new BorderLayout());
		pCentro.setBackground(Funciones.Colores.Turquesa);
		
		JLabel sombra = new JLabel("Mejores Alojamientos");
		sombra.setFont(Funciones.Letra.negrita(40));
		sombra.setForeground(Funciones.Colores.Coral);
		sombra.setBounds(2, 2, 600, 50);
		
		JPanel panelTitulo = new JPanel(null);
		panelTitulo.setOpaque(false); 
		panelTitulo.setPreferredSize(new Dimension(600, 60));
				
		JLabel titulo = new JLabel("Mejores Alojamientos");
		titulo.setFont(Funciones.Letra.negrita(40));
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
		Tabla.aplicar(tabla);
		
		Tabla.aplicar(tabla);
		
		tabla.getColumnModel().getColumn(0).setPreferredWidth(70);
		tabla.getColumnModel().getColumn(1).setPreferredWidth(420);
		tabla.getColumnModel().getColumn(2).setPreferredWidth(160);
		tabla.getColumnModel().getColumn(3).setPreferredWidth(120);
		tabla.getColumnModel().getColumn(4).setPreferredWidth(150);
		tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
		
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(1200, 550));
		scroll.setBackground(Funciones.Colores.Turquesa);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
		scroll.getViewport().setBackground(Funciones.Colores.Turquesa);
		pCentro.add(scroll, BorderLayout.CENTER);
		
		tabla.setBackground(Color.WHITE);
		tabla.setOpaque(true);
		
		cargarTopDesdeCSV("resources/data/alojamientos.csv", 20);
		
		tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tabla.getSelectedRow() != -1) {

                    int filaVista = tabla.getSelectedRow();
                    int filaModelo = tabla.convertRowIndexToModel(filaVista);

                    String id       = tblModelo.getValueAt(filaModelo, 0).toString();
                    String titulo   = tblModelo.getValueAt(filaModelo, 1).toString();
                    String barrio   = tblModelo.getValueAt(filaModelo, 2).toString();
                    int capacidad   = Integer.parseInt(tblModelo.getValueAt(filaModelo, 3).toString());
                    double precio   = Double.parseDouble(tblModelo.getValueAt(filaModelo, 4).toString());
                    double rating   = Double.parseDouble(tblModelo.getValueAt(filaModelo, 5).toString());

                    Window padre = SwingUtilities.getWindowAncestor(PanelGeneral.this);

                    VentanaDetalleApartamento v = new VentanaDetalleApartamento( padre,
                            id, titulo, barrio, capacidad, precio, rating);
                    
                    v.setVisible(true);
                }
            }
        });
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
