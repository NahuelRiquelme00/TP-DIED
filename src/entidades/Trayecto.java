package entidades;

public class Trayecto {
	
	private Integer id;
	private LineaDeTransporte linea;
	private Estacion origen;
	private Estacion destino;
	private Double distancia;
	private Integer duracion;
	private Integer cantidadMaximaPasajeros;
	private EstadoTrayecto estado;
	private Double costo;

	public Trayecto(Integer id, LineaDeTransporte linea, Estacion origen, Estacion destino, Double distancia,
			Integer duracion, Integer cantidadMaximaPasajeros, EstadoTrayecto estado, Double costo) {
		super();
		this.id = id;
		this.linea = linea;
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.duracion = duracion;
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
		this.estado = estado;
		this.costo = costo;
	}

	public Trayecto(LineaDeTransporte linea, Estacion origen, Estacion destino, Double distancia, Integer duracion,
			Integer cantidadMaximaPasajeros, EstadoTrayecto estado, Double costo) {
		super();
		this.linea = linea;
		this.origen = origen;
		this.destino = destino;
		this.distancia = distancia;
		this.duracion = duracion;
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
		this.estado = estado;
		this.costo = costo;
	}

	public LineaDeTransporte getLinea() {
		return linea;
	}

	public void setLinea(LineaDeTransporte linea) {
		this.linea = linea;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public void setDestino(Estacion destino) {
		this.destino = destino;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getCantidadMaximaPasajeros() {
		return cantidadMaximaPasajeros;
	}

	public void setCantidadMaximaPasajeros(Integer cantidadMaximaPasajeros) {
		this.cantidadMaximaPasajeros = cantidadMaximaPasajeros;
	}

	public EstadoTrayecto getEstado() {
		return estado;
	}

	public void setEstado(EstadoTrayecto estado) {
		this.estado = estado;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cantidadMaximaPasajeros == null) ? 0 : cantidadMaximaPasajeros.hashCode());
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((distancia == null) ? 0 : distancia.hashCode());
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((linea == null) ? 0 : linea.hashCode());
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
		Trayecto other = (Trayecto) obj;
		if (cantidadMaximaPasajeros == null) {
			if (other.cantidadMaximaPasajeros != null)
				return false;
		} else if (!cantidadMaximaPasajeros.equals(other.cantidadMaximaPasajeros))
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
		if (distancia == null) {
			if (other.distancia != null)
				return false;
		} else if (!distancia.equals(other.distancia))
			return false;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (linea == null) {
			if (other.linea != null)
				return false;
		} else if (!linea.equals(other.linea))
			return false;
		if (origen == null) {
			if (other.origen != null)
				return false;
		} else if (!origen.equals(other.origen))
			return false;
		return true;
	}

	@Override
	public String toString() {
//		return "Trayecto [id=" + id + ", linea=" + linea + ", origen=" + origen + ", destino=" + destino
//				+ ", distancia=" + distancia + ", duracion=" + duracion + ", cantidadMaximaPasajeros="
//				+ cantidadMaximaPasajeros + ", estado=" + estado + ", costo=" + costo + "]";
		return linea + " [" +origen.getNombre() + "," + destino.getNombre() +"]"+" -> ";
	}
	
	public Boolean activa() {
		return this.getEstado().equals(EstadoTrayecto.ACTIVO);
	}
	
	public String toString2() {
		return linea + " [" +origen.getNombre() + "," + destino.getNombre() +"]"+" -> ";
				// Metro A1 VERDE_CLARA [Estacion A,Estacion B] ->
	}
	
	

}
