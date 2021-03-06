package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.DAOManager;
import dao.TrayectoDAO;
import entidades.Estacion;
import entidades.EstadoTrayecto;
import entidades.LineaDeTransporte;
import entidades.Trayecto;
import excepciones.DAOException;

public class TrayectoDAOImpl implements TrayectoDAO{
	//tp_died.trayecto(id_trayecto, id_linea, id_estacion_origen, id_estacion_destino, distancia, duracion, capacidad, estado, costo)
	final String INSERT = "INSERT INTO tp_died.trayecto VALUES (DEFAULT,?,?,?,?,?,?,?::tp_died.estadoTrayecto,?)";
	final String UPDATE = "UPDATE tp_died.trayecto SET id_linea = ?, id_estacion_origen = ?, id_estacion_destino = ?,  distancia = ?, duracion = ?, capacidad = ?, estado = ?::tp_died.estadoTrayecto, costo = ? WHERE id_trayecto = ?";
	final String DELETE = "DELETE FROM tp_died.trayecto WHERE id_trayecto = ?"; 
	final String GETONE = "SELECT * FROM tp_died.trayecto WHERE id_trayecto = ?";
	final String GETALL = "SELECT * FROM tp_died.trayecto ORDER BY 1";	
	
	private Connection conn;
	
	public TrayectoDAOImpl(Connection conn) throws SQLException{
		this.conn = conn;
	}

	public void crearEntidad(Trayecto e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			stat.setInt(1, e.getLinea().getId());
			stat.setInt(2, e.getOrigen().getId());
			stat.setInt(3, e.getDestino().getId());
			stat.setDouble(4,e.getDistancia());
			stat.setInt(5, e.getDuracion());
			stat.setInt(6, e.getCantidadMaximaPasajeros());
			stat.setString(7, e.getEstado().toString());
			stat.setDouble(8, e.getCosto());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
			rs = stat.getGeneratedKeys();
			if(rs.next()) {
				e.setId(rs.getInt(1));
			}else {
				throw new DAOException("No puedo asignar ID a este trayecto");
			}
		} catch (SQLException ex) {
			throw new DAOException("Error en SQL",ex);
		} finally {
			if(rs!=null) {try {rs.close();	} catch (SQLException ex) {	throw new DAOException("Error en SQL",ex);}}
			if(stat!=null) {try {stat.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex);	}}
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
	}

	@Override
	public void eliminarEntidad(Trayecto e) throws DAOException {
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
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
	}

	@Override
	public void modificarEntidad(Trayecto e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setInt(1, e.getLinea().getId());
			stat.setInt(2, e.getOrigen().getId());
			stat.setInt(3, e.getDestino().getId());
			stat.setDouble(4,e.getDistancia());
			stat.setInt(5, e.getDuracion());
			stat.setInt(6, e.getCantidadMaximaPasajeros());
			stat.setString(7, e.getEstado().toString());
			stat.setDouble(8, e.getCosto());
			stat.setInt(9, e.getId());
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
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
	}

	private Trayecto convertir (ResultSet rs) throws SQLException, DAOException {
		
		Integer id_trayecto = rs.getInt(1);
		Integer id_linea = rs.getInt(2);
		Integer id_origen = rs.getInt(3);
		Integer id_destino = rs.getInt(4);
		Double distancia = rs.getDouble(5);
		Integer duracion = rs.getInt(6);
		Integer capacidad = rs.getInt(7);
		EstadoTrayecto estado = EstadoTrayecto.valueOf(rs.getString(8));
		Double costo = rs.getDouble(9);
		
		//Creo las estaciones
		DAOManager manager = DAOManagerImpl.getInstance();
		LineaDeTransporte linea = manager.getLineaDeTransporteDAO().obtenerEntidad(id_linea);
		Estacion origen = manager.getEstacionDAO().obtenerEntidad(id_origen);
		Estacion destino = manager.getEstacionDAO().obtenerEntidad(id_destino);
		
		Trayecto trayecto = new Trayecto(id_trayecto, linea, origen, destino, distancia, duracion, capacidad, estado, costo);
		return trayecto;
	}
	
	@Override
	public List<Trayecto> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Trayecto> trayecto = new ArrayList<Trayecto>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while(rs.next()) {
				trayecto.add(convertir(rs));
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
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
		return trayecto;
	}

	@Override
	public Trayecto obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		Trayecto e = null;
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
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
		return e;
	}

}
