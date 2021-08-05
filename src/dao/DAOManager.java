package dao;

public interface DAOManager {
	
	EstacionDAO getEstacionDAO();
	
	MantenimientoDAO getMantenimientoDAO();
	
	LineaDeTransporteDAO getLineaDeTransporteDAO();
	
	TrayectoDAO getTrayectoDAO();

}
