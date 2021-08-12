package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import dao.BoletoDAO;
import entidades.Boleto;
import excepciones.DAOException;

public class BoletoDAOimpl implements BoletoDAO{
	//tp_died.boleto(id_boleto, correo_cliente, nombre_cliente, fecha_venta, origen, destino, camino, costo)
	final String INSERT = "INSERT INTO tp_died.boleto VALUES (DEFAULT,?,?,?,?,?,?,?)";
	final String UPDATE = "UPDATE tp_died.boleto SET correo_cliente = ?, nombre_cliente = ?, fecha_venta = ?,  origen = ?, destino = ?, camino = ?, costo = ? WHERE id_boleto = ?";
	final String DELETE = "DELETE FROM tp_died.boleto WHERE id_boleto = ?"; 
	final String GETONE = "SELECT * FROM tp_died.boleto WHERE id_boleto = ?";
	final String GETALL = "SELECT * FROM tp_died.boleto";
	
	private Connection conn;

	public BoletoDAOimpl(Connection conn) {
		this.conn = conn;
	}

	@SuppressWarnings("static-access")
	@Override
	public void crearEntidad(Boleto e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(INSERT, stat.RETURN_GENERATED_KEYS);
			stat.setString(1, e.getCorreoCliente());
			stat.setString(2, e.getNombreCliente());
			stat.setDate(3, Date.valueOf(e.getFechaVenta()));
			stat.setString(4,e.getOrigen());
			stat.setString(5, e.getDestino());
			stat.setString(6, e.getCamino());
			stat.setDouble(7, e.getCosto());
			if(stat.executeUpdate()==0) {
				throw new DAOException("Puede que no se haya guardado");
			}
			rs = stat.getGeneratedKeys();
			if(rs.next()) {
				e.setId(rs.getInt(1));
			}else {
				throw new DAOException("No puedo asignar ID a este Boleto");
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
	public void eliminarEntidad(Boleto e) throws DAOException {
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
	public void modificarEntidad(Boleto e) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(UPDATE);
			stat.setString(1, e.getCorreoCliente());
			stat.setString(2, e.getNombreCliente());
			stat.setDate(3, Date.valueOf(e.getFechaVenta()));
			stat.setString(4,e.getOrigen());
			stat.setString(5, e.getDestino());
			stat.setString(6, e.getCamino());
			stat.setDouble(7, e.getCosto());
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

	private Boleto convertir (ResultSet rs) throws SQLException {		
		Integer id = rs.getInt(1);
		String correo_cliente = rs.getString(2);
		String nombre_cliente = rs.getString(3);
		LocalDate fecha_venta = rs.getDate(4).toLocalDate();
		String origen = rs.getString(5);
		String destino = rs.getString(6);
		String camino = rs.getString(7);
		Double costo = rs.getDouble(8);
		
		Boleto boleto = new Boleto(id, correo_cliente, nombre_cliente, fecha_venta, origen, destino, camino, costo);
		return boleto;
	}
	
	@Override
	public List<Boleto> obtenerTodasLasEntidades() throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<Boleto> boleto = new ArrayList<Boleto>();
		try {
			stat = conn.prepareStatement(GETALL);
			rs = stat.executeQuery();
			while(rs.next()) {
				boleto.add(convertir(rs));
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
		return boleto;
	}

	@Override
	public Boleto obtenerEntidad(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		PreparedStatement stat = null;
		ResultSet rs = null;
		Boleto e = null;
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
