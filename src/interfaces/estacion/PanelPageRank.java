package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.Trayecto;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class PanelPageRank extends JPanel {

	private static final long serialVersionUID = -5107555255305961066L;
	private DAOManager manager;
	private JTable table;
	private PageRankTableModel model;
	List<Estacion> estaciones;
	List<Trayecto> rutas;
	private static final double d = 0.5; //para el factor de amortiguacion
	private static final double eCorte = 0.00000001; //para el valor de corte, finaliza al ser menor

	public PanelPageRank(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Page Rank", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_INFORMACION_ESTACIONES);
			}
		});
		btnVolver.setPreferredSize(new Dimension(80, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(15)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(696, Short.MAX_VALUE)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 507, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		model = new PageRankTableModel();
		table = new JTable(model);
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(Color.GREEN);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Double.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(250);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
		Map<Estacion,Double> listaPR = calcularPR();
		Set<Estacion> estaciones = listaPR.keySet();
		List<List<Object>> data = new ArrayList<List<Object>>();
		
		for(Estacion e :  estaciones) {
			List<Object> fila = new ArrayList<Object>();
			fila.add(e.getId());
			fila.add(e.getNombre());
			fila.add(listaPR.get(e));
			data.add(fila);
		}
		
		data.sort((e1,e2) -> ((Double) e2.get(2)).compareTo((Double) e1.get(2)));
		
		model.setData(data);
		model.fireTableDataChanged();
		table.setRowSelectionInterval(0,0);
	}
	
	private Map<Estacion,Double> calcularPR() {	
		
		try {
			estaciones = manager.getEstacionDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rutas = manager.getTrayectoDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<Estacion,List<Estacion>> adyacentesEntrantes = new LinkedHashMap<Estacion,List<Estacion>>();
		Map<Estacion,Double> pageRank = new LinkedHashMap<Estacion,Double>();
		Map<Estacion,Integer> gradoSalida = new LinkedHashMap<Estacion,Integer>();
		
		estaciones.stream().forEach(e -> {
			adyacentesEntrantes.put(e, this.getAdyacentesEntrantes(e));
			pageRank.put(e, 1.0);
			gradoSalida.put(e,this.gradoSalida(e));
		});
		
		Map<Estacion,Double> nuevoPR = prIterativo(pageRank,adyacentesEntrantes,gradoSalida);
		
		while(!cortarIteracion(nuevoPR,pageRank)) {
			
			for(Estacion e : estaciones) {
				pageRank.replace(e, nuevoPR.get(e));
			}
			
			 nuevoPR = prIterativo(pageRank,adyacentesEntrantes,gradoSalida);
		}
		
		for(Estacion e : estaciones) {
			pageRank.replace(e, nuevoPR.get(e));
		}
		
		return pageRank;
	}
	
	private Map<Estacion,Double> prIterativo(Map<Estacion,Double> prViejo, Map<Estacion,List<Estacion>> adyacentesEntrantes, Map<Estacion,Integer> gradoSalida){
		Map<Estacion,Double> nuevoPR = new LinkedHashMap<Estacion,Double>();
		Set<Estacion> estaciones = prViejo.keySet();
		
		estaciones.stream().forEach(e -> {
			nuevoPR.put(e, 1-d);
			
			adyacentesEntrantes.get(e).stream().forEach(eEntrante -> {
				Double sumaActual = nuevoPR.get(e);
				Double prEntrante = prViejo.get(eEntrante);
				Integer gradoSalidaEntrante = gradoSalida.get(eEntrante);
				
				nuevoPR.replace(e, sumaActual+d*prEntrante/gradoSalidaEntrante);
				
			});
			
		});
		
		return nuevoPR;
	}
	
	private Boolean cortarIteracion(Map<Estacion,Double> nuevo, Map<Estacion,Double> viejo) {
		Set<Estacion> estaciones = nuevo.keySet();
		
		for(Estacion e : estaciones) {
			if(Math.abs(nuevo.get(e)-viejo.get(e)) > eCorte) {
				return false;
			}
		}
		
		return true;
	}
	
	public List<Estacion> getAdyacentesEntrantes(Estacion destino){
		return rutas.stream().filter(r -> r.getDestino().equals(destino)).map(r -> r.getOrigen()).collect(Collectors.toList());
	}
	
	public Integer gradoSalida(Estacion e) {
		Integer grado = 0;
		
		for(Trayecto r : rutas) if(r.getOrigen().equals(e)) grado++;
		
		return grado;
	}
	


}
