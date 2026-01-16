package domain;

import java.util.Arrays;

public class Usuario {
	private int id;
	private String usuario;
	private String email;
	private String nombre;
	private char[] contrasenia;

	public Usuario(int id, String usuario, String email, String nombre) {
		this.id = id;
		this.usuario = usuario;
		this.email = email;
		this.nombre = nombre;
	}
	
	public Usuario(String usuario, String email, String nombre) {
	    this.usuario = usuario;
	    this.email = email;
	    this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", email=" + email + ", nombre=" + nombre
				+ ", contrasenia=" + Arrays.toString(contrasenia) + "]";
	}

}
