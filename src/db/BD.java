package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Usuario;

public class BD {
	
	static Connection con;
	
	public static Usuario usuarioLogeado;
	
	public static void initBD(String nombreBD)  {
		con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);
			
			crearTablaUsuario();
	        crearTablaAlquiler();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeBD() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablaUsuario() {
		String sql = "CREATE TABLE IF NOT EXISTS Usuario ("
				+ "usuario TEXT PRIMARY KEY,"
				+ "email TEXT unique,"
				+ "nombre TEXT,"
				+ "contrasenia TEXT)";
		try (Statement st = con.createStatement()) {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean registrarUsuario(Usuario u, String contrasenia) {
		String sql = "INSERT INTO Usuario (usuario, email, nombre, contrasenia) VALUES (?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, u.getUsuario());
			ps.setString(2, u.getEmail());
			ps.setString(3, u.getNombre());
			ps.setString(4, contrasenia);
			ps.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static boolean validarLogin(String usuarioEmail, String contrasenia) {
		String sql = "SELECT usuario, email, nombre, contrasenia FROM Usuario WHERE usuario = ? OR email = ?";
		
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuarioEmail);
			ps.setString(2, usuarioEmail);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String contraseniaBD = rs.getString("contrasenia");
				
				if (contraseniaBD.equals(contrasenia)) {
					usuarioLogeado = new Usuario(
							rs.getString("usuario"),
							rs.getString("email"),
							rs.getString("nombre")
					);
					rs.close();
					return true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void crearTablaAlquiler() {
	    String sql = "CREATE TABLE IF NOT EXISTS Alquiler ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "usuario TEXT,"
	            + "idAlojamiento TEXT"
	            + ")";
	    try (Statement st = con.createStatement()) {
	        st.executeUpdate(sql);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean registrarAlquiler(String usuario, String idAlojamiento) {
	    String sql = "INSERT INTO Alquiler (usuario, idAlojamiento) VALUES (?, ?)";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, usuario);
	        ps.setString(2, idAlojamiento);
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Usuario obtenerUsuarioPorUsuarioOEmail(String usuarioEmail) {
	    String sql = "SELECT usuario, email, nombre FROM Usuario WHERE usuario = ? OR email = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, usuarioEmail);
	        ps.setString(2, usuarioEmail);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            String usuario = rs.getString("usuario");
	            String email = rs.getString("email");
	            String nombre = rs.getString("nombre");
	            rs.close();
	            return new Usuario(usuario, email, nombre);
	        }
	        rs.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public static boolean actualizarUsuario(String usuarioOriginal, String nuevoNombre, String nuevoEmail) {
	    String sql = "UPDATE Usuario SET nombre = ?, email = ? WHERE usuario = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, nuevoNombre);
	        ps.setString(2, nuevoEmail);
	        ps.setString(3, usuarioOriginal);

	        int filas = ps.executeUpdate();

	        if (filas > 0) {
	            usuarioLogeado = new Usuario(usuarioOriginal, nuevoEmail, nuevoNombre);
	            return true;
	        }
	        return false;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}