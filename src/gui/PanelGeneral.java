package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class PanelGeneral extends JPanel {
	private JPanel pNorte, pSur, pOeste, pEste, pCentro;
	private JLabel txtPruebaPanelGeneral;
	private DefaultTableModel tblModelo;
	public PanelGeneral() {
		
		
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255,102,120);
		setBackground(turquesa);
		setLayout(new BorderLayout());
		
		//Creamos los paneles 
		pNorte = new JPanel();
		pSur = new JPanel();
		pOeste = new JPanel();
		pEste = new JPanel();
		pCentro = new JPanel();
				
		//Creamos componentes
		txtPruebaPanelGeneral = new JLabel("General");
		
		//Añadimos paneles a ventana
		add(pNorte,BorderLayout.NORTH);
		add(pSur,BorderLayout.SOUTH);
		add(pOeste,BorderLayout.WEST);
		add(pEste,BorderLayout.EAST);
		add(pCentro, BorderLayout.CENTER);
		
		String[] columnas = {"ID", "Título", "Barrio", "Capacidad", "Precio/Noche", "Rating"};
		
		//Añadimos componenets a panel
		pCentro.add(txtPruebaPanelGeneral);
		
	}
}
