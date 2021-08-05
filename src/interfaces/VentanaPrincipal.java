package interfaces;

import java.awt.Container;
import java.awt.EventQueue;
import javax.swing.JFrame;

import interfaces.boletos.PanelComprarBoleto;
import interfaces.boletos.PanelGestionarBoletos;
import interfaces.boletos.PanelVerBoletos;
import interfaces.estacion.PanelAgregarEstacion;
import interfaces.estacion.PanelAgregarMantenimiento;
import interfaces.estacion.PanelBuscarEstacion;
import interfaces.estacion.PanelFlujoMaximo;
import interfaces.estacion.PanelGestionarEstaciones2;
import interfaces.estacion.PanelInformacionEstaciones;
import interfaces.estacion.PanelMantenimientosEstacion;
import interfaces.estacion.PanelModificarEstacion;
import interfaces.estacion.PanelPageRank;
import interfaces.estacion.PanelProximoMantenimiento;
import interfaces.lineas.PanelAgregarLinea;
import interfaces.lineas.PanelBuscarLinea;
import interfaces.lineas.PanelGestionarLineas;
import interfaces.trayectos.PanelAgregarTrayecto;
import interfaces.trayectos.PanelGestionarTrayectos;
import interfaces.trayectos.PanelVerTrayectos;


public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = -2455145160154899220L;
	
	public static final int PANE_MENU_PRINCIPAL = 0;
	public static final int PANE_MENU_GESTIONAR_ESTACIONES = 1;
	public static final int PANE_AGREGAR_ESTACION = 2;
	public static final int PANE_BUSCAR_ESTACION = 3;
	public static final int PANE_MODIFICAR_ESTACION = 4;
	public static final int PANE_VER_MANTENIMIENTOS_ESTACION = 5;
	public static final int PANE_AGREGAR_MANTENIMIENTO = 6;
	public static final int PANE_MENU_INFORMACION_ESTACIONES = 7;
	public static final int PANE_FLUJO_MAXIMO = 8;
	public static final int PANE_PAGE_RANK = 9;	
	public static final int PANE_PROXIMO_MANTENIMIENTO = 10;
	public static final int PANE_MENU_GESTIONAR_LINEAS = 11;
	public static final int PANE_AGREGAR_LINEA = 12;
	public static final int PANE_BUSCAR_LINEA = 13;
	public static final int PANE_MODIFICAR_LINEA = 14;
	public static final int PANE_MENU_GESTIONAR_TRAYECTOS = 16;
	public static final int PANE_AGREGAR_TRAYECTO = 17;
	public static final int PANE_VER_TRAYECTOS = 18;
	public static final int PANE_MENU_GESTIONAR_BOLETOS = 20;
	public static final int PANE_COMPRAR_BOLETO = 21;
	public static final int PANE_VER_BOLETOS = 22;
	
	private Container previousPane;
	private Container previouspreviousPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		setResizable(false);
		setTitle("Sistema de gesti\u00F3n de transporte multimodal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setLocationRelativeTo(null);
		cambiarPanel(PANE_MENU_PRINCIPAL);
	}

	public void cambiarPanel(int panel)  {
		previouspreviousPane = previousPane;
		previousPane = this.getContentPane();
		switch(panel) {
		case PANE_MENU_PRINCIPAL:
			this.setContentPane(new PanelMenuPrincipal(this));
			this.setTitle("Sistema de gestion de transporte multimodal");
			this.pack();
			break;
		case PANE_MENU_GESTIONAR_ESTACIONES:
			this.setContentPane(new PanelGestionarEstaciones2(this));
			this.setTitle("Gestionar estaciones");
			this.pack();
			break;
		case PANE_AGREGAR_ESTACION:
			this.setContentPane(new PanelAgregarEstacion(this));
			this.setTitle("Agregar estacion");
			this.pack();
			break;
		case PANE_BUSCAR_ESTACION:
			this.setContentPane(new PanelBuscarEstacion(this));
			this.setTitle("Buscar estacion");
			this.pack();
			break;
		case PANE_MODIFICAR_ESTACION:
			this.setContentPane(new PanelModificarEstacion(this,((PanelBuscarEstacion) previousPane).getEstacion()));
			this.setTitle("Modificar estacion");
			this.pack();
			break;
		case PANE_VER_MANTENIMIENTOS_ESTACION:
			this.setContentPane(new PanelMantenimientosEstacion(this,((PanelBuscarEstacion) previousPane).getEstacion()));
			this.setTitle("Historial de mantenimientos de la estacion seleccionada");
			this.pack();
			break;
		case PANE_AGREGAR_MANTENIMIENTO:
			this.setContentPane(new PanelAgregarMantenimiento(this,((PanelBuscarEstacion) previousPane).getEstacion()));
			this.setTitle("Agregar mantenimiento");
			this.pack();
			break;
		case PANE_MENU_INFORMACION_ESTACIONES:
			this.setContentPane(new PanelInformacionEstaciones(this));
			this.setTitle("Informacion sobre las estaciones");
			this.pack();
			break;
		case PANE_FLUJO_MAXIMO:
			this.setContentPane(new PanelFlujoMaximo(this));
			this.setTitle("Ver flujo maximo entre estaciones");
			this.pack();
			break;
		case PANE_PAGE_RANK:
			this.setContentPane(new PanelPageRank(this));
			this.setTitle("Ver tabla de PAGE RANK");
			this.pack();
			break;
		case PANE_PROXIMO_MANTENIMIENTO:
			this.setContentPane(new PanelProximoMantenimiento(this));
			this.setTitle("Ver proximos manteniemientos programados");
			this.pack();
			break;
		case PANE_MENU_GESTIONAR_LINEAS:
			this.setContentPane(new PanelGestionarLineas(this));
			this.setTitle("Gestion de lineas de transporte");
			this.pack();
			break;
		case PANE_AGREGAR_LINEA:
			this.setContentPane(new PanelAgregarLinea(this));
			this.setTitle("Agregar linea de transporte");
			this.pack();
			break;
		case PANE_BUSCAR_LINEA:
			this.setContentPane(new PanelBuscarLinea(this));
			this.setTitle("Buscar linea de transporte");
			this.pack();
			break;
		case PANE_MENU_GESTIONAR_TRAYECTOS:
			this.setContentPane(new PanelGestionarTrayectos(this));
			this.setTitle("Gestion de trayectos");
			this.pack();
			break;
		case PANE_AGREGAR_TRAYECTO:
			this.setContentPane(new PanelAgregarTrayecto(this));
			this.setTitle("Agregar trayecto");
			this.pack();
			break;
		case PANE_VER_TRAYECTOS:
			this.setContentPane(new PanelVerTrayectos(this));
			this.setTitle("Ver listado de trayectos");
			this.pack();
			break;
		case PANE_MENU_GESTIONAR_BOLETOS:
			this.setContentPane(new PanelGestionarBoletos(this));
			this.setTitle("Gestion de boletos");
			this.pack();
			break;
		case PANE_COMPRAR_BOLETO:
			this.setContentPane(new PanelComprarBoleto(this));
			this.setTitle("Comprar boleto");
			this.pack();
			break;
		case PANE_VER_BOLETOS:
			this.setContentPane(new PanelVerBoletos(this));
			this.setTitle("Ver listado de boletos");
			this.pack();
			break;
		}
		this.getContentPane().setVisible(false);
		this.getContentPane().setVisible(true);
	}
	
	public void backPreviousPane() {
		getContentPane().setVisible(false);
		this.setContentPane(previousPane);
		previousPane=previouspreviousPane;
		getContentPane().setVisible(true);
	}
}
