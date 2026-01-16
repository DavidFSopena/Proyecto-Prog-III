package domain;

import java.util.List;

public class Recursividad {
	public static int contarBusquedasConBarrioRec(List<Busqueda> busquedas, String barrio) {
        if (busquedas == null || busquedas.isEmpty()) return 0;

        Busqueda b = busquedas.get(0);
        int suma = 0;
        
        if (b.getBarrio() != null && b.getBarrio().equalsIgnoreCase(barrio)) {
        	suma = 1;
        	
        }

        return suma + contarBusquedasConBarrioRec(busquedas.subList(1, busquedas.size()), barrio);
    }
}
