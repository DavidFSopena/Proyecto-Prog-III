package domain;

public class Busqueda {
	private String fecha;
	private String filtros; 
	private int resultados;
	private String barrio;
	private int adultos;
	private int ninos;
	
	public Busqueda(String fecha, String filtros, int resultados, String barrio, int adultos, int ninos) {
        this.fecha = fecha;
        this.filtros = filtros;
        this.resultados = resultados;
        this.barrio = barrio;
        this.adultos = adultos;
        this.ninos = ninos;
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

	public String getBarrio() {
		return barrio;
	}

	public int getAdultos() {
		return adultos;
	}

	public int getNinos() {
		return ninos;
	}
	
	
}
