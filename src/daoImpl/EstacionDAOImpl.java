package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import dao.EstacionDAO;
import entidades.Estacion;
import entidades.EstadoEstacion;
import excepciones.DAOException;

public class EstacionDAOImpl implements EstacionDAO{
	
	final String INSERT = "INSERT INTO tp_died.estacion(nombre, horarioApertura, horarioCierre, estado) VALUES (?,?,?,?::tp_died.estadoEstacion)";
	final String UPDATE = "UPDATE tp_died.estacion SET nombre = ?, horarioApertura = ?, horarioCierre = ?, estado = ?::tp_died.estadoEstacion WHERE id_estacion = ?";
	final String DELETE = "DELETE FROM tp_died.estacion WHERE id_estacion = ?"; 
	final String GETONE = "SELECT * FROM tp_died.estacion WHERE id_estacion = ?";
	final String GETALL = "SELECT * FROM tp_died.estacion";
	
	private Connection conn;
	
	public EstacionDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void crearEntidad(Estacion e) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(INSERT, stat.RETURN_GENERATED_KEYS);
			stat.setString(1, e.getNombre());
			stat.setTime(2, Time.valueOf(e.getHorarioApertura()));
			stat.setTime(3, Time.valueOf(e.getHorarioCierre()));
			stat.setString(4, e.getEstado().toString());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
			rs = stat.getGeneratedKeys();
			if (rs.next()) {
				e.setId(rs.getInt(1));
			}else {
				throw new DAOException("No puedo asigar ID a esta estacion");
			}
		} catch (SQLException ex){
			throw new DAOException("Error en SQL", ex);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL",ex); 
				}
			}
			if(stat!=null) {
				try {
					stat.close();
				}catch(SQLException ex) {
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
		
	}

	@Override
	public void eliminarEntidad(Estacion e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(DELETE);
			stat.setInt(1, e.getId());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya eliminado");
			}
		} catch (SQLException ex){
			throw new DAOException("Error en SQL", ex);
		} finally {
			if(stat!=null) {
				try {
					stat.close();
				}catch(SQLException ex) {
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
	}

	@Override
	public void modificarEntidad(Estacion e) throws DAOException {
		// TODO Auto-generated method stub
		//"UPDATE tp_died.estacion SET nombre = ?, horarioApertura = ?, horarioCierre = ?, estado = ? WHERE id_estacion = ?";
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, e.getNombre());
			stat.setTime(2, Time.valueOf(e.getHorarioApertura()));
			stat.setTime(3, Time.valueOf(e.getHorarioCierre()));
			stat.setString(4, e.getEstado().toString());
			stat.setInt(5, e.getId());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya modificado");
			}
		} catch (SQLException ex){
			throw new DAOException("Error en SQL", ex);
		} finally {
			if(stat!=null) {
				try {
					stat.close();
				}catch(SQLException ex) {
					throw new DAOException("Error en SQL", ex);
				}
			}
		}
	}
	
	private Estacion convertir(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("id_estacion");
		String nombre = rs.getString("nombre");
		LocalTime horarioApertura = rs.getTime("horarioApertura").toLocalTime();
		LocalTime horarioCierre = rs.getTime("horarioCierre").toLocalTime();
		EstadoEstacion estado = EstadoEstacion.valueOf(rs.getString("estado"));
		Estacion estacion = new Estacion(id,nombre,horarioApertura,horarioCierre,estado);
		return estacion;
	}

	@Override
	public List<Estacion> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Estacion> estaciones = new ArrayList<Estacion>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while(rs.next()) {
				estaciones.add(convertir(rs));
			}
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL",ex);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL",ex); 
				}
			}
			if(stat!=null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL",ex); 
				}
			}
		}
		return estaciones;
	}

	@Override
	public Estacion obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		Estacion e = null;
		try {
			stat = conn.prepareStatement(GETONE);
			stat.setInt(1, id);
			rs = stat.executeQuery();
			if(rs.next()) {
				e = convertir(rs); 
			}else {
				throw new DAOException("No se ha encontrado ese registro");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL",ex);
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL",ex); 
				}
			}
			if(stat!=null) {
				try {
					stat.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL",ex); 
				}
			}
		}
		return e;
	}

//	public static void main(String[] args) throws SQLException, DAOException {
//		Connection conn = null;
//		try {
//			EstacionDAO estacionDao = new EstacionDAOImpl();
//			//Estacion nuevaEstacion = new Estacion("X", LocalTime.now(),LocalTime.now(),EstadoEstacion.EN_MANTENIMIENTO);
//			//estacionDao.crearEntidad(nuevaEstacion);
//			//System.out.println("La estacion se a generado con ID: " + nuevaEstacion.getId());
//			List<Estacion> estaciones = estacionDao.obtenerTodasLasEntidades();
//			for (Estacion e : estaciones) {
//				System.out.println(e.toString());
//			}	
//			Estacion nuevaEstacion = new Estacion(5,"Z",LocalTime.now(),LocalTime.now(),EstadoEstacion.EN_MANTENIMIENTO);
//			estacionDao.modificarEntidad(nuevaEstacion);
//			estaciones = estacionDao.obtenerTodasLasEntidades();
//			for (Estacion e : estaciones) {
//				System.out.println(e.toString());
//			}
//			
//		} finally {
//			if (conn!=null) {
//				conn.close();
//			}
//		}
//	}
}
