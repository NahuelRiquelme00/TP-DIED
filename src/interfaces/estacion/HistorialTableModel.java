package interfaces.estacion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entidades.Mantenimiento;

public class HistorialTableModel extends AbstractTableModel{

	private static final long serialVersionUID = -7046484432099295965L;

	private List<Mantenimiento> datos = new ArrayList<>();
	
	public HistorialTableModel(List<Mantenimiento> mant) {
		datos = mant;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0: return "ID";
		case 1: return "Fecha de inicio";
		case 2: return "Fecha de fin";
		case 3: return "Observaciones";			
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
		Mantenimiento preguntado = datos.get(rowIndex);
		switch (columnIndex) {
		case 0: return preguntado.getId();
		case 1: return preguntado.getFechaInicio();
		case 2: return preguntado.getFechaFin();
		case 3: return preguntado.getObservaciones();
		default: return "Error al cargar los datos";
		}
	}

}
