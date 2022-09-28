package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedDAO {

	private Connection con;
	
	public HuespedDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Huesped huesped, int reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRegistro(huesped, reserva, statement);
			}
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void ejecutarRegistro(Huesped huesped, int reserva, PreparedStatement statement) throws SQLException {
		
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFecha_nacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, (reserva));
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try(resultSet){
			while(resultSet.next()) {
				huesped.setId_huesped(resultSet.getInt(1));;
				System.out.println(String.format("Huesped guardado correctamente!! %s", reserva));
			}
		}
		
	}
	
	public List<Huesped> listarHuespedes(){
		List<Huesped> resultado = new ArrayList<>();
		
		try {
			var querySelector = "SELECT id_huesped, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM HUESPEDES";
			
			final PreparedStatement statement = con.prepareStatement(querySelector);
			
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet){
					while(resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("id_huesped"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fecha_nacimiento"),
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"), 
								resultSet.getInt("id_reserva"));
						resultado.add(huesped);
					}
				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return resultado;
	}
	
	public List<Huesped> listarHuespedes(String busqueda){
		List<Huesped> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con){
			var querySelector = "SELECT id_huesped, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM V_HUESPEDES_RESERVAS WHERE nombre = ? or Busqueda = ?";
			final PreparedStatement statement = con.prepareStatement(querySelector);
			try(statement){
				statement.setString(1, busqueda);
				statement.setString(2, busqueda);
				statement.setString(3, busqueda);
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("id_huesped"), resultSet.getString("nombre"),
								resultSet.getString("apellido"), resultSet.getDate("fecha_nacimiento"), 
								resultSet.getString("nacionalidad"),
								resultSet.getString("telefono"), resultSet.getInt("id_reserva"));
						resultado.add(fila);
					}
				}
			}
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		return resultado;
		
	}
	
	public int eliminarHuesped(Integer id_huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE id_huesped = ?");
			try(statement){
				statement.setInt(1, id_huesped);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int modificarHuespedes(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, int id_reserva, Integer id_huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE HUESPEDES SET" + " NOMBRE = ?, " +
		"APELLIDO = ?," + " FECHA_NACIMIENTO = ?," + " NACIONALIDAD = ?," + " TELEFONO = ?," + " TELEFONO = ?," +
					" ID_RESERVA = ?" + "WHERE ID = ?");
			try(statement){
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fecha_nacimiento);
				statement.setString(4, nacionalidad);
				statement.setString(5, telefono);
				statement.setInt(6, id_reserva);
				statement.setInt(7, id_huesped);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
				
			}
		}catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
