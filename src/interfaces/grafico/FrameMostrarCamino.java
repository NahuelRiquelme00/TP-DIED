package interfaces.grafico;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidades.Estacion;
import entidades.Trayecto;

public class FrameMostrarCamino extends JFrame {

	private static final long serialVersionUID = -1976771790313998687L;
	private JPanel contentPane;

	public FrameMostrarCamino(List<Estacion> estaciones,List<Trayecto> rutas) {
		setType(Type.POPUP);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Recorrido seleccionado");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new GrafoPanel(estaciones,rutas);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
