package daoImpl;

import java.sql.Connection;
import java.util.List;

import dao.TrayectoDAO;
import entidades.Trayecto;
import excepciones.DAOException;

public class TrayectoDAOImpl implements TrayectoDAO{
	
	private Connection conn;
	
	public TrayectoDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void crearEntidad(Trayecto e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarEntidad(Trayecto e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEntidad(Trayecto e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Trayecto> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trayecto obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
