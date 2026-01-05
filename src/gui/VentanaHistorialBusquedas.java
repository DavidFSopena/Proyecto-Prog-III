package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Sesion;

public class VentanaHistorialBusquedas extends JDialog{
	public VentanaHistorialBusquedas() {
        setTitle("Historial de búsquedas");
        setModal(true);
        setSize(new Dimension(800, 400));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTable tabla = new JTable(new ModeloTablaHistorialBusquedas(Sesion.getHistorial().getLista()));
        Tabla.aplicar(tabla);

        tabla.getColumnModel().getColumn(1).setPreferredWidth(550);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
