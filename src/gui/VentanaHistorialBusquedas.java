package gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import domain.Sesion;
import javax.swing.JLabel;
import domain.Recursividad;

public class VentanaHistorialBusquedas extends JDialog{
	private PanelBuscar panelBuscar;
	
	
	public VentanaHistorialBusquedas(PanelBuscar panelBuscar) {
		this.panelBuscar = panelBuscar;
        setTitle("Historial de búsquedas");
        setModal(true);
        setSize(new Dimension(800, 400));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel lblEstadis = new JLabel();
        lblEstadis.setHorizontalAlignment(JLabel.CENTER);
        add(lblEstadis, BorderLayout.NORTH);
        actualizarEstadis(lblEstadis);

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
        JButton btnLimpiar = new JButton("Limpiar historial");
    	btnLimpiar.addActionListener(e -> {
    	    Sesion.getHistorial().clear();
    	    tabla.setModel(new ModeloTablaHistorialBusquedas(Sesion.getHistorial().getLista()));
            actualizarEstadis(lblEstadis);

    	});

    	JPanel sur = new JPanel();
    	sur.add(btnLimpiar);
    	add(sur, BorderLayout.SOUTH);
    }
	
	private void actualizarEstadis(JLabel lblEstadis) {
	    int totalResultados = Recursividad.sumarBusquedasRec(
	            Sesion.getHistorial().getLista()
	    );
	    int numBusquedas = Sesion.getHistorial().getLista().size();

	    lblEstadis.setText(
	        "Búsquedas: " + numBusquedas +", Resultados totales mostrados: " + totalResultados
	    );
	}

	
}
