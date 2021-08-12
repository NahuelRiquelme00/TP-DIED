package interfaces.estacion;

import javax.swing.JPanel;

import entidades.Estacion;
import entidades.Mantenimiento;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;

import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelIniciarMantenimiento extends JPanel {

	private static final long serialVersionUID = -5903578272497806257L;
	private DAOManager manager;
	private Estacion estacion;
	private JTextField textFieldEstacionID;
	private JTextField textFieldInicio;
	private JTextArea textAreaObs;

	public PanelIniciarMantenimiento(final VentanaPrincipal frame, Estacion actual) {
		manager = DAOManagerImpl.getInstance();
		estacion = actual;
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Atributos del mantenimiento", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JLabel lblObs = new JLabel("Observaciones");
		lblObs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textAreaObs = new JTextArea();
		
		JLabel lblNombre = new JLabel("Estacion - ID");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldEstacionID = new JTextField(estacion.getNombre()+" ID - "+estacion.getId());
		textFieldEstacionID.setEditable(false);
		textFieldEstacionID.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldEstacionID.setColumns(10);
		
		JLabel lblInicio = new JLabel("Fecha de inicio");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldInicio = new JTextField(LocalDate.now().toString());
		textFieldInicio.setEditable(false);
		textFieldInicio.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldInicio.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarMantenimiento();
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		btnGuardar.setPreferredSize(new Dimension(80, 25));
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		btnNewButton.setPreferredSize(new Dimension(80, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(148, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(81)
							.addComponent(textFieldEstacionID, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInicio, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(textFieldInicio, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblObs, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(66)
							.addComponent(textAreaObs, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(148)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
					.addGap(135))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textFieldEstacionID, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInicio, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(textFieldInicio, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblObs, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(textAreaObs, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addGap(59)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(92))
		);
		setLayout(groupLayout);
	}
	public Mantenimiento obtenerMantenimiento() {
		return new Mantenimiento(estacion.getId(),
				LocalDate.now(),
				null,
				textAreaObs.getText());
	}
	
	public void agregarMantenimiento() {
		Mantenimiento nuevoMantenimiento = this.obtenerMantenimiento();
		try {
			manager.getMantenimientoDAO().crearEntidad(nuevoMantenimiento);
			JOptionPane.showMessageDialog(null, "El mantenimiento fue creado con exito","Mantenimiento creado", JOptionPane.INFORMATION_MESSAGE);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al intentar crear el mantenimiento");
			e.printStackTrace();
		}
	}
}
