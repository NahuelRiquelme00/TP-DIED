package interfaces.boletos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JPanel;
import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.EstadoEstacion;
import entidades.Trayecto;
import excepciones.DAOException;
import grafo.Edge;
import grafo.Graph;
import interfaces.VentanaPrincipal;
import interfaces.grafico.FrameMostrarCamino;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class PanelVerCombinaciones extends JPanel {

	private static final long serialVersionUID = -3245439443820352709L;
	private DAOManager manager;
	private JPanel panelGrafico;
	private JTable table;
	@SuppressWarnings({ "rawtypes" })
	private JComboBox lOrigen;
	@SuppressWarnings({ "rawtypes" })
	private JComboBox lDestino;
	private CaminosTableModel model;
	private List<Estacion> estaciones;
	private List<Trayecto> rutas;
	private List<List<Trayecto>> listaCaminos;
	private Map<List<Trayecto>,Double> distancias;
	private Map<List<Trayecto>,Integer> duraciones;
	private Map<List<Trayecto>,Double> costos;
	private List<Trayecto> caminoRapido;
	private List<Trayecto> caminoCorto;
	private List<Trayecto> caminoBarato;
	private JButton btnBuscar;
	private JButton btnVolver;
	private JButton masBarato;
	private JButton masRapido;
	private JButton menorDistancia;
	private List<Trayecto> seleccionado;
	private Integer rowMasRapido;
	private Integer rowMasBarato;
	private Integer rowMenorDistancia;	
	private JButton btnRecorrido;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PanelVerCombinaciones(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		try {
			estaciones = manager.getEstacionDAO().obtenerTodasLasEntidades().stream().filter(est -> est.operativa()).collect(Collectors.toList());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		try {
			rutas = manager.getTrayectoDAO().obtenerTodasLasEntidades().stream().filter(r -> r.getLinea().activa() && r.activa() && r.getOrigen().operativa() && r.getDestino().operativa()).collect(Collectors.toList());
		} catch (DAOException e1) {
			e1.printStackTrace();
		}
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Recorridos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JPanel panelSeleccionar = new JPanel();
		panelSeleccionar.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Seleccionar origen y destino", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel = new JLabel("Estacion origen");
		
		//Estacion y destino
		lOrigen = new JComboBox(estaciones.toArray());
		lOrigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Estacion> aux = new ArrayList<Estacion>();				
				Estacion origSeleccionada = (Estacion) lOrigen.getSelectedItem();
				Estacion destSeleccionada = (Estacion)lDestino.getSelectedItem();
				LocalTime a = null, c = null;
				aux.add(new Estacion(-1,"",a,c,EstadoEstacion.OPERATIVA));
				aux.addAll(estaciones);
				lDestino.removeAllItems();
				aux.remove(origSeleccionada);
				
				for(Estacion e1 : aux) {
					lDestino.addItem(e1);
				}
		
				lDestino.setSelectedItem(destSeleccionada);
			}
		});
		lDestino = new JComboBox(estaciones.toArray());		
		lDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Estacion> aux = new ArrayList<Estacion>();
				Estacion origSeleccionada = (Estacion) lOrigen.getSelectedItem();
				Estacion destSeleccionada = (Estacion)lDestino.getSelectedItem();
				LocalTime a = null, c = null;
				aux.add(new Estacion(-1,"",a,c,EstadoEstacion.OPERATIVA));
				aux.addAll(estaciones);
				lOrigen.removeAllItems();
				aux.remove(destSeleccionada);
				
				for(Estacion e1 : aux) {
					lOrigen.addItem(e1);
				}
			
				lOrigen.setSelectedItem(origSeleccionada);
			}
		});
		
		JLabel lblEstacionDestino = new JLabel("Estacion destino");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCaminos();
				actualizarPaneles();
				
				//lista de caminos
				DecimalFormat df = new DecimalFormat("###.00");
				
				List<List<Object>> data = new ArrayList<List<Object>>();
				for(List<Trayecto> c : listaCaminos) {
					Double distancia = c.stream().map(r -> r.getDistancia()).reduce(0.0, (d1,d2) -> d1+d2);
					Integer duracion = c.stream().map(r -> r.getDuracion()).reduce(0, (d1,d2) -> d1+d2);
					Double costo = c.stream().map(r -> r.getCosto()).reduce(0.0, (c1,c2) -> c1+c2);
					
					
					
					distancias.put(c, distancia);
					duraciones.put(c, duracion);
					costos.put(c, costo);
					
					List<Object> fila = new ArrayList<Object>();
					
					fila.add(c);
					fila.add(df.format(distancias.get(c)));
					fila.add(duraciones.get(c));
					fila.add(df.format(costos.get(c)));
					data.add(fila);
				}
				model.setData(data);
				model.fireTableDataChanged();
						
			}
		});
		GroupLayout gl_panelSeleccionar = new GroupLayout(panelSeleccionar);
		gl_panelSeleccionar.setHorizontalGroup(
			gl_panelSeleccionar.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelSeleccionar.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelSeleccionar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSeleccionar.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(18)
							.addComponent(lOrigen, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSeleccionar.createSequentialGroup()
							.addComponent(lblEstacionDestino)
							.addGap(12)
							.addComponent(lDestino, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSeleccionar.createSequentialGroup()
							.addGap(84)
							.addComponent(btnBuscar)))
					.addContainerGap())
		);
		gl_panelSeleccionar.setVerticalGroup(
			gl_panelSeleccionar.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelSeleccionar.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addGroup(gl_panelSeleccionar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSeleccionar.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel))
						.addComponent(lOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panelSeleccionar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSeleccionar.createSequentialGroup()
							.addGap(4)
							.addComponent(lblEstacionDestino))
						.addComponent(lDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addComponent(btnBuscar)
					.addGap(7))
		);
		panelSeleccionar.setLayout(gl_panelSeleccionar);
		
		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_BOLETOS);
			}
		});
		
		JPanel panelCriterios = new JPanel();
		panelCriterios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Criterios de busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		masRapido = new JButton("Mas rapido");
		masRapido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setRowSelectionInterval(rowMasRapido, rowMasRapido);
			}
		});
		masRapido.setEnabled(false);
		
		menorDistancia = new JButton("Mas corto");
		menorDistancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setRowSelectionInterval(rowMenorDistancia, rowMenorDistancia);
			}
		});
		menorDistancia.setEnabled(false);
		
		masBarato = new JButton("Mas barato");
		masBarato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setRowSelectionInterval(rowMasBarato, rowMasBarato);
			}
		});
		masBarato.setEnabled(false);
		GroupLayout gl_panelCriterios = new GroupLayout(panelCriterios);
		gl_panelCriterios.setHorizontalGroup(
			gl_panelCriterios.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCriterios.createSequentialGroup()
					.addContainerGap(60, Short.MAX_VALUE)
					.addGroup(gl_panelCriterios.createParallelGroup(Alignment.LEADING)
						.addComponent(masBarato, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(masRapido, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(menorDistancia, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
					.addGap(48))
		);
		gl_panelCriterios.setVerticalGroup(
			gl_panelCriterios.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCriterios.createSequentialGroup()
					.addContainerGap(51, Short.MAX_VALUE)
					.addComponent(masRapido, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(menorDistancia, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(masBarato, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		panelCriterios.setLayout(gl_panelCriterios);
		
		JButton btnComprar = new JButton("Comprar");
		btnComprar.setEnabled(false);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_COMPRAR_BOLETO);
			}
		});
		
		panelGrafico = new JPanel();
		panelGrafico.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)), "Resultados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		GroupLayout gl_panelGrafico = new GroupLayout(panelGrafico);
		gl_panelGrafico.setHorizontalGroup(
			gl_panelGrafico.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGrafico.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelGrafico.setVerticalGroup(
			gl_panelGrafico.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGrafico.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		//Codigo tabla
		model = new CaminosTableModel();
		
		//Opcion 1
//		table = new JTable(model);
//		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		//table.getColumnModel().getColumn(0).setPreferredWidth(800);
//		//table.getColumnModel().getColumn(1).setPreferredWidth(10);
//		//table.getColumnModel().getColumn(2).setPreferredWidth(10);
//		//table.getColumnModel().getColumn(3).setPreferredWidth(5);
//		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		scrollPane.setViewportView(table);
		
		
		//Opcion 2
		 table = new JTable(){
			private static final long serialVersionUID = -3117727877073761017L;
			@Override
			    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			      Component component = super.prepareRenderer(renderer, row, column);
			      int rendererWidth = component.getPreferredSize().width;
			      TableColumn tableColumn = getColumnModel().getColumn(column);
			      tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
			      return component;
			    }
			  };
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setModel(model);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
		
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {		
			public void valueChanged(ListSelectionEvent e) {
				Integer filaSeleccionada = table.getSelectedRow();
				
				if(filaSeleccionada != -1) {
					seleccionado = (List<Trayecto>) model.getValueAt(filaSeleccionada, 0);
					btnComprar.setEnabled(true);
					btnRecorrido.setEnabled(true);
				}
				
			}
		});
		panelGrafico.setLayout(gl_panelGrafico);
		
		btnRecorrido = new JButton("Ver recorrido");
		btnRecorrido.setEnabled(false);
		btnRecorrido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameMostrarCamino fmc = new FrameMostrarCamino(estaciones, seleccionado);
				fmc.setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelSeleccionar, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelCriterios, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addComponent(panelGrafico, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(480, Short.MAX_VALUE)
					.addComponent(btnRecorrido)
					.addGap(18)
					.addComponent(btnComprar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addGap(584))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelSeleccionar, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(panelCriterios, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelGrafico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnComprar)
							.addComponent(btnRecorrido))
						.addComponent(btnVolver))
					.addGap(6))
		);
		setLayout(groupLayout);
		
		distancias = new LinkedHashMap<List<Trayecto>,Double>();
		duraciones = new LinkedHashMap<List<Trayecto>,Integer>();
		costos = new LinkedHashMap<List<Trayecto>,Double>();
		listaCaminos = new ArrayList<List<Trayecto>>();
		caminoRapido = new ArrayList<Trayecto>();
		caminoCorto = new ArrayList<Trayecto>();
		caminoBarato = new ArrayList<Trayecto>();      
	}
	
	private void buscarCaminos() {
		Graph<Integer> grafo = new Graph<Integer>();		
		
		estaciones.stream().forEach(e -> grafo.addNodo(e.getId()));
		for(Trayecto r : rutas) {
			grafo.conectar(r.getOrigen().getId(), r.getDestino().getId(), r.getId(), 0.0);
		}
	
		Estacion origen = (Estacion) lOrigen.getSelectedItem();
		Estacion destino = (Estacion) lDestino.getSelectedItem();
		
		if(origen.getId() == -1 || destino.getId() == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un origen y un destino ","Estaciones no seleccionadas", JOptionPane.INFORMATION_MESSAGE);
			resetearCaminos();
		}
		else {
			List<List<Edge<Integer>>> todosLosCaminos = grafo.caminosRutas(origen.getId(), destino.getId());
			List<List<Trayecto>> todosLosCaminosRuta = new ArrayList<List<Trayecto>>();
		
			for(List<Edge<Integer>> camino : todosLosCaminos) {
				List<Trayecto> caminoRuta;
				caminoRuta = camino.stream().map(edge -> {
					try {
						return manager.getTrayectoDAO().obtenerEntidad(edge.getId());
					} catch (DAOException e1) {
						e1.printStackTrace();
					}
					return null;
				}).collect(Collectors.toList());
				todosLosCaminosRuta.add(caminoRuta);
			}
			
			listaCaminos = todosLosCaminosRuta.stream().filter(c -> !repiteEstacion(c)).collect(Collectors.toList());
		}
	}
	
	private void resetearCaminos() {
		caminoRapido = new ArrayList<Trayecto>();;
		caminoCorto = new ArrayList<Trayecto>();;
		caminoBarato = new ArrayList<Trayecto>();
		listaCaminos = new ArrayList<List<Trayecto>>();
		
		masRapido.setEnabled(false);
		menorDistancia.setEnabled(false);
		masBarato.setEnabled(false);
	}
	
	private Boolean repiteEstacion(List<Trayecto> camino) {
		List<Estacion> recorridas = new ArrayList<Estacion>();
		
		for(Trayecto r : camino) {
			if(recorridas.contains(r.getOrigen())) return true;
			else recorridas.add(r.getOrigen());
		}
		
		return false;
	}
	
	private void actualizarPaneles() {
		@SuppressWarnings("unused")
		DecimalFormat df = new DecimalFormat("###.00");
		
		if(listaCaminos.size() > 0) {
			for(List<Trayecto> camino : listaCaminos) {
				Double distancia = camino.stream().map(r -> r.getDistancia()).reduce(0.0, (d1,d2) -> d1+d2);
				Integer duracion = camino.stream().map(r -> r.getDuracion()).reduce(0, (d1,d2) -> d1+d2);
				Double costo = camino.stream().map(r -> r.getCosto()).reduce(0.0, (c1,c2) -> c1+c2);
				
				if(distancias.containsKey(camino)) {
					distancias.replace(camino, distancia);
				}
				else {
					distancias.put(camino, distancia);
				}
				
				if(duraciones.containsKey(camino)) {
					duraciones.replace(camino, duracion);
				}
				else {
					duraciones.put(camino, duracion);
				}
				
				if(costos.containsKey(camino)) {
					costos.replace(camino, costo);
				}
				else {
					costos.put(camino, costo);
				}
			}
			
			caminoRapido = listaCaminos.stream().min((c1,c2) -> duraciones.get(c1).compareTo(duraciones.get(c2))).get();
			caminoCorto = listaCaminos.stream().min((c1,c2) -> distancias.get(c1).compareTo(distancias.get(c2))).get();
			caminoBarato = listaCaminos.stream().min((c1,c2) -> costos.get(c1).compareTo(costos.get(c2))).get();
			
			rowMasRapido = listaCaminos.lastIndexOf(caminoRapido);
			rowMasBarato = listaCaminos.lastIndexOf(caminoBarato);
			rowMenorDistancia = listaCaminos.lastIndexOf(caminoCorto);
			
			
			masRapido.setEnabled(true);
			menorDistancia.setEnabled(true);
			masBarato.setEnabled(true);
		}
		else {
			resetearCaminos();
			
			if(((Estacion) lOrigen.getSelectedItem()).getId() != -1 && ((Estacion) lDestino.getSelectedItem()).getId() != -1) {
				//Si no hay caminos entre las estacione seleccionadas
				JOptionPane.showMessageDialog(null, "No existe ningun camino entre las estaciones seleccionadas","Camino inexistente", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
			}
		}
	}	

	public List<Trayecto> getSeleccionado() {
		return seleccionado;
	}
	
	public List<Estacion> getEstaciones(){
		return estaciones;
	}
	
	public List<Trayecto> getRutas(){
		return rutas;
	}
}
