package interfaces;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class PanelMenuPrincipal extends JPanel {

	private static final long serialVersionUID = 5977351894072574469L;

	public PanelMenuPrincipal(final VentanaPrincipal frame) {
		setFont(new Font("Tahoma", Font.PLAIN, 30));
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Menu Principal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton btnGestionarEstaciones = new JButton("Gestionar Estaciones");
		btnGestionarEstaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_ESTACIONES);
			}
		});
		btnGestionarEstaciones.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnGestionar = new JButton("Gestionar Lineas de Transporte");
		btnGestionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_LINEAS);
			}
		});
		btnGestionar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnGestionarTrayectos = new JButton("Gestionar Trayectos");
		btnGestionarTrayectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_TRAYECTOS);
			}
		});
		btnGestionarTrayectos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnComprarBoletos = new JButton("Comprar Boleto");
		btnComprarBoletos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_BOLETOS);
			}
		});
		btnComprarBoletos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(226)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnGestionarEstaciones, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestionar, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGestionarTrayectos, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnComprarBoletos, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(110, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(728, Short.MAX_VALUE)
					.addComponent(btnSalir)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(92)
					.addComponent(btnGestionarEstaciones, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnGestionar, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnGestionarTrayectos, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(btnComprarBoletos, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addGap(67)
					.addComponent(btnSalir)
					.addContainerGap())
		);
		setLayout(groupLayout);

	}
}
