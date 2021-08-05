package interfaces.estacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.EstacionDAO;

import entidades.Estacion;
import excepciones.DAOException;

public class EstacionTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;

	private EstacionDAO estaciones;
	
	private List<Estacion> datos = new ArrayList<>();
	
	public EstacionTableModel(EstacionDAO manager) {
		this.estaciones = manager;
	}
	
	public void updateModel()  {
		try {
			datos = estaciones.obtenerTodasLasEntidades();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Estacion> getDatos() {
		return datos;
	}

	public void setDatos(List<Estacion> datos) {
		this.datos = datos;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0: return "ID";
		case 1: return "Nombre";
		case 2: return "Apertura";
		case 3: return "Cierre";
		case 4: return "Estado";			
		default: return "error";
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
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Estacion preguntado = datos.get(rowIndex);
		switch (columnIndex) {
		case 0: return preguntado.getId();
		case 1: return preguntado.getNombre();
		case 2: return preguntado.getHorarioApertura().toString();
		case 3: return preguntado.getHorarioCierre().toString();
		case 4: return preguntado.getEstado().toString();			
		default: return "error";
		}
	}

}
