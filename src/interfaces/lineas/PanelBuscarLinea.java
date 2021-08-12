package interfaces.lineas;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.ColorLinea;
import entidades.EstadoLineaDeTransporte;
import entidades.LineaDeTransporte;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;

import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;

public class PanelBuscarLinea extends JPanel {

	private static final long serialVersionUID = 141409156942786323L;
	private JTextField textFieldNombre;
	private DAOManager manager;
	private LineaTableModel model;
	private JTable table;
	private LineaDeTransporte actual;
	private int row_selected;
	JButton btnEditar;
	JButton btnBorrar;

	public PanelBuscarLinea(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de lineas de transporte", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_LINEAS);
			}
		});
		
		JPanel panelAtributos = new JPanel();
		panelAtributos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Atributos de busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 15));
		
		EstadoLineaDeTransporte[] estados = {null ,EstadoLineaDeTransporte.ACTIVA,EstadoLineaDeTransporte.NO_ACTIVA};
		JComboBox<?> comboBoxEstado = new JComboBox<Object>(estados);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Dialog", Font.BOLD, 15));
		
		ColorLinea[] colores = {null ,ColorLinea.AMARILLA,ColorLinea.AZUL,ColorLinea.NARANJA,ColorLinea.ROJA,ColorLinea.VERDE_CLARA,ColorLinea.VERDE_OSCURA};
		JComboBox<?> comboBoxColor = new JComboBox<Object>(colores);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
				List<LineaDeTransporte> datos_filtro = model.getDatos();
				if(!textFieldNombre.getText().isBlank()) {
					datos_filtro = datos_filtro.stream()
							.filter(lin -> lin.getNombre().toLowerCase().contains(textFieldNombre.getText().toLowerCase()))
							.collect(Collectors.toList());
				}
				if(comboBoxColor.getSelectedItem()!=null) {
					datos_filtro = datos_filtro.stream()
							.filter(lin -> lin.getColor().equals(comboBoxColor.getSelectedItem()))
							.collect(Collectors.toList());	
				}
				if(comboBoxEstado.getSelectedItem()!=null) {
					datos_filtro = datos_filtro.stream()
							.filter(lin -> lin.getEstado().equals(comboBoxEstado.getSelectedItem()))
							.collect(Collectors.toList());
				}
				model.setDatos(datos_filtro);
				model.fireTableDataChanged();
				System.out.println("Actualizando");
			}
		});
		GroupLayout gl_panelAtributos = new GroupLayout(panelAtributos);
		gl_panelAtributos.setHorizontalGroup(
			gl_panelAtributos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAtributos.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_panelAtributos.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelAtributos.createSequentialGroup()
							.addGap(68)
							.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelAtributos.createSequentialGroup()
							.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addGroup(gl_panelAtributos.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panelAtributos.createSequentialGroup()
							.addComponent(lblColor)
							.addGap(38)
							.addComponent(comboBoxColor, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
							.addGap(184))
						.addGroup(gl_panelAtributos.createSequentialGroup()
							.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_panelAtributos.setVerticalGroup(
			gl_panelAtributos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAtributos.createSequentialGroup()
					.addGroup(gl_panelAtributos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAtributos.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_panelAtributos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAtributos.createSequentialGroup()
									.addGap(1)
									.addComponent(lblNewLabel))
								.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_panelAtributos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAtributos.createSequentialGroup()
									.addGap(3)
									.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
								.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelAtributos.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_panelAtributos.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblColor)
								.addComponent(comboBoxColor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
					.addGap(25))
				.addGroup(Alignment.TRAILING, gl_panelAtributos.createSequentialGroup()
					.addContainerGap(69, Short.MAX_VALUE)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		panelAtributos.setLayout(gl_panelAtributos);
		
		JPanel panelLineas = new JPanel();
		panelLineas.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Lineas de transporte", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setLayout(null);
		panelOpciones.setBorder(new TitledBorder(null, "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MODIFICAR_LINEA);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(29, 39, 132, 26);
		panelOpciones.add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la linea?", "Eliminar linea de transporte",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion == 0) {
					try {
						manager.getLineaDeTransporteDAO().eliminarEntidad(actual);
						System.out.println("Linea eliminada");
						btnEditar.setEnabled(false);
						btnBorrar.setEnabled(false);
						actualizarTabla();
						//desabilitarBotones();						
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error al eliminar");
						e1.printStackTrace();
					}
				}
			}
		});
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(29, 77, 132, 26);
		panelOpciones.add(btnBorrar);
		
		GroupLayout gl_panelLineas = new GroupLayout(panelLineas);
		gl_panelLineas.setHorizontalGroup(
			gl_panelLineas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLineas.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOpciones, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addGap(7))
		);
		gl_panelLineas.setVerticalGroup(
			gl_panelLineas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelLineas.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panelLineas.createSequentialGroup()
					.addContainerGap(98, Short.MAX_VALUE)
					.addComponent(panelOpciones, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(118))
		);
		
		//Codigo de la tabla
		table = new JTable();
		scrollPane.setViewportView(table);
		panelLineas.setLayout(gl_panelLineas);
		
		this.model = new LineaTableModel(manager.getLineaDeTransporteDAO());
		this.model.updateModel();
		this.table.setModel(model);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int filasSeleccionadas = table.getSelectedRowCount();
				if(filasSeleccionadas == 1) {
					//habilitarBotones();
					btnBorrar.setEnabled(true);
					btnEditar.setEnabled(true);
					row_selected = table.getSelectedRow();
					Integer idLinea = (Integer) table.getValueAt(row_selected,0);
					try {
						actual = manager.getLineaDeTransporteDAO().obtenerEntidad(idLinea);
						System.out.println(actual.toString());
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error al buscar estacion");
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panelAtributos, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(panelLineas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(706, Short.MAX_VALUE)
					.addComponent(btnVolver)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panelAtributos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(panelLineas, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVolver)
					.addGap(6))
		);
		setLayout(groupLayout);

	}
	
	public void actualizarTabla() {
		table.clearSelection();
		model.updateModel();
		model.fireTableDataChanged();		
	}
	
	public LineaDeTransporte getLinea() {
		return actual;
	}
}
