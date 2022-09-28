package controller;


import java.sql.Date;
import java.util.List;

import DAO.HuespedDAO;
import factory.ConnectionFactory;
import modelo.Huesped;

public class HuespedController {

	
	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		
		var factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
		
	}
	
	public List<Huesped> listarHuespedes(){
		return huespedDAO.listarHuespedes();
	}
	
	public List<Huesped> listarHuespedes(String busqueda){
		return huespedDAO.listarHuespedes(busqueda);
	}
	
	public void guardar(Huesped huesped, int reserva) {
		huespedDAO.guardar(huesped, reserva);
	}
	
	public int modificarHuesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, int id_reserva, Integer id_huesped) {
		return huespedDAO.modificarHuespedes(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva, id_huesped);
	}
	
	public int eliminarHuesped(Integer id_huesped) {
		return huespedDAO.eliminarHuesped(id_huesped);
	}
}
