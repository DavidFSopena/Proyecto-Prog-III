package domain;

import java.util.ArrayList;

public class HistorialBusquedas {
	private ArrayList<Busqueda> lista;
    private int max;

    public HistorialBusquedas(int max) {
        this.max = max;
        this.lista = new ArrayList<>();
    }

	public ArrayList<Busqueda> getLista() {
		return lista;
	}
	
	public void add(Busqueda b) {
        lista.add(0, b);

        while (lista.size() > max) {
            lista.remove(lista.size() - 1);
        }
    }
    
    public void clear() {
    	lista.clear();
    }
}
