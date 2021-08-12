package interfaces.trayectos;

import javax.swing.JPanel;

import entidades.EstadoTrayecto;
import entidades.Trayecto;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;

import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelModificarTrayecto extends JPanel {

	private static final long serialVersionUID = -7485831544221947916L;
	
	private DAOManager manager;
	private Trayecto modificar;
	private JTextField textFieldLinea;
	private JTextField textFieldOrigen;
	private JTextField textFieldDuracion;
	private JTextField textFieldDistancia;
	private JTextField textFieldCapacidad;
	private JTextField textFieldCosto;
	private JTextField textFieldDestino;
	private JComboBox<?> comboBoxEstado;

	public PanelModificarTrayecto(final VentanaPrincipal frame, Trayecto actual) {
		manager = DAOManagerImpl.getInstance();
		modificar = actual;
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Atributos del tramo", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldLinea = new JTextField();
		textFieldLinea.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldLinea.setEditable(false);
		textFieldLinea.setColumns(10);
		
		JLabel lblEstacionOrigen = new JLabel("Estacion Origen");
		lblEstacionOrigen.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldOrigen = new JTextField();
		textFieldOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldOrigen.setEditable(false);
		textFieldOrigen.setColumns(10);
		
		JLabel lblEstacionDestino = new JLabel("Estacion Destino");
		lblEstacionDestino.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldDuracion.setColumns(10);
		
		JLabel lblDistancia = new JLabel("Distancia");
		lblDistancia.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldDistancia = new JTextField();
		textFieldDistancia.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldDistancia.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Capacidad");
		lblCantidad.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldCapacidad = new JTextField();
		textFieldCapacidad.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldCapacidad.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldCosto = new JTextField();
		textFieldCosto.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldCosto.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 15));
		
		EstadoTrayecto[] estados = {EstadoTrayecto.ACTIVO,EstadoTrayecto.NO_ACTIVO};
		comboBoxEstado = new JComboBox<Object>(estados);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarTrayecto();
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_TRAYECTOS);
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_TRAYECTOS);
			}
		});
		
		textFieldDestino = new JTextField();
		textFieldDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldDestino.setEditable(false);
		textFieldDestino.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(228)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLinea, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
							.addGap(153)
							.addComponent(textFieldLinea, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEstacionOrigen, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(78)
							.addComponent(textFieldOrigen, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDistancia, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(127)
							.addComponent(textFieldDistancia, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(118)
							.addComponent(textFieldCapacidad, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(150)
							.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(143)
							.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(86)
							.addComponent(btnNewButton)
							.addGap(19)
							.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDuracion, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEstacionDestino, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldDestino, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldDuracion, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
					.addGap(216))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLinea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldLinea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEstacionOrigen, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEstacionDestino, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldDestino, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDuracion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldDuracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDistancia, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldDistancia, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCantidad, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldCapacidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCosto, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblEstado, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCancelar)
						.addComponent(btnNewButton))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		cargarDatos();
	}
	
	public void cargarDatos() {
		textFieldLinea.setText(modificar.getLinea().getNombre());
		textFieldOrigen.setText(modificar.getOrigen().getNombre());
		textFieldDestino.setText(modificar.getDestino().getNombre());
		textFieldDistancia.setText(modificar.getDistancia().toString());
		textFieldDuracion.setText(modificar.getDuracion().toString());
		textFieldCapacidad.setText(modificar.getCantidadMaximaPasajeros().toString());
		textFieldCosto.setText(modificar.getCosto().toString());
		comboBoxEstado.setSelectedItem(modificar.getEstado());
	}
	
	public Trayecto obtenerTrayecto() {
		return new Trayecto(
				modificar.getId(), 
				modificar.getLinea(), 
				modificar.getOrigen(),
				modificar.getDestino(),
				Double.valueOf(textFieldDistancia.getText()),
				Integer.valueOf(textFieldDuracion.getText()),
				Integer.valueOf(textFieldCapacidad.getText()),
				(EstadoTrayecto)comboBoxEstado.getSelectedItem(),
				Double.valueOf(textFieldCosto.getText()) );
	}
	
	public void modificarTrayecto() {
		Trayecto trayectoActualizado = this.obtenerTrayecto();
		try {
			manager.getTrayectoDAO().modificarEntidad(trayectoActualizado);
			JOptionPane.showMessageDialog(null, "El tramo fue modificada con exito","Tramo modificado", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al modificar el trayecto");
			e.printStackTrace();
		}
	}
	
}
