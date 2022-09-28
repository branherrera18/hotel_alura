package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaDAO {

	 final private Connection con;
	
	 public ReservaDAO(Connection con) {
		 this.con = con;
	 }
	 
	 public void guardar(Reserva reserva) {
		 
		 try {
			 
			 	final PreparedStatement statement = con.prepareStatement(
					 "INSERT INTO RESERVAS (fecha_entrada, fecha_salida, valor, forma_Pago) VALUES (?, ?, ?, ?)",
					 Statement.RETURN_GENERATED_KEYS);
			 try(statement){
				 ejecutarRegisto(reserva, statement);
			 }
			 }catch(SQLException ex) {
				 throw new RuntimeException(ex);
			 }		 
		 
	 }

	private void ejecutarRegisto(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setDate(1, reserva.getFecha_entrada());
		statement.setDate(2, reserva.getFecha_salida());
		statement.setString(3, reserva.getValor());
		statement.setString(4, reserva.getForma_pago());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		try (resultSet){
			
			while(resultSet.next()) {
				reserva.setId_reserva(resultSet.getInt(1));
				System.out.println(String.format("Reserva guardada con exito! %s", reserva));
			}
		}
			
		
	}
	
	public List<Reserva> listarReservas(){
		List<Reserva> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con){
			final PreparedStatement statement = con.prepareStatement("SELECT id_reserva, fecha_entrada, fecha_salida, valor, forma_pago FROM RESERVAS");
		
			try(statement){
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet){
					while(resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("id_reserva"), resultSet.getDate("fecha_entrada"),
								resultSet.getDate("fecha_salida"), resultSet.getString("valor"), resultSet.getString("forma_pago"));
						resultado.add(fila);
						}
					}
				}
				return resultado;
			}catch(SQLException e) {
				throw new RuntimeException(e);
		}
	}
	
	
	public int eliminar(Integer id_reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM RESERVAS WHERE id_reserva = ?");
			try(statement){
				statement.setInt(1, id_reserva);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "No puede eliminar una reserva que tenga Huespedes");
			return 0;
		}
	}
	
	public int modificar(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago, Integer id_reserva) {
		try {
			
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " + " FECHA_ENTRADA = ?, " +
		" FECHA_SALIDA = ?," + " VALOR = ?," + " FORMA_PAGO = ?" + " WHERE id_reserva = ?");
			
			try(statement){
				
				statement.setDate(1, (java.sql.Date)fecha_entrada);
				statement.setDate(2, (java.sql.Date)fecha_salida);
				statement.setString(3, valor);
				statement.setString(4, forma_pago);
				statement.setInt(5, id_reserva);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				
				return updateCount;
			}
			
			}catch (SQLException ex) {
				throw new RuntimeException(ex);
		}
		
	}
	
	public List<Reserva> listarReservas(String busqueda){
		
		List<Reserva> resultado = new ArrayList<>();
		
		final Connection con = new ConnectionFactory().recuperaConexion();
		
		try(con){
			var querySelector = "SELECT id_reserva, fecha_entrada, fecha_salida, valor, forma_pago FROM RESERVAS WHERE id_reserva = ?";
			final PreparedStatement statement = con.prepareStatement(querySelector);
			try(statement){
				statement.setString(1, busqueda);
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				try(resultSet){
					while(resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("id_reserva"), resultSet.getDate("fecha_entrada")
								,resultSet.getDate("fecha_salida"), resultSet.getString("valor"), resultSet.getString("forma_pago"));
						resultado.add(fila);
					}
				}
			}
			
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		return resultado;
	}

	
}
