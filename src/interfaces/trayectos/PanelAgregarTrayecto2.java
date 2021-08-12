package interfaces.trayectos;

import javax.swing.JPanel;
import interfaces.VentanaPrincipal;
import javax.swing.border.TitledBorder;
import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Estacion;
import entidades.EstadoTrayecto;
import entidades.LineaDeTransporte;
import entidades.Trayecto;
import excepciones.DAOException;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PanelAgregarTrayecto2 extends JPanel {

	private static final long serialVersionUID = 6308460454389902713L;
	private DAOManager manager;
	private JTextField textFieldLinea;
	private JTextField textFieldDuracion;
	private JTextField textFieldDistancia;
	private JTextField textFieldCapacidad;
	private JTextField textFieldCosto;
	private JComboBox<?> comboBoxEstado;
	private LineaDeTransporte linea;
	private List<Estacion> listaEstaciones;
	private JComboBox<?> comboBoxOrigen;
	private JComboBox<?> comboBoxDestino;

	public PanelAgregarTrayecto2(final VentanaPrincipal frame, LineaDeTransporte l) {
		manager = DAOManagerImpl.getInstance();
		linea = l;
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Atributos del tramo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_TRAYECTOS);
			}
		});
		
		JLabel lblLinea = new JLabel("Linea");
		lblLinea.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblEstacionOrigen = new JLabel("Estacion Origen");
		lblEstacionOrigen.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblEstacionDestino = new JLabel("Estacion Destino");
		lblEstacionDestino.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblDistancia = new JLabel("Distancia");
		lblDistancia.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblCantidad = new JLabel("Capacidad");
		lblCantidad.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldLinea = new JTextField();
		textFieldLinea.setEditable(false);
		textFieldLinea.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldLinea.setColumns(10);
		
		
		//
		try {
			listaEstaciones = manager.getEstacionDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		comboBoxOrigen = new JComboBox<Object>(listaEstaciones.toArray());
		comboBoxOrigen.setSelectedIndex(-1);
		comboBoxOrigen.setEditable(true);
		
		comboBoxDestino = new JComboBox<Object>(listaEstaciones.toArray());
		comboBoxDestino.setEnabled(true);
		comboBoxDestino.setSelectedIndex(-1);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldDuracion.setColumns(10);
		
		textFieldDistancia = new JTextField();
		textFieldDistancia.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldDistancia.setColumns(10);
		
		textFieldCapacidad = new JTextField();
		textFieldCapacidad.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldCapacidad.setColumns(10);
		
		textFieldCosto = new JTextField();
		textFieldCosto.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldCosto.setColumns(10);
		
		EstadoTrayecto[] estados = {EstadoTrayecto.ACTIVO,EstadoTrayecto.NO_ACTIVO};
		comboBoxEstado = new JComboBox<Object>(estados);
		comboBoxEstado.setSelectedIndex(-1);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarTrayecto();
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_TRAYECTOS);
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(228, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLinea)
							.addGap(153)
							.addComponent(textFieldLinea, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDuracion)
							.addGap(128)
							.addComponent(textFieldDuracion, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDistancia)
							.addGap(127)
							.addComponent(textFieldDistancia, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCantidad)
							.addGap(118)
							.addComponent(textFieldCapacidad, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCosto)
							.addGap(150)
							.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEstado)
							.addGap(143)
							.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(93)
							.addComponent(btnNewButton)
							.addGap(19)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEstacionDestino)
								.addComponent(lblEstacionOrigen))
							.addGap(72)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxOrigen, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxDestino, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))))
					.addGap(216))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(57, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLinea)
						.addComponent(textFieldLinea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstacionOrigen)
						.addComponent(comboBoxOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblEstacionDestino))
						.addComponent(comboBoxDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDuracion)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldDuracion, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDistancia)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldDistancia, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCantidad)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldCapacidad, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCosto)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(lblEstado))
						.addComponent(comboBoxEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnCancelar))
					.addGap(32))
		);
		setLayout(groupLayout);
		
		cargarDatos();
	}
	
	public void cargarDatos() {
		textFieldLinea.setText(linea.getNombre());
	}
	
	public Trayecto obtenerTrayecto() {
		return new Trayecto(
				linea, 
				(Estacion)comboBoxOrigen.getSelectedItem(),
				(Estacion)comboBoxDestino.getSelectedItem(),
				Double.valueOf(textFieldDistancia.getText()),
				Integer.valueOf(textFieldDuracion.getText()),
				Integer.valueOf(textFieldCapacidad.getText()),
				(EstadoTrayecto)comboBoxEstado.getSelectedItem(),
				Double.valueOf(textFieldCosto.getText()) );
	}
	
	public void agregarTrayecto() {
		Trayecto nuevoTrayecto = this.obtenerTrayecto();
		try {
			manager.getTrayectoDAO().crearEntidad(nuevoTrayecto);
			JOptionPane.showMessageDialog(null, "El trayecto fue creado con exito","Trayecto creado", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al crear trayecto");
			e.printStackTrace();
		}
	}
}
