package daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import dao.LineaDeTransporteDAO;
import entidades.LineaDeTransporte;
import excepciones.DAOException;

public class LineaDeTransporteDAOImpl implements LineaDeTransporteDAO{
	
	private Connection conn;
	
	public LineaDeTransporteDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
	}

	@Override
	public void crearEntidad(LineaDeTransporte e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminarEntidad(LineaDeTransporte e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarEntidad(LineaDeTransporte e) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LineaDeTransporte> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineaDeTransporte obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
