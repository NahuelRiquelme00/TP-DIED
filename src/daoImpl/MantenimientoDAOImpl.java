package daoImpl;

import java.sql.Connection;
import java.util.List;

import dao.MantenimientoDAO;
import entidades.Mantenimiento;
import excepciones.DAOException;

public class MantenimientoDAOImpl implements MantenimientoDAO{
	
	private Connection conn;
	
	public MantenimientoDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void crearEntidad(Mantenimiento e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarEntidad(Mantenimiento e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEntidad(Mantenimiento e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Mantenimiento> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mantenimiento obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
