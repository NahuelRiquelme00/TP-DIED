package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import entidades.Estacion;
import interfaces.VentanaPrincipal;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelAgregarMantenimiento extends JPanel {
	private static final long serialVersionUID = -135954443302962401L;
	
	private JTextField textFieldNombre;
	private JTextField textFieldInicio;
	private JTextField textFieldFin;

	public PanelAgregarMantenimiento(final VentanaPrincipal frame, Estacion actual) {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Atributos del mantenimiento", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNombre = new JLabel("ID - Estacion");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblInicio = new JLabel("Fecha de inicio");
		lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblFin = new JLabel("Fecha de fin");
		lblFin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblObs = new JLabel("Observaciones");
		lblObs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNombre.setColumns(10);
		
		textFieldInicio = new JTextField();
		textFieldInicio.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldInicio.setColumns(10);
		
		textFieldFin = new JTextField();
		textFieldFin.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldFin.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		btnNewButton.setPreferredSize(new Dimension(80, 25));
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnGuardar.setPreferredSize(new Dimension(80, 25));
		
		JTextArea textAreaObs = new JTextArea();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(148, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombre)
							.addGap(81)
							.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblInicio)
							.addGap(63)
							.addComponent(textFieldInicio, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFin)
							.addGap(87)
							.addComponent(textFieldFin, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblObs)
							.addGap(66)
							.addComponent(textAreaObs, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(156)
							.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
					.addGap(137))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(81, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombre)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInicio)
						.addComponent(textFieldInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFin)
						.addComponent(textFieldFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblObs)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(textAreaObs, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGuardar, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(57))
		);
		setLayout(groupLayout);

	}
}
