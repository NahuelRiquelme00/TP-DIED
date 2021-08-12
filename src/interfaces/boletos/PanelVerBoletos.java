package interfaces.boletos;

import javax.swing.JPanel;

import interfaces.VentanaPrincipal;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;

public class PanelVerBoletos extends JPanel {

	private static final long serialVersionUID = -1284703670946654660L;
	private DAOManager manager;
	private JTable table;
	private BoletoTableModel model;

	public PanelVerBoletos(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de boletos ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_GESTIONAR_BOLETOS);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Codigo de la tabla
		table = new JTable();
		scrollPane.setViewportView(table);		
		this.model = new BoletoTableModel(manager.getBoletoDAO());
		this.model.updateModel();
		this.table.setModel(model);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(706)
					.addComponent(btnVolver))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 513, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnVolver))
		);
		setLayout(groupLayout);

	}
}
