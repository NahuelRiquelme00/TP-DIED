package entidades;

import java.time.LocalTime;

public class Estacion {
	
	private Integer id;
	private String nombre;
	private LocalTime horarioApertura;
	private LocalTime horarioCierre;
	private EstadoEstacion estado;
	
	public Estacion(Integer id, String nombre, LocalTime horarioApertura, LocalTime horarioCierre,EstadoEstacion estado){
		this.id = id;
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}

	public Estacion(String nombre, LocalTime horarioApertura, LocalTime horarioCierre,EstadoEstacion estado){
		this.nombre = nombre;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
		this.estado = estado;
	}

	public Estacion() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalTime getHorarioApertura() {
		return horarioApertura;
	}

	public void setHorarioApertura(LocalTime horarioApertura) {
		this.horarioApertura = horarioApertura;
	}

	public LocalTime getHorarioCierre() {
		return horarioCierre;
	}

	public void setHorarioCierre(LocalTime horarioCierre) {
		this.horarioCierre = horarioCierre;
	}

	public EstadoEstacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoEstacion estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
//		return "Estacion [id=" + id + ", nombre=" + nombre + ", horarioApertura=" + horarioApertura + ", horarioCierre="
//				+ horarioCierre + ", estado=" + estado + "]";
		return nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((horarioApertura == null) ? 0 : horarioApertura.hashCode());
		result = prime * result + ((horarioCierre == null) ? 0 : horarioCierre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Estacion other = (Estacion) obj;
		if (estado != other.estado)
			return false;
		if (horarioApertura == null) {
			if (other.horarioApertura != null)
				return false;
		} else if (!horarioApertura.equals(other.horarioApertura))
			return false;
		if (horarioCierre == null) {
			if (other.horarioCierre != null)
				return false;
		} else if (!horarioCierre.equals(other.horarioCierre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	public Boolean operativa() {
		return this.estado.equals(EstadoEstacion.OPERATIVA);
	}
	
	
	
}
