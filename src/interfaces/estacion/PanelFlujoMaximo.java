package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.Trayecto;
import excepciones.DAOException;
import grafo.Edge;
import grafo.Graph;
import interfaces.VentanaPrincipal;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelFlujoMaximo extends JPanel {

	private static final long serialVersionUID = -1578268652531266975L;
	private DAOManager manager;
	private List<Estacion> estaciones;
	private List<Trayecto> trayectos;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEstaciones;
	private FlujoMaximoTableModel model;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelFlujoMaximo(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Calculo del flujo maximo de cada estacion", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		try {
			estaciones = manager.getEstacionDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			trayectos = manager.getTrayectoDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_INFORMACION_ESTACIONES);
			}
		});
		btnVolver.setPreferredSize(new Dimension(80, 25));
		
		JLabel lblNewLabel = new JLabel("Estacion Origen");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		comboBoxEstaciones = new JComboBox(estaciones.toArray());
		comboBoxEstaciones.setSelectedIndex(-1);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Destinos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		
		model = new FlujoMaximoTableModel();
		table = new JTable(model);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Double.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		scrollPane.setViewportView(table);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 752, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(4)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 439, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(158)
					.addComponent(lblNewLabel)
					.addGap(30)
					.addComponent(comboBoxEstaciones, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(btnCalcular))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(696)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(comboBoxEstaciones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCalcular))
					.addGap(16)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 473, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);

	}
	
	private void actualizarTabla() {
		Estacion origen = (Estacion) comboBoxEstaciones.getSelectedItem();
		
		List<List<Object>> data = new ArrayList<List<Object>>();
		
		List<Trayecto> subrutas = trayectos.stream().filter(r -> !r.getDestino().equals(origen)).collect(Collectors.toList());
		if(origen.getId() != -1) {
			for(Estacion e : estaciones) {
				if(!origen.equals(e)) {
					Integer flujo = calcularFlujo(origen, e, subrutas, estaciones);
					List<Object> fila = new ArrayList<Object>();
					fila.add(e.getId());
					fila.add(e.getNombre());
					fila.add(flujo);
					data.add(fila);
				}
			}
		}
		model.setData(data);
		model.fireTableDataChanged();
	}
	
	private Integer calcularFlujo(Estacion origen, Estacion destino, List<Trayecto> rutasAntes, List<Estacion> estaciones) {
		
		Integer rtdo = 0;
	
		//Voy sacando las rutas de la estacion destino para que sea sumidero
		List<Trayecto> rutas = rutasAntes.stream().filter(r -> !r.getOrigen().equals(destino)).collect(Collectors.toList());
		
		Map<Trayecto,Integer> capRestante = new LinkedHashMap<Trayecto,Integer>();
		for(Trayecto r : rutas) capRestante.put(r, r.getCantidadMaximaPasajeros());
		
		List<Trayecto> rutasCamino = buscarCamino(origen,destino,rutas,estaciones,capRestante);
		
		while(!rutasCamino.isEmpty()) {
			Integer minCapacidad = rutasCamino.stream().map(r -> capRestante.get(r)).min((c1,c2) -> c1.compareTo(c2)).get();
			
			for(Trayecto r : rutasCamino) capRestante.replace(r, capRestante.get(r)-minCapacidad);
			
			rtdo+=minCapacidad;
	
			for(Trayecto r : capRestante.keySet()) {
				if(capRestante.get(r) == 0) rutas.remove(r);
			}
	
			rutasCamino = buscarCamino(origen,destino,rutas,estaciones,capRestante);
		}
		
		return rtdo;
	}
	
	private List<Trayecto> buscarCamino(Estacion origen, Estacion destino, List<Trayecto> rutas, List<Estacion> estaciones, Map<Trayecto,Integer> capacidad){
		List<Trayecto> salida = new ArrayList<Trayecto>();
		
		Graph<Integer> grafo = new Graph<Integer>();
		
		estaciones.stream().forEach(e -> grafo.addNodo(e.getId()));
		for(Trayecto r : rutas) {
			Double cap = Double.parseDouble(String.valueOf(capacidad.get(r)));
			grafo.conectar(r.getOrigen().getId(), r.getDestino().getId(), r.getId(), cap);
		}
		
		
		//Se utiliza Dikstra repitiendolo hasta que ya no encuentre ningun camino
		List<Edge<Integer>> camino = grafo.caminoDijkstra(origen.getId(),destino.getId());
		
		camino.stream().forEach(x -> {
			try {
				salida.add(manager.getTrayectoDAO().obtenerEntidad(x.getId()));
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		});
		
		return salida;
	}
	
}
