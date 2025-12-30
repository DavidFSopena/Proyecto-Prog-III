package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Alojamiento;
import domain.Barrio;
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
	        crearTablaAlojamiento();

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
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "usuario TEXT unique,"
				+ "email TEXT unique,"
				+ "nombre TEXT unique,"
				+ "contrasenia TEXT"
				+ ")";
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
		String sql = "SELECT id, usuario, email, nombre, contrasenia FROM Usuario WHERE nombre = ? OR email = ?";
		
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, usuarioEmail);
			ps.setString(2, usuarioEmail);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				String contraseniaBD = rs.getString("contrasenia");
				
				if (contraseniaBD.equals(contrasenia)) {
					int id = rs.getInt("id");
					String usuario = rs.getString("usuario");
					String email = rs.getString("email");
					usuarioLogeado = new Usuario(
							id,
							usuario,
							email,
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
	
	public static void crearTablaAlojamiento() {
		String sql = "CREATE TABLE IF NOT EXISTS Alojamiento("
				+ "id TEXT PRIMARY KEY,"
				+ "titulo TEXT,"
				+ "barrio TEXT,"
				+ "capacidad INTEGER,"
				+ "precio REAL,"
				+ "rating REAL,"
				+ "usuario TEXT"
				+ ")";
		try(Statement st = con.createStatement();) {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void crearTablaAlquiler() {
	    String sql = "CREATE TABLE IF NOT EXISTS Alquiler ("
	            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	            + "usuarioID INTEGER,"
	            + "idAlojamiento TEXT,"
	            + "FOREIGN KEY (usuarioID) REFERENCES Usuario(id)"
	            + ")";
	    try (Statement st = con.createStatement()) {
	        st.executeUpdate(sql);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static boolean registrarAlquiler(int usuarioID, String idAlojamiento) {
	    String sql = "INSERT INTO Alquiler (usuarioID, idAlojamiento) VALUES (?, ?)";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, usuarioID);
	        ps.setString(2, idAlojamiento);
	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public static Usuario obtenerUsuarioPorUsuarioOEmail(String usuarioEmail) {
	    String sql = "SELECT usuario, email, nombre FROM Usuario WHERE nombre = ? OR email = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, usuarioEmail);
	        ps.setString(2, usuarioEmail);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	        	int id = rs.getInt("id");
	            String usuario = rs.getString("usuario");
	            String email = rs.getString("email");
	            String nombre = rs.getString("nombre");
	            Usuario u = new Usuario(id,usuario,email,nombre);
	            rs.close();
	            return u;
	        }
	        rs.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	public static boolean actualizarUsuario(int usuarioID, String nuevoUsuario, String nuevoNombre, String nuevoEmail) {
		if(emailExiste(nuevoEmail,usuarioID)) {
			return false; //Error pq el email ya está en uso
		}
		
		if(nombreExiste(nuevoNombre,usuarioID)) {
			return false; //Error pq el email ya está en uso
		}
		
		String sql = "UPDATE Usuario SET nombre = ?, email = ?, usuario = ? WHERE id = ?";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, nuevoNombre);
	        ps.setString(2, nuevoEmail);
	        ps.setString(3, nuevoUsuario);
	        ps.setInt(4, usuarioID);
 
	        int filas = ps.executeUpdate();

	        if (filas > 0) {
	            usuarioLogeado = new Usuario(usuarioID, nuevoUsuario, nuevoEmail, nuevoNombre);
	            return true;
	        }
	        return false;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public static List<Alojamiento> obtenerListaAlojamiento(int usuarioID){
		List<Alojamiento> lista = new ArrayList<>();

		String sql =
				"SELECT al.id, al.titulo, al.barrio, al.capacidad, al.precio, al.rating " +
				"FROM Alquiler aq " +
				"JOIN Alojamiento al ON aq.idAlojamiento = al.id " +
				"WHERE aq.usuarioID = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, usuarioID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Barrio barrio = Barrio.valueOf(rs.getString("barrio").toUpperCase());

				Alojamiento a = new Alojamiento(
						rs.getString("id"),
						rs.getString("titulo"),
						barrio,
						rs.getInt("capacidad"),
						rs.getDouble("precio"),
						rs.getDouble("rating")
				);

				lista.add(a);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}
	
	public static boolean emailExiste(String email, int usuarioID) {
		String sql = "SELECT email FROM Usuario WHERE email = ? AND id != ?";
		try(PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, email);
			ps.setInt(2, usuarioID);
			ResultSet rs = ps.executeQuery();
			boolean existe = rs.next();
			return existe;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public static boolean nombreExiste(String nombre, int usuarioID) {
		String sql = "SELECT email FROM Usuario WHERE nombre = ? AND id != ?";
		try(PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setString(1, nombre);
			ps.setInt(2, usuarioID);
			ResultSet rs = ps.executeQuery();
			boolean existe = rs.next();
			return existe;
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
	}
	
	public static void upsertAlojamiento(Alojamiento a) {
	    String sql = "INSERT OR REPLACE INTO Alojamiento (id, titulo, barrio, capacidad, precio, rating) VALUES (?,?,?,?,?,?)";
	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, a.getId());
	        ps.setString(2, a.getTitulo());
	        ps.setString(3, a.getBarrio().toString());
	        ps.setInt(4, a.getCapacidad());
	        ps.setDouble(5, a.getPrecioNoche());
	        ps.setDouble(6, a.getRating());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

//	public static void cargarAlojamientosDesdeCSV(String rutaCsv) {
//	    try (java.util.Scanner sc = new java.util.Scanner(new java.io.File(rutaCsv), "UTF-8")) {
//	        if (sc.hasNextLine()) sc.nextLine();
//
//	        while (sc.hasNextLine()) {
//	            String linea = sc.nextLine().trim();
//	            if (linea.isEmpty()) continue;
//
//	            String[] c = linea.split(";");
//	            if (c.length != 6) continue;
//
//	            for (int i = 0; i < 6; i++) c[i] = c[i].trim();
//
//	            Barrio barrio = null;
//	            try {
//	                barrio = Barrio.valueOf(c[2].toUpperCase().replace(" ", "_"));
//	            } catch (Exception e) {}
//
//	            Alojamiento a = new Alojamiento(
//	                    c[0], c[1], barrio,
//	                    Integer.parseInt(c[3]),
//	                    Double.parseDouble(c[4]),
//	                    Double.parseDouble(c[5])
//	            );
//
//	            upsertAlojamiento(a);
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
}