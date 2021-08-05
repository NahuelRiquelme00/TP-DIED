package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import interfaces.VentanaPrincipal;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;

public class PanelGestionarEstaciones2 extends JPanel {

	private static final long serialVersionUID = 3145028131019651457L;

	public PanelGestionarEstaciones2(final VentanaPrincipal frame) {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnNewButton = new JButton("Agregar Estacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_AGREGAR_ESTACION);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnBuscarEstacion = new JButton("Buscar Estacion");
		btnBuscarEstacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		btnBuscarEstacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnInformacion = new JButton("Informacion sobre las estaciones");
		btnInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_INFORMACION_ESTACIONES);
			}
		});
		btnInformacion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setPreferredSize(new Dimension(80, 25));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				VentanaPrincipal inicio = new VentanaPrincipal();
//				inicio.setVisible(true);
//				frame.dispose();
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_PRINCIPAL);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(224)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(224)
					.addComponent(btnBuscarEstacion, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(224)
					.addComponent(btnInformacion, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(696, Short.MAX_VALUE)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(142)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnBuscarEstacion, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnInformacion, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(117)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
