package entidades;

import java.util.ArrayList;
import java.util.List;

public class LineaDeTransporte {
	
	private Integer id;
	private String nombre;
	private ColorLinea color;
	private EstadoLineaDeTransporte estado;
	private List<Trayecto> recorrido;
	
	public LineaDeTransporte(Integer id, String nombre, ColorLinea color, EstadoLineaDeTransporte estado) {
		this.id = id;
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
		this.recorrido = new ArrayList<Trayecto>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ColorLinea getColor() {
		return color;
	}

	public void ColorLinea(ColorLinea color) {
		this.color = color;
	}

	public EstadoLineaDeTransporte getEstado() {
		return estado;
	}

	public void setEstado(EstadoLineaDeTransporte estado) {
		this.estado = estado;
	}

	public List<Trayecto> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(List<Trayecto> recorrido) {
		this.recorrido = recorrido;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((recorrido == null) ? 0 : recorrido.hashCode());
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
		LineaDeTransporte other = (LineaDeTransporte) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (estado != other.estado)
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
		if (recorrido == null) {
			if (other.recorrido != null)
				return false;
		} else if (!recorrido.equals(other.recorrido))
			return false;
		return true;
	}
	
	

}
