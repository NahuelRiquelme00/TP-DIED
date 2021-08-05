package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import dao.*;

public class DAOManagerImpl implements DAOManager{
	
	private Connection conn;
	
	private EstacionDAO estaciones = null;
	private MantenimientoDAO mantenimientos = null;
	private LineaDeTransporteDAO lineasDeTransporte = null;
	private TrayectoDAO trayectos = null; 
	
	public DAOManagerImpl() {
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "ramonesb");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public EstacionDAO getEstacionDAO() {
		// TODO Auto-generated method stub
		if (estaciones == null) {
			try {
				estaciones = new EstacionDAOImpl(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return estaciones;
	}

	@Override
	public MantenimientoDAO getMantenimientoDAO() {
		// TODO Auto-generated method stub
		if (mantenimientos == null) {
			mantenimientos = new MantenimientoDAOImpl(conn);
		}
		return mantenimientos;
	}

	@Override
	public LineaDeTransporteDAO getLineaDeTransporteDAO() {
		// TODO Auto-generated method stub
		if (lineasDeTransporte == null) {
			try {
				lineasDeTransporte = new LineaDeTransporteDAOImpl(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lineasDeTransporte;
	}

	@Override
	public TrayectoDAO getTrayectoDAO() {
		// TODO Auto-generated method stub
		if (trayectos == null) {
			trayectos = new TrayectoDAOImpl(conn);
		}
		return trayectos;
	}

	//Para probar el DAOManager
//	public static void main(String[] args) throws DAOException, SQLException {
//		DAOManagerImpl man = new DAOManagerImpl();
//		List<Estacion> estaciones = man.getEstacionDAO().obtenerTodasLasEntidades();
//		System.out.println(estaciones);
//	}
}
