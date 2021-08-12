package interfaces.lineas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.LineaDeTransporteDAO;
import entidades.LineaDeTransporte;
import excepciones.DAOException;

public class LineaTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -1155934481376911107L;
	
	private LineaDeTransporteDAO lineas;
	private List<LineaDeTransporte> datos = new ArrayList<>();

	public LineaTableModel(LineaDeTransporteDAO manager) {
		this.lineas = manager;
	}

	public void updateModel()  {
		try {
			datos = lineas.obtenerTodasLasEntidades();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public List<LineaDeTransporte> getDatos() {
		return datos;
	}

	public void setDatos(List<LineaDeTransporte> datos) {
		this.datos = datos;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0: return "ID";
		case 1: return "Nombre";
		case 2: return "Color";
		case 3: return "Estado";			
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
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		LineaDeTransporte preguntado = datos.get(rowIndex);
		switch (columnIndex) {
		case 0: return preguntado.getId();
		case 1: return preguntado.getNombre();
		case 2: return preguntado.getColor().toString();
		case 3: return preguntado.getEstado().toString();			
		default: return "Error al cargar los datos";
		}
	}

}
