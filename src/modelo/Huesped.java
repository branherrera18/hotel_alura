package modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Huesped {

	private Integer id_huesped;
	private String nombre;
	private String apellido;
	private Date fecha_nacimiento;
	private String nacionalidad;
	private String telefono;
	private int id_reserva;
	private List<Reserva> reserva;
	
	public Huesped(Integer id_huesped, String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono,
			int id_reserva) {
		
		this.id_huesped = id_huesped;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;

	}

	public Huesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, int id_reserva) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.id_reserva = id_reserva;
	}
	
	

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public Integer getId_huesped() { //Id
		return id_huesped;
	}

	public void setId_huesped(Integer id_huesped) {
		this.id_huesped = id_huesped;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	
	public void agregar(Reserva reserva) {
		if(this.reserva == null) {
			this.reserva = new ArrayList<>();
		}
		
		this.reserva.add(reserva);
	}
	
	
	@Override
	public String toString() {
		return "nombre: " + this.nombre + ", apellido: " + this.apellido + ", fecha de nacimiento: " + this.fecha_nacimiento + ", nacionalidad: " + this.nacionalidad + ", teléfono: " + this.telefono + ", reserva nro: " + this.id_reserva;
	}

	public List<Reserva> getReservas(){
		return this.reserva;
	}

}
