package domain;
import domain.HistorialBusquedas;


public class Sesion {

    private static Usuario usuarioActual;
    private static HistorialBusquedas historial = new HistorialBusquedas(10);


    public static void setUsuarioActual(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }
    
    public static HistorialBusquedas getHistorial() {
        return historial;
    }

    public static boolean hayUsuario() {
        return usuarioActual != null;
    }
}
