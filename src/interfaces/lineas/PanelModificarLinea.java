package interfaces.lineas;

import javax.swing.JPanel;

import entidades.ColorLinea;
import entidades.EstadoLineaDeTransporte;
import entidades.LineaDeTransporte;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.border.TitledBorder;
import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PanelModificarLinea extends JPanel {

	private static final long serialVersionUID = 180606568961878258L;
	DAOManager manager;
	private JTextField textFieldID;
	private JTextField textFieldNombre;
	private LineaDeTransporte modificar;
	private JComboBox<?> comboBoxColor;
	private JComboBox<?> comboBoxEstado;

	public PanelModificarLinea(final VentanaPrincipal frame, LineaDeTransporte actual) {
		manager = DAOManagerImpl.getInstance();
		modificar = actual;
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Atributos de la linea", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldID = new JTextField();
		textFieldID.setText((String) null);
		textFieldID.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldID.setEditable(false);
		textFieldID.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setText((String) null);
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNombre.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		EstadoLineaDeTransporte[] estados = {EstadoLineaDeTransporte.ACTIVA,EstadoLineaDeTransporte.NO_ACTIVA};
		comboBoxEstado = new JComboBox<Object>(estados);		
		ColorLinea[] colores = {ColorLinea.AMARILLA,ColorLinea.AZUL,ColorLinea.NARANJA,ColorLinea.ROJA,ColorLinea.VERDE_CLARA,ColorLinea.VERDE_OSCURA};
		comboBoxColor = new JComboBox<Object>(colores);
		
		JButton btnGuardar = new JButton("Modificar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarLinea();
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_LINEA);
			}
		});
		btnGuardar.setPreferredSize(new Dimension(80, 25));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_LINEA);
			}
		});
		btnCancelar.setPreferredSize(new Dimension(80, 25));
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(153, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(278))
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
									.addComponent(lblColor, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addGap(156)
									.addComponent(comboBoxColor, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEstado)
									.addGap(156)
									.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)))
							.addGap(144))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(92)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textFieldID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblColor)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(comboBoxColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEstado)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(3)
							.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		setLayout(groupLayout);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		textFieldID.setText(modificar.getId().toString());
		textFieldNombre.setText(modificar.getNombre());
		comboBoxColor.setSelectedItem(modificar.getColor());
		comboBoxEstado.setSelectedItem(modificar.getEstado());
	}
	
	public LineaDeTransporte obtenerLinea() {
		return new LineaDeTransporte(modificar.getId(),textFieldNombre.getText(),(ColorLinea) comboBoxColor.getSelectedItem(),(EstadoLineaDeTransporte) comboBoxEstado.getSelectedItem());
	}
	
	public void modificarLinea() {
		LineaDeTransporte lineaActualizada = this.obtenerLinea();
		try {
			manager.getLineaDeTransporteDAO().modificarEntidad(lineaActualizada);
			JOptionPane.showMessageDialog(null, "La linea fue modificada con exito","Linea de transporte modificada", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			System.out.println("Error al intentar modificar entidad");
			e.printStackTrace();
		}
	}
}
