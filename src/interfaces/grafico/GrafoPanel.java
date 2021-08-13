package interfaces.grafico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import entidades.Estacion;
import entidades.Trayecto;

public class GrafoPanel extends JPanel {
	private static final long serialVersionUID = -2001468260698444240L;
    @SuppressWarnings("unused")
	private ControladorGrafo grfController;
    private List<VerticeView> vertices;
    private List<AristaView> aristas;
    
    public GrafoPanel(List<Estacion> estaciones,List<Trayecto> rutas) {
        //Inicializas vertices y aristas
        this.vertices = new ArrayList<>(); 
        this.aristas = new ArrayList<>();
        grfController = new ControladorGrafo(this,estaciones,rutas);
    }
    
    
    // AGREGO AL DIBUJO DEL GRAFO ARISTAS Y VERTICES
    public void agregar(AristaView arista){
        this.aristas.add(arista);
    }    
    
    public void agregar(VerticeView vert){
        this.vertices.add(vert);
    }

    //Metodo importante para pintar el camino
    public void caminoPintar(List<Estacion> camino){
    	Integer idOrigen =-1;
    	Integer idDestino =-1;
    	for(Estacion mat : camino) {
    		if(idOrigen<0) {
    			idOrigen=mat.getId();
    		}else {
    			idDestino = mat.getId();
    			for(AristaView av : this.aristas) {
    				if(av.getOrigen().getId().equals(idOrigen) && av.getDestino().getId().equals(idDestino) ) {
    	    			av.setColor(Color.PINK);
    	    			av.getOrigen().setColor(Color.GREEN);
    	    			av.getDestino().setColor(Color.GREEN);
    				}
    			}
    			idOrigen = idDestino;
    		}
    	}
    }
    
    // DIBUJAR VERTICES Y ARISTAS
    private void dibujarVertices(Graphics2D g2d) {
        for (VerticeView v : this.vertices) {
            g2d.setPaint(Color.BLUE);
            g2d.drawString(v.etiqueta(),v.getCoordenadaX()-5,v.getCoordenadaY()-5);
            g2d.setPaint(v.getColor());
            g2d.fill(v.getNodo());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        System.out.println(this.aristas);
        
        Double tita=0.0;
        Point p1 = new Point(0,0);
        Point p2 = new Point(0,0);
        Point center= new Point(0,0);
        
        for (AristaView a : this.aristas) {            
            tita=Math.atan((double)(a.getDestino().getCoordenadaY()-a.getOrigen().getCoordenadaY())/(a.getDestino().getCoordenadaX()-a.getOrigen().getCoordenadaX()));
            
            if(tita==0 && a.getOrigen().getCoordenadaX()>a.getDestino().getCoordenadaX()){tita=Math.PI;}
            else if(a.getDestino().getCoordenadaX()<a.getOrigen().getCoordenadaX()){tita=tita+Math.PI;}
            
            g2d.setPaint(a.getColor());
            g2d.setStroke ( a.getFormatoLinea());
            g2d.draw(a.getLinea());
            g2d.setPaint(a.getColor());
            g2d.setPaint(Color.BLACK);
            Polygon flecha = new Polygon();  
            
            center=new Point(a.getDestino().getCoordenadaX()+10, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(center.x, center.y);
            
            p1=new Point(a.getDestino().getCoordenadaX()+10-18,a.getDestino().getCoordenadaY()+10+5);
            p1=this.rotarAlrededor(p1,tita,center);
            flecha.addPoint(p1.x, p1.y);
            
            p2=new Point(a.getDestino().getCoordenadaX()+10-18,a.getDestino().getCoordenadaY()+10-5);
            p2=this.rotarAlrededor(p2, tita, center);
            flecha.addPoint(p2.x, p2.y);
            
            g2d.fillPolygon(flecha);
            
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        dibujarVertices(g2d);
        dibujarAristas(g2d);
    }

	public List<VerticeView> getVertices() {
		return vertices;
	}

	public void setVertices(List<VerticeView> vertices) {
		this.vertices = vertices;
	}

	public List<AristaView> getAristas() {
		return aristas;
	}

	public void setAristas(List<AristaView> aristas) {
		this.aristas = aristas;
	}

	public Dimension getPreferredSize() {
        return new Dimension(1000,600);
    }
        
    public VerticeView buscarVerticeView(Integer id) {
    	for(VerticeView vv: vertices) {
    		if(vv.getId().equals(id)) return vv;
    	}
		return null;
    }
    
    private Point rotarAlrededor (Point p, Double angle, Point center){
        
        Double s = Math.sin(angle);
        Double c = Math.cos(angle);

        Double cx=center.getX();
        Double cy=center.getY();
        
        p.setLocation(p.getX()-cx, p.getY()-cy);
        
        Double xnew = p.getX() * c - p.getY() * s;
        Double ynew = p.getX() * s + p.getY() * c;

        p.setLocation((xnew+cx),(ynew+cy));
        
        return p;
    }

	public ControladorGrafo getController() {
		return grfController;
	}

	public void setController(ControladorGrafo grfController) {
		this.grfController = grfController;
	}

    
    
}
