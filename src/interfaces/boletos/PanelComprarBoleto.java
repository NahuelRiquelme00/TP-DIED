package interfaces.boletos;

import javax.swing.JPanel;

import interfaces.VentanaPrincipal;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import entidades.Boleto;
import entidades.Trayecto;
import excepciones.DAOException;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.SystemColor;

public class PanelComprarBoleto extends JPanel {

	private static final long serialVersionUID = 2385232888474643278L;
	private DAOManager manager;
	private JTextField textFieldNombre;
	private JTextField textFieldCorreo;
	private JTextField textFieldVenta;
	private JTextField textFieldOrigen;
	private JTextField textFieldDestino;
	private JTextField textFieldCosto;
	private JTextArea textAreaCamino;
	private List<Trayecto> seleccionado;
	private String origen;
	private String destino;
	private Double costo;

	public PanelComprarBoleto(final VentanaPrincipal frame, List<Trayecto> t) {
		manager = DAOManagerImpl.getInstance();
		seleccionado = t;
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Datos del boleto", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_COMBINACIONES);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Nombre cliente");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		
		JLabel lblCorreCliente = new JLabel("Correo cliente");
		lblCorreCliente.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		
		JLabel lblFechaDeVenta = new JLabel("Fecha de venta");
		lblFechaDeVenta.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldVenta = new JTextField();
		textFieldVenta.setEditable(false);
		textFieldVenta.setColumns(10);
		
		JLabel lblEstacionDeOrigen = new JLabel("Estacion origen");
		lblEstacionDeOrigen.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblEstacionDestino = new JLabel("Estacion destino");
		lblEstacionDestino.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldOrigen = new JTextField();
		textFieldOrigen.setEditable(false);
		textFieldOrigen.setColumns(10);
		
		textFieldDestino = new JTextField();
		textFieldDestino.setEditable(false);
		textFieldDestino.setColumns(10);
		
		JLabel lblCamino = new JLabel("Camino");
		lblCamino.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textAreaCamino = new JTextArea();
		textAreaCamino.setLineWrap(true);
		textAreaCamino.setBackground(SystemColor.controlHighlight);
		textAreaCamino.setEditable(false);
		
		
		JLabel lblCosto = new JLabel("Costo");
		lblCosto.setFont(new Font("Dialog", Font.BOLD, 15));
		
		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		textFieldCosto.setColumns(10);
		
		JButton btnNewButton = new JButton("Comprar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregrarBoleto();
				frame.cambiarPanel(VentanaPrincipal.PANE_VER_BOLETOS);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(116, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(237)
									.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(237)
									.addComponent(textFieldCorreo, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(237)
									.addComponent(textFieldVenta, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(237)
									.addComponent(textFieldOrigen, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(238)
									.addComponent(textFieldDestino, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(237)
									.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(237)
									.addComponent(textAreaCamino, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(1)
										.addComponent(lblCamino, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(1)
										.addComponent(lblCosto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(1)
										.addComponent(lblEstacionDestino, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(lblEstacionDeOrigen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(1)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblCorreCliente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblFechaDeVenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)))))
							.addGap(103))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addGap(18)
							.addComponent(btnCancelar)
							.addGap(297))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCorreo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCorreCliente))
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFechaDeVenta))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(textFieldOrigen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addComponent(lblEstacionDeOrigen)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(43)
							.addComponent(textFieldDestino, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(lblEstacionDestino)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(textFieldCosto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(lblCosto)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(textAreaCamino, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(lblCamino)))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnNewButton))
					.addGap(16))
		);
		setLayout(groupLayout);
		cargarDatos();
	}
	
	public void cargarDatos() {
		textFieldVenta.setText(LocalDate.now().toString());
		origen = seleccionado.get(0).getOrigen().getNombre();
		destino = seleccionado.get(seleccionado.size()-1).getDestino().getNombre();
		textFieldOrigen.setText(origen);
		textFieldDestino.setText(destino);
		costo = seleccionado.stream().map(r -> r.getCosto()).reduce(0.0, (c1,c2) -> c1+c2);
		textFieldCosto.setText(costo.toString());
		textAreaCamino.setText(seleccionado.toString());		
	}
	
	public Boleto obtenerDatos() {
		return new Boleto(textFieldCorreo.getText(),
				textFieldNombre.getText(),
				LocalDate.now(),
				origen,
				destino,
				seleccionado.toString(),
				costo);		
	}
	
	public void agregrarBoleto() {
		Boleto nuevoBoleto = this.obtenerDatos();
		try {
			manager.getBoletoDAO().crearEntidad(nuevoBoleto);
			JOptionPane.showMessageDialog(null, "El boleto fue comprado con exito","Boleto comprado", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			System.out.println("Error al generar boleto");
			e.printStackTrace();
		}
	}
}
