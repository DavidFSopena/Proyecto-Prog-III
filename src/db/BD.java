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
	
	public static void initBD(String nombreBD)  {
		con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + nombreBD);

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
		try (Statement st = con.createStatement();){
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean registrarUsuario(Usuario u, String contrasenia) {
		String sql = "INSERT INTO Usuario (usuario, email, nombre, contrasenia) VALUES (?,?,?,?)";
		try(PreparedStatement ps = con.prepareStatement(sql);) {
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
		String sql = "SELECT contrasenia FROM Usuario WHERE Usuario = ? or Email = ?";
		
		try(PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, usuarioEmail);
			ps.setString(2, usuarioEmail);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String passBD = rs.getString(1);
				return passBD.equals(contrasenia);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
