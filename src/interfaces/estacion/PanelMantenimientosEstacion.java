package interfaces.estacion;

import javax.swing.JPanel;

import entidades.Estacion;
import entidades.Mantenimiento;
import excepciones.DAOException;
import interfaces.VentanaPrincipal;
import javax.swing.border.TitledBorder;
import dao.DAOManager;
import daoImpl.DAOManagerImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelMantenimientosEstacion extends JPanel {

	private static final long serialVersionUID = 8354039540721308L;
	private DAOManager manager;
	private JTextField textFieldNombre;
	private JTable table;
	private HistorialTableModel model;
	private List<Mantenimiento> mantenimientos;

	public PanelMantenimientosEstacion(final VentanaPrincipal frame, Estacion actual) {
		manager = DAOManagerImpl.getInstance();
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Historial de mantenimientos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		JLabel lblNombre = new JLabel("Estacion");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		textFieldNombre.setText((String) null);
		textFieldNombre.setFont(new Font("Dialog", Font.PLAIN, 15));
		textFieldNombre.setColumns(10);
		textFieldNombre.setText(actual.getNombre()+" ID "+actual.getId());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.cambiarPanel(VentanaPrincipal.PANE_BUSCAR_ESTACION);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 776, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(685)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(254, Short.MAX_VALUE)
					.addComponent(lblNombre)
					.addGap(28)
					.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addGap(244))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(textFieldNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnNewButton))
		);
		
		try {
			mantenimientos = manager.getMantenimientoDAO().obtenerTodasLasEntidades();
		} catch (DAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Mantenimiento> mant = mantenimientos.stream().filter(m -> m.getIdEstacion().equals(actual.getId())).collect(Collectors.toList());
			
		table = new JTable();
		scrollPane.setViewportView(table);
		model = new HistorialTableModel(mant);
		table.setModel(model);
		
		setLayout(groupLayout);
		
		if(mant.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No existen mantenimientos realizados en esta estacion","Mantenimientos inexistentes", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
