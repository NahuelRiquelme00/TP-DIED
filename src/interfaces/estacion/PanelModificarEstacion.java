package interfaces.estacion;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.EstadoEstacion;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelModificarEstacion extends JPanel {

	private static final long serialVersionUID = -1388605330312710339L;
	private JTextField textFieldNombre;
	private JTextField textFieldApertura;
	private JTextField textFieldCierre;
	JComboBox<?> comboBoxEstado;
	private JTextField textFieldID;
	private Estacion modificar;

	public PanelModificarEstacion(final VentanaPrincipal frame, Estacion actual) {
		modificar = actual;
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Atributos de la estacion", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblApertura = new JLabel("Hora de apertura");
		lblApertura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblCierre = new JLabel("Hora de cierre");
		lblCierre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNombre.setColumns(10);
		
		textFieldApertura = new JTextField();
		textFieldApertura.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldApertura.setColumns(10);
		
		textFieldCierre = new JTextField();
		textFieldCierre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldCierre.setColumns(10);
		
		EstadoEstacion[] estados = {EstadoEstacion.OPERATIVA,EstadoEstacion.EN_MANTENIMIENTO};
		comboBoxEstado = new JComboBox<Object>(estados);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		btnNewButton.setPreferredSize(new Dimension(80, 25));
		
		JButton btnGuardar = new JButton("Modificar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarEstacion();
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		btnGuardar.setPreferredSize(new Dimension(80, 25));
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldID = new JTextField();
		textFieldID.setEditable(false);
		textFieldID.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldID.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(287))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(150)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(lblApertura)
						.addComponent(lblCierre)
						.addComponent(lblEstado)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(textFieldCierre, GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
							.addComponent(textFieldApertura, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
							.addComponent(textFieldNombre, GroupLayout.DEFAULT_SIZE, 7, Short.MAX_VALUE)
							.addComponent(comboBoxEstado, 0, 7, Short.MAX_VALUE)))
					.addGap(152))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldApertura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblApertura))
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldCierre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCierre))
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		textFieldID.setText(modificar.getId().toString());
		textFieldNombre.setText(modificar.getNombre());
		textFieldApertura.setText(modificar.getHorarioApertura().toString());
		textFieldCierre.setText(modificar.getHorarioCierre().toString());
		comboBoxEstado.setSelectedItem(modificar.getEstado());
	}
	
	public Estacion obtenerEstacion() {
		return new Estacion(modificar.getId(),textFieldNombre.getText(),LocalTime.parse(textFieldApertura.getText())
				,LocalTime.parse(textFieldCierre.getText()),(EstadoEstacion) comboBoxEstado.getSelectedItem());
	}
	
	public void agregarEstacion() {
		DAOManager manager = new DAOManagerImpl();
		Estacion nuevaEstacion = this.obtenerEstacion();
		try {
			manager.getEstacionDAO().crearEntidad(nuevaEstacion);
			JOptionPane.showMessageDialog(null, "La estacion fue creada con exito","Estacion creada", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			System.out.println("Error al intentar crear entidad");
			e.printStackTrace();
		}		
	}
	
	public void modificarEstacion() {
		DAOManager manager = new DAOManagerImpl();
		Estacion estacionActualizada = this.obtenerEstacion();
		try {
			manager.getEstacionDAO().modificarEntidad(estacionActualizada);
			JOptionPane.showMessageDialog(null, "La estacion fue modificada con exito","Estacion modificada", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			System.out.println("Error al intentar modificar entidad");
			e.printStackTrace();
		}
	}
}
