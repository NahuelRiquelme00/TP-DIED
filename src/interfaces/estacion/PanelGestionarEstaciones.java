package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PanelGestionarEstaciones extends JPanel {

	private static final long serialVersionUID = 2146107098063201605L;
	private JTextField textFieldNombre;
	private JTextField textFieldApertura;
	private JTextField textFieldCierre;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private DAOManager manager;
	private Estacion estacion;
	private Boolean editable;
	private EstacionTableModel model;
	private JTable table;
	
	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Boolean isEditable() {
		return editable;
	}
	
	public void setEditable(Boolean editable) {
		this.editable = editable;
		textFieldNombre.setEditable(editable);
		textFieldApertura.setEditable(editable);
		textFieldCierre.setEditable(editable);
	}
	
	public void loadData() {
		if (estacion == null) {
			estacion = new Estacion();
		}
		textFieldNombre.setText(estacion.getNombre());
		textFieldApertura.setText(estacion.getHorarioApertura().toString());
		textFieldCierre.setText(estacion.getHorarioCierre().toString());
		
		textFieldNombre.requestFocus();
	}
	
	public void saveDate() {
		if (estacion == null) {
			estacion = new Estacion();
		}
		estacion.setNombre(textFieldNombre.getText());
		estacion.setHorarioApertura(LocalTime.parse(textFieldApertura.getText()));
		estacion.setHorarioCierre(LocalTime.parse(textFieldCierre.getText()));
	}
	
	public Estacion obtenerEstaciones() throws DAOException {
		Integer id = (int) model.getValueAt(table.getSelectedRow(), 0);
		return manager.getEstacionDAO().obtenerEntidad(id);
	}
	
	public PanelGestionarEstaciones(final VentanaPrincipal frame) {
		frame.setTitle("Gestionar estaciones");
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(panelPrincipal);
		GridBagLayout gbl_panelPrincipal = new GridBagLayout();
		gbl_panelPrincipal.columnWidths = new int[]{486, 268, 0};
		gbl_panelPrincipal.rowHeights = new int[]{56, 454, 23, 0};
		gbl_panelPrincipal.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelPrincipal.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPrincipal.setLayout(gbl_panelPrincipal);
		
		JPanel panelOpciones = new JPanel();
		panelOpciones.setLayout(null);
		panelOpciones.setBorder(new TitledBorder(null, "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.gridwidth = 2;
		gbc_panelOpciones.fill = GridBagConstraints.BOTH;
		gbc_panelOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelOpciones.gridx = 0;
		gbc_panelOpciones.gridy = 0;
		panelPrincipal.add(panelOpciones, gbc_panelOpciones);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAgregar.setBounds(10, 22, 91, 23);
		panelOpciones.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.setBounds(200, 22, 91, 23);
		panelOpciones.add(btnEditar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(291, 22, 91, 23);
		panelOpciones.add(btnBorrar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(646, 22, 91, 23);
		panelOpciones.add(btnBuscar);
		
		JButton btnHistorialMantenimientos = new JButton("Historial Mantenimientos");
		btnHistorialMantenimientos.setEnabled(false);
		btnHistorialMantenimientos.setBounds(382, 22, 181, 23);
		panelOpciones.add(btnHistorialMantenimientos);
		
		JPanel panelEstaciones = new JPanel();
		panelEstaciones.setLayout(null);
		panelEstaciones.setBorder(new TitledBorder(null, "Estaciones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelEstaciones = new GridBagConstraints();
		gbc_panelEstaciones.fill = GridBagConstraints.BOTH;
		gbc_panelEstaciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelEstaciones.gridx = 0;
		gbc_panelEstaciones.gridy = 1;
		panelPrincipal.add(panelEstaciones, gbc_panelEstaciones);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 22, 466, 421);
		panelEstaciones.add(scrollPane);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		manager = new DAOManagerImpl();
		this.model = new EstacionTableModel(manager.getEstacionDAO());
		this.model.updateModel();
		this.table.setModel(model);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setBorder(new TitledBorder(null, "Datos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.insets = new Insets(0, 0, 5, 0);
		gbc_panelDatos.gridx = 1;
		gbc_panelDatos.gridy = 1;
		panelPrincipal.add(panelDatos, gbc_panelDatos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(6, 56, 49, 14);
		panelDatos.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		textFieldNombre.setBounds(121, 53, 131, 20);
		panelDatos.add(textFieldNombre);
		
		JLabel lblApertura = new JLabel("Apertura");
		lblApertura.setBounds(6, 139, 57, 14);
		panelDatos.add(lblApertura);
		
		textFieldApertura = new JTextField();
		textFieldApertura.setColumns(10);
		textFieldApertura.setBounds(121, 136, 131, 20);
		panelDatos.add(textFieldApertura);
		
		JLabel lblCierre = new JLabel("Cierre");
		lblCierre.setBounds(6, 227, 49, 14);
		panelDatos.add(lblCierre);
		
		textFieldCierre = new JTextField();
		textFieldCierre.setColumns(10);
		textFieldCierre.setBounds(121, 224, 131, 20);
		panelDatos.add(textFieldCierre);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(6, 306, 44, 14);
		panelDatos.add(lblEstado);
		
		JLabel lblAccion = new JLabel("Accion.");
		lblAccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccion.setBounds(144, 429, 114, 14);
		panelDatos.add(lblAccion);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(70, 371, 91, 23);
		panelDatos.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(161, 371, 91, 23);
		panelDatos.add(btnCancelar);
		
		JRadioButton rdbtnOperativa = new JRadioButton("Operativa");
		buttonGroup.add(rdbtnOperativa);
		rdbtnOperativa.setBounds(88, 301, 164, 24);
		panelDatos.add(rdbtnOperativa);
		
		JRadioButton rdbtnMantenimiento = new JRadioButton("En mantenimiento");
		buttonGroup.add(rdbtnMantenimiento);
		rdbtnMantenimiento.setBounds(88, 329, 164, 24);
		panelDatos.add(rdbtnMantenimiento);
		
		JButton btnIr = new JButton("Ir");
		btnIr.setEnabled(false);
		btnIr.setBounds(23, 371, 49, 23);
		panelDatos.add(btnIr);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_PRINCIPAL);
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnVolver.gridx = 1;
		gbc_btnVolver.gridy = 2;
		panelPrincipal.add(btnVolver, gbc_btnVolver);

	}
}
