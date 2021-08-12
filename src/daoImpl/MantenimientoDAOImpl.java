package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import dao.MantenimientoDAO;
import entidades.Estacion;
import entidades.EstadoEstacion;
import entidades.Mantenimiento;
import excepciones.DAOException;

public class MantenimientoDAOImpl implements MantenimientoDAO{
	//tp_died.mantenimiento (id_mantenimiento, id_estacion, fechaInicio, fechaFin, observaciones)
	final String INSERT = "INSERT INTO tp_died.mantenimiento VALUES (DEFAULT,?,?,?,?)";
	//final String UPDATE = "UPDATE tp_died.mantenimiento SET fechaInicio = ?, fechaFin = ?, observaciones = ? WHERE id_mantenimiento = ?";
	final String UPDATE = "UPDATE tp_died.mantenimiento SET fechaFin = ? WHERE id_estacion = ? AND fechaFin IS NULL";
	final String UPDEST2 = "UPDATE tp_died.estacion SET estado = 'OPERATIVA' WHERE id_estacion = ?";
	final String DELETE = "DELETE FROM tp_died.mantenimiento WHERE id_mantenimiento = ?"; 
	final String GETONE = "SELECT * FROM tp_died.mantenimiento WHERE id_mantenimiento = ?";
	final String GETALL = "SELECT * FROM tp_died.mantenimiento ORDER BY 1";
	final String ULTMANT =	"SELECT es.id_estacion, es.nombre, MAX(ma.fechaInicio) "
						+"FROM tp_died.estacion es LEFT JOIN tp_died.mantenimiento ma ON es.id_estacion = ma.id_estacion "
						+"GROUP BY es.id_estacion, es.nombre ORDER BY 1;";
	final String UPDEST = "UPDATE tp_died.estacion SET estado = 'EN_MANTENIMIENTO' WHERE id_estacion = ?";
	
	private Connection conn;
	
	public MantenimientoDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@SuppressWarnings("static-access")
	@Override
	public void crearEntidad(Mantenimiento e) throws DAOException {
		PreparedStatement stat = null;
		PreparedStatement stat2 = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(INSERT, stat.RETURN_GENERATED_KEYS);
			stat.setInt(1, e.getIdEstacion());
			if(e.getFechaInicio()==null) {
				stat.setObject(2, null);
			}else stat.setDate(2, Date.valueOf(e.getFechaInicio()));
			if(e.getFechaFin()==null) {
				stat.setObject(3, null);
			}else stat.setDate(3, Date.valueOf(e.getFechaFin()));
			stat.setString(4, e.getObservaciones());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
			rs = stat.getGeneratedKeys();
			if(rs.next()) {
				e.setId(rs.getInt(1));
			}else {
				throw new DAOException("No puedo asignar ID a este mantenimiento");
			}
			stat2 = conn.prepareStatement(UPDEST);
			stat2.setInt(1, e.getIdEstacion());
			stat2.executeUpdate();
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
					stat2.close();
				} catch (SQLException ex) {
					throw new DAOException("Error en SQL",ex);
				}
			}
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
		
	}

	@Override
	public void eliminarEntidad(Mantenimiento e) throws DAOException {
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
	public void modificarEntidad(Mantenimiento e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		PreparedStatement stat2 = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setDate(1,Date.valueOf(LocalDate.now()));
			stat.setInt(2, e.getIdEstacion());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya modificado");
			}
			stat2 = conn.prepareStatement(UPDEST2);
			stat2.setInt(1, e.getIdEstacion());
			stat2.executeUpdate();
		} catch (SQLException ex){
			throw new DAOException("Error en SQL", ex);
		} finally {
			if(stat!=null) {
				try {
					stat.close();
					stat2.close();
				}catch(SQLException ex) {
					throw new DAOException("Error en SQL", ex);
				}
			}
			//if(conn!=null) {try {conn.close();} catch (SQLException ex) {throw new DAOException("Error en SQL",ex); }}
		}
	}

	private Mantenimiento convertir (ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		Integer id_estacion = rs.getInt(2);
		LocalDate fechaInicio = rs.getDate(3).toLocalDate();
		LocalDate fechaFin;
		if(rs.getObject(4) != null){
			fechaFin = rs.getDate(4).toLocalDate();
		}else {
			fechaFin = null;
		}
		String obs = rs.getString(5);
		Mantenimiento mant = new Mantenimiento(id, id_estacion, fechaInicio, fechaFin, obs);
		return mant;
	}
	
	@Override
	public List<Mantenimiento> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Mantenimiento> mant = new ArrayList<Mantenimiento>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while(rs.next()) {
				mant.add(convertir(rs));
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
		return mant;
	}

	@Override
	public Mantenimiento obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		Mantenimiento e = null;
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
	
	public Map<Estacion,LocalDate> recuperarUltimosMantenimientos(){
		Map<Estacion,LocalDate> salida = new LinkedHashMap<Estacion,LocalDate>();
		
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			sentencia = conn.prepareStatement(ULTMANT);
			resultado = sentencia.executeQuery();
			
			while(resultado.next()) {
				Integer id = resultado.getInt(1);
				String nombre = resultado.getString(2);
				LocalDate fecha;
				if(resultado.getObject(3) == null) fecha = LocalDate.MIN;
				else  fecha = resultado.getDate(3).toLocalDate();
				LocalTime a = null, c = null;
				salida.put(new Estacion(id,nombre,a,c,EstadoEstacion.OPERATIVA), fecha);
			}
			
			System.out.println("Consulta realizada: " +sentencia.toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(resultado!=null) try { resultado.close();} catch(SQLException e) {e.printStackTrace();}
			if(sentencia!=null) try { sentencia.close();} catch(SQLException e) {e.printStackTrace();}
			//if(conn!=null) try { conn.close();} catch(SQLException e) {e.printStackTrace();}
		}
		
		return salida;
	}

}
