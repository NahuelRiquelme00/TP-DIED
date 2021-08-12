package entidades;

public class LineaDeTransporte {
	
	private Integer id;
	private String nombre;
	private ColorLinea color;
	private EstadoLineaDeTransporte estado;
	
	public LineaDeTransporte(Integer id, String nombre, ColorLinea color, EstadoLineaDeTransporte estado) {
		this.id = id;
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
	}

	public LineaDeTransporte(String nombre, entidades.ColorLinea color, EstadoLineaDeTransporte estado) {
		this.nombre = nombre;
		this.color = color;
		this.estado = estado;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setColor(ColorLinea color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
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
		LineaDeTransporte other = (LineaDeTransporte) obj;
		if (color != other.color)
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
		return true;
	}

	@Override
	public String toString() {
		//return "LineaDeTransporte [id=" + id + ", nombre=" + nombre + ", color=" + color + ", estado=" + estado + "]";
		return nombre + " " + color;
	}
	
	public Boolean activa() {
		return this.getEstado().equals(EstadoLineaDeTransporte.ACTIVA);
	}
	
}
