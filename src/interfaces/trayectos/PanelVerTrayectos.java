package interfaces.trayectos;

import javax.swing.JPanel;

import interfaces.VentanaPrincipal;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.LineaDeTransporte;
import entidades.Trayecto;
import excepciones.DAOException;

import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ItemEvent;

public class PanelVerTrayectos extends JPanel {

	private static final long serialVersionUID = -3608850816420439740L;
	private JTable table;
	private DAOManager manager;
	private TrayectoTableModel model;
	private List<LineaDeTransporte> lineas;
	private List<Trayecto> todos;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxLineas;
	private LineaDeTransporte linea;
	private Estacion estacionOrigen;
	private Trayecto tramo;
	private List<Trayecto> trayecto;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PanelVerTrayectos(final VentanaPrincipal frame) {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de trayectos", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		JPanel panelTramos = new JPanel();
		panelTramos.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Tramos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		
		//Codigo de la tabla
		manager = DAOManagerImpl.getInstance();
		this.model = new TrayectoTableModel(manager.getTrayectoDAO());
		this.model.updateModel();
		todos = this.model.getDatos();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		//this.table.setModel(model);
		
		//Obtener lineas
		try {
			lineas = manager.getLineaDeTransporteDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		GroupLayout gl_panelTramos = new GroupLayout(panelTramos);
		gl_panelTramos.setHorizontalGroup(
			gl_panelTramos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTramos.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelTramos.setVerticalGroup(
			gl_panelTramos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTramos.createSequentialGroup()
					.addGap(9)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
					.addContainerGap())
		);
		panelTramos.setLayout(gl_panelTramos);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_PRINCIPAL);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Seleccione una linea de  transporte");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JButton btnEliminar = new JButton("Eliminar Tramo");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el tramo?", "Eliminar tramo",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion == 0) {
					try {
						manager.getTrayectoDAO().eliminarEntidad(tramo);
						System.out.println("Tramo eliminado");
						btnEliminar.setEnabled(false);
						actualizarTabla();
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error al eliminar tramo");
						e1.printStackTrace();
					}
				}
			}
		});
		btnEliminar.setEnabled(false);
		
		JButton btnModificar = new JButton("Modificar tramo");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MODIFICAR_TRAYECTO);
			}
		});
		btnModificar.setEnabled(false);
		
		JButton btnAgregar = new JButton("Agregar Tramo");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(estacionOrigen==null) {
					frame.cambiarPanel(VentanaPrincipal.PANE_AGREGAR_TRAYECTO2);
				}else {
					frame.cambiarPanel(VentanaPrincipal.PANE_AGREGAR_TRAYECTO);
				}
			}
		});
		btnAgregar.setEnabled(false);
		
		comboBoxLineas = new JComboBox(lineas.toArray());
		comboBoxLineas.setSelectedIndex(-1);		
		comboBoxLineas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//actualizarTabla();
				estacionOrigen = null;
				linea = (LineaDeTransporte)e.getItem();
				table.setModel(model);
				//List<Trayecto> trayecto
				trayecto = todos;
				trayecto = trayecto.stream()
						.filter(tra -> tra.getLinea().equals(linea))
						.collect(Collectors.toList());
				model.setDatos(trayecto);
				model.fireTableDataChanged();
				System.out.println("Actualizando");
				btnAgregar.setEnabled(true);
				//btnModificar.setEnabled(true);
				//btnEliminar.setEnabled(true);
				if(trayecto.isEmpty()) {
					
				}else {
					estacionOrigen = trayecto.get(trayecto.size()-1).getDestino();
				}
			}
		});
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionadas = table.getSelectedRowCount();
				if(filaSeleccionadas ==1) {
					btnModificar.setEnabled(true);
					btnEliminar.setEnabled(true);
					int row_selected = table.getSelectedRow();
					Integer id_trayecto = (Integer) table.getValueAt(row_selected, 0);
					try {
						tramo = manager.getTrayectoDAO().obtenerEntidad(id_trayecto);
						System.out.println(tramo.toString());
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});			

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addComponent(lblNewLabel)
					.addGap(38)
					.addComponent(comboBoxLineas, GroupLayout.PREFERRED_SIZE, 228, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(btnAgregar)
						.addGap(18)
						.addComponent(btnModificar)
						.addGap(18)
						.addComponent(btnEliminar)
						.addGap(18)
						.addComponent(btnVolver))
					.addComponent(panelTramos, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblNewLabel))
						.addComponent(comboBoxLineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(panelTramos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVolver)
						.addComponent(btnEliminar)
						.addComponent(btnModificar)
						.addComponent(btnAgregar))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	public void actualizarTabla() {
		table.clearSelection();
		model.updateModel();
		model.fireTableDataChanged();		
	}
	
	public Trayecto getTramo() {
		return tramo;
	}
	
	public Estacion getOrigen() {
		//estacionOrigen = trayecto.get(trayecto.size()).getDestino();
		return estacionOrigen;
	}
	
	public List<Trayecto> getTrayecto(){
		return trayecto;
	}
	
	public LineaDeTransporte getLinea() {
		return linea;
	}
	
	
}
