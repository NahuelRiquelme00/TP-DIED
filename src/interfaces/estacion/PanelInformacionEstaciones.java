package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import interfaces.VentanaPrincipal;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class PanelInformacionEstaciones extends JPanel {

	private static final long serialVersionUID = 1854283304528464403L;

	public PanelInformacionEstaciones(final VentanaPrincipal frame) {
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnCalcularFlujoMaximo = new JButton("Calcular Flujo Maximo");
		btnCalcularFlujoMaximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_FLUJO_MAXIMO);
			}
		});
		btnCalcularFlujoMaximo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnVer = new JButton("Ver Page Rank");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_PAGE_RANK);
			}
		});
		btnVer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnProximoMantenimiento = new JButton("Proximo Mantenimiento");
		btnProximoMantenimiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_PROXIMO_MANTENIMIENTO);
			}
		});
		btnProximoMantenimiento.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnNewButton_1 = new JButton("Volver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_ESTACIONES);
			}
		});
		btnNewButton_1.setPreferredSize(new Dimension(80, 25));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(231)
					.addComponent(btnCalcularFlujoMaximo, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(231)
					.addComponent(btnVer, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(231)
					.addComponent(btnProximoMantenimiento, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(698, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(148)
					.addComponent(btnCalcularFlujoMaximo, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnVer, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnProximoMantenimiento, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(112)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}

}
