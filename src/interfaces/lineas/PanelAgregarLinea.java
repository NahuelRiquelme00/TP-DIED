package interfaces.lineas;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.ColorLinea;
import entidades.EstadoLineaDeTransporte;
import entidades.LineaDeTransporte;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;

public class PanelAgregarLinea extends JPanel {
	
	private static final long serialVersionUID = -8411840365928044902L;
	private DAOManager manager;
	private JTextField textFieldNombre;
	private JComboBox<?> comboBoxColor;
	private JComboBox<?> comboBoxEstado;

	public PanelAgregarLinea(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Atributos lineas de transporte", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_LINEAS);
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNombre.setColumns(10);
		
		EstadoLineaDeTransporte[] estados = {EstadoLineaDeTransporte.ACTIVA,EstadoLineaDeTransporte.NO_ACTIVA};
		comboBoxEstado = new JComboBox<Object>(estados);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarLinea();
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_LINEAS);
			}
		});
		btnGuardar.setPreferredSize(new Dimension(80, 25));
		
		ColorLinea[] colores = {ColorLinea.AMARILLA,ColorLinea.AZUL,ColorLinea.NARANJA,ColorLinea.ROJA,ColorLinea.VERDE_CLARA,ColorLinea.VERDE_OSCURA};
		comboBoxColor = new JComboBox<Object>(colores);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(156, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNombre)
									.addGap(146)
									.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
									.addGap(63)
									.addComponent(comboBoxColor, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEstado)
									.addGap(156)
									.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)))
							.addGap(143))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(btnCancelar)
							.addGap(299))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(153)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblColor)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(comboBoxColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEstado)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(98)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnCancelar))
					.addGap(105))
		);
		setLayout(groupLayout);
	}
	
	public LineaDeTransporte obtenerLinea() {
		return new LineaDeTransporte(textFieldNombre.getText(),
				(ColorLinea)comboBoxColor.getSelectedItem(),
				(EstadoLineaDeTransporte)comboBoxEstado.getSelectedItem());
	}
	
	public void agregarLinea() {
		LineaDeTransporte nuevaLinea = this.obtenerLinea();
		try {
			manager.getLineaDeTransporteDAO().crearEntidad(nuevaLinea);
			JOptionPane.showMessageDialog(null, "La linea fue creada con exito","Linea creada", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			System.out.println("Error al intentar crear la entidad");
			e.printStackTrace();
		}
	}
}
