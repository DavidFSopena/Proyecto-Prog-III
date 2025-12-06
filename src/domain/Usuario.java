package domain;

public class Usuario {
	private String usuario;
	private String email;
	private String nombre;
	private char[] contrasenia;

	public Usuario(String usuario, String email, String nombre) {
		this.usuario = usuario;
		this.email = email;
		this.nombre = nombre;
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

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", email=" + email + ", nombre=" + nombre + "]";
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
}
