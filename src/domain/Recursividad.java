package domain;

import java.util.List;

public class Recursividad {
	public static int sumarBusquedasRec(List<Busqueda> busquedas) {
        if (busquedas == null || busquedas.isEmpty()) return 0;

        Busqueda b = busquedas.get(0);
        

        return b.getResultados() + sumarBusquedasRec(busquedas.subList(1, busquedas.size()));    }
}
