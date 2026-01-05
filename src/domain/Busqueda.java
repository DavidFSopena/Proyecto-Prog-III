package domain;

public class Busqueda {
	private String fecha;
	private String filtros; 
	private int resultados; 
	
	public Busqueda(String fecha, String filtros, int resultados) {
        this.fecha = fecha;
        this.filtros = filtros;
        this.resultados = resultados;
    }

	public String getFecha() {
		return fecha;
	}

	public String getFiltros() {
		return filtros;
	}

	public int getResultados() {
		return resultados;
	}
	
	
}
