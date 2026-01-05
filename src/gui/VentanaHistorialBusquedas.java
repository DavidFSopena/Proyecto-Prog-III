package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.Sesion;

public class VentanaHistorialBusquedas extends JDialog{
	private PanelBuscar panelBuscar;
	
	public VentanaHistorialBusquedas(PanelBuscar panelBuscar) {
		this.panelBuscar = panelBuscar;
        setTitle("Historial de búsquedas");
        setModal(true);
        setSize(new Dimension(800, 400));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTable tabla = new JTable(new ModeloTablaHistorialBusquedas(Sesion.getHistorial().getLista()));
        Tabla.aplicar(tabla);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2 && tabla.getSelectedRow() != -1) {

                    int fila = tabla.getSelectedRow();
                    domain.Busqueda b = Sesion.getHistorial().getLista().get(fila);

                    panelBuscar.setBarrioDesdeString(b.getBarrio());
                    panelBuscar.setAdultos(b.getAdultos());
                    panelBuscar.setNinos(b.getNinos());

                    panelBuscar.ejecutarBusquedaDesdeFiltros(false);
                    dispose();
                }
            }
        });


        tabla.getColumnModel().getColumn(1).setPreferredWidth(550);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
