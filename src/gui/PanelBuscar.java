package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelBuscar extends JPanel {
	private JPanel pNorte, pSur, pOeste, pEste, pCentro;
	private JLabel txtPruebaPanelBuscar;
	private JTable tabla;
	private DefaultTableModel modelo;
	private JButton btnRecargar;

	public PanelBuscar() {
		Color turquesa = new Color(96, 198, 194);
		Color coral = new Color(255, 102, 120);
		setBackground(turquesa);
		setLayout(new BorderLayout(10, 10));
		setOpaque(false);

		// Creamos los paneles
		pNorte = new JPanel();
		pSur = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
		pOeste = new JPanel();
		pEste = new JPanel();
		pCentro = new JPanel(new BorderLayout());
		pNorte.setOpaque(false);
		pSur.setOpaque(false);
		pOeste.setOpaque(false);
		pEste.setOpaque(false);
		pCentro.setOpaque(false);

		// Creamos componentes
		txtPruebaPanelBuscar = new JLabel("Buscar alojamientos");
		txtPruebaPanelBuscar.setFont(new Font("Segoe UI", Font.BOLD,18));
		
		pNorte.add(txtPruebaPanelBuscar);

		// Añadimos paneles a ventana
		add(pNorte, BorderLayout.NORTH);
		add(pSur, BorderLayout.SOUTH);
		add(pOeste, BorderLayout.WEST);
		add(pEste, BorderLayout.EAST);
		add(pCentro, BorderLayout.CENTER);

		// Añadimos componenets a panel
		pCentro.add(txtPruebaPanelBuscar);
	}
}
