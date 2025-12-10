package domain;

public class Alojamiento {
	private String id;
	private String titulo;
	private Barrio barrio;
	private int capacidad;
	private double precioNoche;
	private double rating;

	public Alojamiento(String id, String titulo, Barrio barrio, int capacidad, double precioNoche, double rating) {
		this.id = id;
		this.titulo = titulo;
		this.barrio = barrio;
		this.capacidad = capacidad;
		this.precioNoche = precioNoche;
		this.rating = rating;
	}

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public Barrio getBarrio() {
		return barrio;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public double getPrecioNoche() {
		return precioNoche;
	}

	public double getRating() {
		return rating;
	}
}

