package gui;

import java.util.ArrayList;
import domain.Busqueda;


import javax.swing.table.AbstractTableModel;

public class ModeloTablaHistorialBusquedas extends AbstractTableModel{
	private ArrayList<Busqueda> datos;
    private String[] columnas = { "Fecha", "Filtros", "Resultados" };

    public ModeloTablaHistorialBusquedas(ArrayList<Busqueda> datos) {
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Busqueda b = datos.get(rowIndex);

        if (columnIndex == 0) return b.getFecha();
        if (columnIndex == 1) return b.getFiltros();
        return b.getResultados();
    }
}
