package interfaces.boletos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import dao.BoletoDAO;
import entidades.Boleto;
import excepciones.DAOException;

public class BoletoTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 3463163062934188400L;
	
	private BoletoDAO boletos;
	private List<Boleto> datos = new ArrayList<>();
	
	public BoletoTableModel(BoletoDAO manager) {
		this.boletos = manager;
	}
	
	public void updateModel() {
		try {
			datos = boletos.obtenerTodasLasEntidades();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0: return "ID";
		case 1: return "Nombre cliente";
		case 2: return "Correo cliente";
		case 3: return "Fecha de venta";
		case 4: return "Costo";
		case 5: return "Camino";	
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
		return 6;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Boleto preguntado = datos.get(rowIndex);
		switch (columnIndex) {
		case 0: return preguntado.getId();
		case 1: return preguntado.getNombreCliente();
		case 2: return preguntado.getCorreoCliente();
		case 3: return preguntado.getFechaVenta().toString();
		case 4: return preguntado.getCosto();	
		case 5: return preguntado.getCamino();
		default: return "Error al cargar los datos";
		}
	}

}
