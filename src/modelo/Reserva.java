package modelo;

import java.sql.Date;

public class Reserva {
	
	private Integer id_reserva;
	private Date fecha_entrada;
	private Date fecha_salida;
	private String valor;
	private String forma_pago;
	private Integer huesped_id;
	private int reserva_id;
	
	public Reserva(Integer id_reserva, Date fecha_entrada, Date fecha_salida, String valor , String forma_pago) {

		this.id_reserva = id_reserva;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}

	public Reserva(Date fecha_entrada, Date fecha_salida, String valor, String forma_pago) {
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.valor = valor;
		this.forma_pago = forma_pago;
	}

	public Reserva(int reserva_id) {
		this.reserva_id = reserva_id; 
	}

	public Integer getId_reserva() {
		return id_reserva;
	}

	public void setId_reserva(Integer id_reserva) {
		this.id_reserva = id_reserva;
	}

	public Date getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public Date getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}

	public Integer getHuesped_id() {
		return huesped_id;
	}

	public void setHuesped_id(Integer huesped_id) {
		this.huesped_id = huesped_id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return String.format("{id: %s, Checkin: %s, Checkout: %s, Valor: %s, Forma de Pago: %s}", this.id_reserva, this.fecha_entrada,
				this.fecha_salida, this.valor, this.forma_pago);
				}
	
	
	
}
