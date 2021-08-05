package dao;

import java.util.List;

import excepciones.DAOException;

public interface DAO<T,K> {
	
	public void crearEntidad(T e) throws DAOException;
	
	public void eliminarEntidad(T e) throws DAOException;
	
	public void modificarEntidad(T e) throws DAOException;
	
	public List<T> obtenerTodasLasEntidades() throws DAOException;
	
	public T obtenerEntidad(K id) throws DAOException;

}
