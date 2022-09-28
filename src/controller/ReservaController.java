package controller;

import java.sql.Date;
import java.util.List;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO= new ReservaDAO(factory.recuperaConexion());
	}
	
	
	public int modificar(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago, Integer id_reserva) {
		return reservaDAO.modificar(fecha_entrada, fecha_salida, valor, forma_pago, id_reserva);
	}
	
	public int eliminar(Integer id_reserva) {
		return reservaDAO.eliminar(id_reserva);
	}
	
	public List<Reserva> listarReservas(){
		return reservaDAO.listarReservas();  
	}
	
	public List<Reserva> listarReservas(String busqueda){
		return reservaDAO.listarReservas(busqueda);
	}
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
}
