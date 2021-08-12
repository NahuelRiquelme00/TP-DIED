package interfaces.estacion;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelModificarEstacion extends JPanel {

	private static final long serialVersionUID = -1388605330312710339L;
	DAOManager manager;
	private JTextField textFieldNombre;
	private JTextField textFieldApertura;
	private JTextField textFieldCierre;
	private JTextField textFieldID;
	private Estacion modificar;

	public PanelModificarEstacion(final VentanaPrincipal frame, Estacion actual) {
		manager = DAOManagerImpl.getInstance();
		modificar = actual;
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Atributos de la estacion", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblApertura = new JLabel("Hora de apertura");
		lblApertura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblCierre = new JLabel("Hora de cierre");
		lblCierre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNombre.setColumns(10);
		
		textFieldApertura = new JTextField();
		textFieldApertura.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldApertura.setColumns(10);
		
		textFieldCierre = new JTextField();
		textFieldCierre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldCierre.setColumns(10);
		
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
					.addContainerGap(156, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
									.addGap(146)
									.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNombre)
									.addGap(146)
									.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblApertura)
									.addGap(63)
									.addComponent(textFieldApertura, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblCierre)
									.addGap(89)
									.addComponent(textFieldCierre, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)))
							.addGap(146))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addGap(25)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(280))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(103)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApertura)
						.addComponent(textFieldApertura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCierre)
						.addComponent(textFieldCierre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(138)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(54))
		);
		setLayout(groupLayout);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		textFieldID.setText(modificar.getId().toString());
		textFieldNombre.setText(modificar.getNombre());
		textFieldApertura.setText(modificar.getHorarioApertura().toString());
		textFieldCierre.setText(modificar.getHorarioCierre().toString());
	}
	
	public Estacion obtenerEstacion() {
		return new Estacion(modificar.getId(),textFieldNombre.getText(),LocalTime.parse(textFieldApertura.getText())
				,LocalTime.parse(textFieldCierre.getText()),modificar.getEstado());
	}
	
	public void agregarEstacion() {
		manager = DAOManagerImpl.getInstance();
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
