package gui;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Alojamiento;

public class ModeloTablaMisAlojamientosUsuario extends DefaultTableModel {
	
	private List<String> titulos = Arrays.asList("ID","Titulo","Barrio","Capacidad","Precio/Noche","Rating");
	private List<Alojamiento> listaAlojamientos;

	public ModeloTablaMisAlojamientosUsuario(List<Alojamiento> l) {
		this.listaAlojamientos = l;
	}

	@Override
	public int getRowCount() {
		if(listaAlojamientos==null)
			return 0;
		return listaAlojamientos.size();
	}

	@Override
	public int getColumnCount() {
		return titulos.size();
	}

	@Override
	public String getColumnName(int column) {
		return titulos.get(column);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Alojamiento a = listaAlojamientos.get(row);
		/*Si la columna es la 0, devuelvo el id del alojamiento
		  Si la columna es la 1, devuelvo el titulo del alojamiento
		  Si la columna es la 2, devuelvo el barrio de créditos del alojamiento...
		  En caso contrario, devolvemos null*/
		switch(column) {
		case 0: return a.getId();
		case 1: return a.getTitulo();
		case 2: return a.getBarrio();
		case 3: return a.getCapacidad();
		case 4: return a.getPrecioNoche();
		case 5: return a.getRating();
		default: return null;
		}
	}
}
