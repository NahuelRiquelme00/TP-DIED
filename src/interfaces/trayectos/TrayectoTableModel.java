package interfaces.trayectos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.TrayectoDAO;
import entidades.Trayecto;
import excepciones.DAOException;

public class TrayectoTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 5971761429533537637L;

	private TrayectoDAO trayectos;
	private List<Trayecto> datos = new ArrayList<>();
	
	public TrayectoTableModel(TrayectoDAO manager) {
		this.trayectos = manager;
	}
	
	public void updateModel() {
		try {
			datos = trayectos.obtenerTodasLasEntidades();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Trayecto> getDatos() {
		return datos;
	}

	public void setDatos(List<Trayecto> datos) {
		this.datos = datos;
	}
	
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0: return "ID tramo";
		case 1: return "Origen";
		case 2: return "Destino";
		case 3: return "Distancia";
		case 4: return "Duracion";
		case 5: return "Capacidad";
		case 6: return "Estado";
		case 7: return "Costo";
		default: return "Error al cargar nombres";
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datos.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Trayecto preguntado = datos.get(rowIndex);
		
		switch (columnIndex) {
		case 0: return preguntado.getId();
		case 1: return preguntado.getOrigen().getNombre();
		case 2: return preguntado.getDestino().getNombre();
		case 3: return preguntado.getDistancia();
		case 4: return preguntado.getDuracion();	
		case 5: return preguntado.getCantidadMaximaPasajeros();
		case 6: return preguntado.getEstado().toString();
		case 7: return preguntado.getCosto();
		default: return "Error al cargar los datos";
		}
	}

}
