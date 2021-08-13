package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.LineaDeTransporteDAO;
import entidades.ColorLinea;
import entidades.EstadoLineaDeTransporte;
import entidades.LineaDeTransporte;
import excepciones.DAOException;

public class LineaDeTransporteDAOImpl implements LineaDeTransporteDAO{
	//tp_died.linea_de_transporte(id_linea, nombre, color, estadoLinea)
	final String INSERT = "INSERT INTO tp_died.linea_de_transporte VALUES (DEFAULT,?,?::tp_died.colorLinea,?::tp_died.estadoLineaTransporte)";
	final String UPDATE = "UPDATE tp_died.linea_de_transporte SET nombre = ?, color = ?::tp_died.colorLinea, estado = ?::tp_died.estadoLineaTransporte WHERE id_linea = ?";
	final String DELETE = "DELETE FROM tp_died.linea_de_transporte WHERE id_linea = ?"; 
	final String GETONE = "SELECT * FROM tp_died.linea_de_transporte WHERE id_linea = ?";
	final String GETALL = "SELECT * FROM tp_died.linea_de_transporte ORDER BY 1";
	
	private Connection conn;
	
	public LineaDeTransporteDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
	}

	@SuppressWarnings("static-access")
	@Override
	public void crearEntidad(LineaDeTransporte e) throws DAOException {
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(INSERT, stat.RETURN_GENERATED_KEYS);
			stat.setString(1, e.getNombre());
			stat.setString(2, e.getColor().toString());
			stat.setString(3, e.getEstado().toString());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
			rs = stat.getGeneratedKeys();
			if(rs.next()) {
				e.setId(rs.getInt(1));
			}else {
				throw new DAOException("No puedo asignar ID a esta linea");
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
	}

	@Override
	public void eliminarEntidad(LineaDeTransporte e) throws DAOException {
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
	public void modificarEntidad(LineaDeTransporte e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, e.getNombre());
			stat.setString(2, e.getColor().toString());
			stat.setString(3, e.getEstado().toString());
			stat.setInt(4, e.getId());
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

	private LineaDeTransporte convertir (ResultSet rs) throws SQLException {
		Integer id = rs.getInt(1);
		String nombre = rs.getString(2);
		ColorLinea color = ColorLinea.valueOf(rs.getString(3));
		EstadoLineaDeTransporte estado = EstadoLineaDeTransporte.valueOf(rs.getString(4));
		LineaDeTransporte linea = new LineaDeTransporte(id, nombre, color, estado);
		return linea;
	}
	
	@Override
	public List<LineaDeTransporte> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<LineaDeTransporte> linea = new ArrayList<LineaDeTransporte>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while(rs.next()) {
				linea.add(convertir(rs));
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
		return linea;
	}

	@Override
	public LineaDeTransporte obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		LineaDeTransporte e = null;
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
