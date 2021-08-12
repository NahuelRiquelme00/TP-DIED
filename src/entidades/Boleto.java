package entidades;

import java.time.LocalDate;

public class Boleto {
	private Integer id;
	private String correoCliente;
	private String nombreCliente;
	private LocalDate fechaVenta;
	private String origen;
	private String destino;
	private String camino;
	private Double costo;
	
	public Boleto(Integer id, String correoCliente, String nombreCliente, LocalDate fechaVenta, String origen,
			String destino, String camino, Double costo) {
		this.id = id;
		this.correoCliente = correoCliente;
		this.nombreCliente = nombreCliente;
		this.fechaVenta = fechaVenta;
		this.origen = origen;
		this.destino = destino;
		this.camino = camino;
		this.costo = costo;
	}
	public Boleto(String correoCliente, String nombreCliente, LocalDate fechaVenta, String origen, String destino,
			String camino, Double costo) {
		this.correoCliente = correoCliente;
		this.nombreCliente = nombreCliente;
		this.fechaVenta = fechaVenta;
		this.origen = origen;
		this.destino = destino;
		this.camino = camino;
		this.costo = costo;
	}
	public String getCorreoCliente() {
		return correoCliente;
	}
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public LocalDate getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Double getCosto() {
		return costo;
	}
	public void setCosto(Double costo) {
		this.costo = costo;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCamino() {
		return camino;
	}

	public void setCamino(String camino) {
		this.camino = camino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((camino == null) ? 0 : camino.hashCode());
		result = prime * result + ((correoCliente == null) ? 0 : correoCliente.hashCode());
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((fechaVenta == null) ? 0 : fechaVenta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombreCliente == null) ? 0 : nombreCliente.hashCode());
		result = prime * result + ((origen == null) ? 0 : origen.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boleto other = (Boleto) obj;
		if (camino == null) {
			if (other.camino != null)
				return false;
		} else if (!camino.equals(other.camino))
			return false;
		if (correoCliente == null) {
			if (other.correoCliente != null)
				return false;
		} else if (!correoCliente.equals(other.correoCliente))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (fechaVenta == null) {
			if (other.fechaVenta != null)
				return false;
		} else if (!fechaVenta.equals(other.fechaVenta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombreCliente == null) {
			if (other.nombreCliente != null)
				return false;
		} else if (!nombreCliente.equals(other.nombreCliente))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		return true;
	}
	
}
