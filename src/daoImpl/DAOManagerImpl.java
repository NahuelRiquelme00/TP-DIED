package daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import dao.*;

public class DAOManagerImpl implements DAOManager{
	
	private static DAOManager DAOManagerInstance;
	
	private static Connection conn;
	
	private EstacionDAO estaciones = null;
	private MantenimientoDAO mantenimientos = null;
	private LineaDeTransporteDAO lineasDeTransporte = null;
	private TrayectoDAO trayectos = null; 
	private BoletoDAO boletos = null;
	
	public static DAOManager getInstance() {
		if(DAOManagerInstance==null) {
			DAOManagerInstance = new DAOManagerImpl();
		}
		return DAOManagerInstance;
	}
	
	private DAOManagerImpl() {
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
		//try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		return estaciones;
	}

	@Override
	public MantenimientoDAO getMantenimientoDAO() {
		// TODO Auto-generated method stub
		if (mantenimientos == null) {
			mantenimientos = new MantenimientoDAOImpl(conn);
		}
		//try {conn.close();} catch (SQLException e) {e.printStackTrace();}
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
		//try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		return lineasDeTransporte;
	}

	@Override
	public TrayectoDAO getTrayectoDAO() {
		// TODO Auto-generated method stub
		if (trayectos == null) {
			try {
				trayectos = new TrayectoDAOImpl(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		return trayectos;
	}

	@Override
	public BoletoDAO getBoletoDAO() {
		// TODO Auto-generated method stub
		if(boletos == null) {
			boletos = new BoletoDAOimpl(conn);
		}
		//try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		return boletos;
	}

}
