package interfaces.estacion;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import daoImpl.MantenimientoDAOImpl;
import entidades.Estacion;
import interfaces.VentanaPrincipal;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class PanelProximoMantenimiento extends JPanel {

	private static final long serialVersionUID = 8074571419448779923L;
	private DAOManager manager;
	private JTable table;
	private ProximoMantenimientoTableModel model;
	private Map<Estacion, LocalDate> ultimosMantenimientos;

	public PanelProximoMantenimiento(final VentanaPrincipal frame) {
		manager = DAOManagerImpl.getInstance();
		ultimosMantenimientos = ((MantenimientoDAOImpl)manager.getMantenimientoDAO()).recuperarUltimosMantenimientos();
		setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Proximos Mantenimientos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_MENU_INFORMACION_ESTACIONES);
			}
		});
		btnVolver.setPreferredSize(new Dimension(80, 25));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(6)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 770, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(696)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(btnVolver, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		PriorityQueue<Estacion> monticulo = new PriorityQueue<Estacion>(10,(e1,e2) -> ultimosMantenimientos.get(e1).compareTo(ultimosMantenimientos.get(e2)));
		for(Estacion e : ultimosMantenimientos.keySet()) monticulo.offer(e);
		
		model = new ProximoMantenimientoTableModel();
		table = new JTable(model);
		table.setEnabled(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionBackground(Color.RED);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();	
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(LocalDate.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);		
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		
		List<List<Object>> data = new ArrayList<List<Object>>();
		
		while(!monticulo.isEmpty()) {
			List<Object> fila = new ArrayList<Object>();
			Estacion prox = monticulo.poll();
			
			fila.add(prox.getId());
			fila.add(prox.getNombre());
			if(ultimosMantenimientos.get(prox).equals(LocalDate.MIN)) fila.add("Nunca");
			else fila.add(ultimosMantenimientos.get(prox));
			data.add(fila);
		}
		model.setData(data);
		model.fireTableDataChanged();
		table.setRowSelectionInterval(0,0);

	}
}
