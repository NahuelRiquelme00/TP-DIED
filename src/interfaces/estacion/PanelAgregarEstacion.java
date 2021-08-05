package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.EstadoEstacion;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelAgregarEstacion extends JPanel {

	private static final long serialVersionUID = 6797513218636515498L;
	private JTextField textFieldNombre;
	private JTextField textFieldApertura;
	private JTextField textFieldCierre;
	JComboBox<?> comboBoxEstado;

	public PanelAgregarEstacion(final VentanaPrincipal frame) {
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
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_ESTACIONES);
			}
		});
		btnNewButton.setPreferredSize(new Dimension(80, 25));
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarEstacion();
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_ESTACIONES);
			}
		});
		btnGuardar.setPreferredSize(new Dimension(80, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(156)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(lblApertura)
						.addComponent(lblCierre)
						.addComponent(lblEstado))
					.addGap(63)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCierre, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(textFieldApertura, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(textFieldNombre, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
						.addComponent(comboBoxEstado, 0, 272, Short.MAX_VALUE))
					.addGap(146))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(295, Short.MAX_VALUE)
					.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addGap(287))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(94)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldNombre)
						.addComponent(lblNombre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldApertura)
						.addComponent(lblApertura, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFieldCierre)
						.addComponent(lblCierre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
	
	public Estacion obtenerEstacion() {
		return new Estacion(textFieldNombre.getText(),LocalTime.parse(textFieldApertura.getText())
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
	
}
