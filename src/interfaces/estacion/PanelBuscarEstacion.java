package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.EstadoEstacion;
import entidades.Mantenimiento;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelBuscarEstacion extends JPanel {

	private static final long serialVersionUID = -644052679195097498L;
	private JTextField textFieldNombre;
	private JTextField textFieldApertura;
	private JTextField textFieldCierre;
	private DAOManager manager;
	private EstacionTableModel model;
	private JTable table;
	private Estacion actual;
	private int row_selected;
	JButton btnEditar;
	JButton btnBorrar;
	JButton btnMantenimientos;
	JButton btnAgregarMantenimiento;
	

	public PanelBuscarEstacion(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Listado de estaciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JPanel panelAtributos = new JPanel();
		panelAtributos.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Atributos de busqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 15));
		
		EstadoEstacion[] estados = {null ,EstadoEstacion.OPERATIVA,EstadoEstacion.EN_MANTENIMIENTO};
		JComboBox<?> comboBoxEstado = new JComboBox<Object>(estados);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblApertura = new JLabel("Horario de apertura");
		lblApertura.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblCierre = new JLabel("Horario de cierre");
		lblCierre.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldApertura = new JTextField();
		textFieldApertura.setColumns(10);
		
		textFieldCierre = new JTextField();
		textFieldCierre.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
				List<Estacion> datos_filtro = model.getDatos();
				if(!textFieldNombre.getText().isBlank()) {
					datos_filtro = datos_filtro.stream()
							.filter(est -> est.getNombre().toLowerCase().contains(textFieldNombre.getText().toLowerCase()))
							.collect(Collectors.toList());
				}
				if(!textFieldApertura.getText().isBlank()) {
					datos_filtro = datos_filtro.stream()
							.filter(est -> est.getHorarioApertura().toString().contains(textFieldApertura.getText()))
							.collect(Collectors.toList());
				}
				if(!textFieldCierre.getText().isBlank()) {
					datos_filtro = datos_filtro.stream()
							.filter(est -> est.getHorarioCierre().toString().contains(textFieldCierre.getText()))
							.collect(Collectors.toList());
				}
				if(comboBoxEstado.getSelectedItem()!=null) {
					datos_filtro = datos_filtro.stream()
							.filter(est -> est.getEstado().equals(comboBoxEstado.getSelectedItem()))
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
					.addGroup(gl_panelAtributos.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApertura)
						.addComponent(lblCierre))
					.addGap(18)
					.addGroup(gl_panelAtributos.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldApertura, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldCierre, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
		);
		gl_panelAtributos.setVerticalGroup(
			gl_panelAtributos.createParallelGroup(Alignment.LEADING)
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
					.addGap(17)
					.addComponent(lblApertura)
					.addGap(21)
					.addComponent(lblCierre))
				.addGroup(gl_panelAtributos.createSequentialGroup()
					.addGap(16)
					.addComponent(textFieldApertura, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(textFieldCierre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelAtributos.createSequentialGroup()
					.addGap(33)
					.addComponent(btnBuscar))
		);
		panelAtributos.setLayout(gl_panelAtributos);
		
		JPanel panelEstaciones = new JPanel();
		panelEstaciones.setBorder(new TitledBorder(null, "Estaciones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setBorder(new TitledBorder(null, "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MODIFICAR_ESTACION);
			}
		});
		btnEditar.setEnabled(false);
		
		JButton btnMantenimientos = new JButton("Ver mantenimientos");
		btnMantenimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_MANTENIMIENTOS_ESTACION);
			}
		});
		btnMantenimientos.setEnabled(false);
		
		JButton btnCambiarEstado = new JButton("Cambiar estado");
		btnCambiarEstado.setEnabled(false);
		btnCambiarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(actual.getEstado().equals(EstadoEstacion.OPERATIVA)) {
					int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro iniciar un mantenimiento?", "Iniciar mantenimiento",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0) {
						frame.cambiarPanel(VentanaPrincipal.PANE_INICIAR_MANTENIMIENTO);
					}	
				}else {
					int opcion = JOptionPane.showConfirmDialog(null, "¿Desea finalizar el mantenimiento?", "Finalizar mantenimiento",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(opcion==0) {
						//frame.cambiarPanel(VentanaPrincipal.PANE_FINALIZAR_MANTENIMIENTO);
						Mantenimiento soloEst = new Mantenimiento(actual.getId());
						try {
							manager.getMantenimientoDAO().modificarEntidad(soloEst);
						} catch (DAOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						};
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "El mantenimiento ha finalizado","Mantenimiento finalizado", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la estacion?", "Eliminar estacion",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion == 0) {
					try {
						manager.getEstacionDAO().eliminarEntidad(actual);
						System.out.println("Estacion eliminada");
						btnEditar.setEnabled(false);
						btnBorrar.setEnabled(false);
						btnMantenimientos.setEnabled(false);
						btnCambiarEstado.setEnabled(false);
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

		GroupLayout gl_panelEstaciones = new GroupLayout(panelEstaciones);
		gl_panelEstaciones.setHorizontalGroup(
			gl_panelEstaciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEstaciones.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 560, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelOpciones, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addGap(285))
		);
		gl_panelEstaciones.setVerticalGroup(
			gl_panelEstaciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEstaciones.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panelEstaciones.createSequentialGroup()
					.addContainerGap(86, Short.MAX_VALUE)
					.addComponent(panelOpciones, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
					.addGap(64))
		);
		GroupLayout gl_panelOpciones = new GroupLayout(panelOpciones);
		gl_panelOpciones.setHorizontalGroup(
			gl_panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOpciones.createSequentialGroup()
					.addGap(56)
					.addComponent(btnEditar))
				.addGroup(gl_panelOpciones.createSequentialGroup()
					.addGap(54)
					.addComponent(btnBorrar))
				.addGroup(gl_panelOpciones.createSequentialGroup()
					.addGap(28)
					.addComponent(btnCambiarEstado))
				.addGroup(gl_panelOpciones.createSequentialGroup()
					.addGap(15)
					.addComponent(btnMantenimientos))
		);
		gl_panelOpciones.setVerticalGroup(
			gl_panelOpciones.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelOpciones.createSequentialGroup()
					.addGap(21)
					.addComponent(btnEditar)
					.addGap(12)
					.addComponent(btnBorrar)
					.addGap(12)
					.addComponent(btnCambiarEstado)
					.addGap(12)
					.addComponent(btnMantenimientos))
		);
		panelOpciones.setLayout(gl_panelOpciones);
		
		//Codigo de la tabla
		table = new JTable();
		scrollPane.setViewportView(table);
		panelEstaciones.setLayout(gl_panelEstaciones);
		
		this.model = new EstacionTableModel(manager.getEstacionDAO());
		this.model.updateModel();
		this.table.setModel(model);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				int filasSeleccionadas = table.getSelectedRowCount();
				if(filasSeleccionadas == 1) {
					//Habilitar botones
					btnBorrar.setEnabled(true);
					btnEditar.setEnabled(true);
					btnMantenimientos.setEnabled(true);
					btnCambiarEstado.setEnabled(true);
					row_selected = table.getSelectedRow();
					Integer idEstacion = (Integer) table.getValueAt(row_selected,0);
					try {
						actual = manager.getEstacionDAO().obtenerEntidad(idEstacion);
						System.out.println(actual.toString());
					} catch (DAOException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error al buscar estacion");
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_ESTACIONES);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panelAtributos, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(panelEstaciones, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(708, Short.MAX_VALUE)
					.addComponent(btnVolver)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(panelAtributos, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panelEstaciones, GroupLayout.PREFERRED_SIZE, 382, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnVolver)
					.addGap(4))
		);
		setLayout(groupLayout);

	}
	
	public void actualizarTabla() {
		table.clearSelection();
		model.updateModel();
		model.fireTableDataChanged();		
	}

	public Estacion getEstacion() {
		return actual;
	}
}
