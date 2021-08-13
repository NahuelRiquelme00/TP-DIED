package interfaces.grafico;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import entidades.Estacion;
import entidades.LineaDeTransporte;
import entidades.Trayecto;

public class ControladorGrafo {

	private GrafoPanel vistaGrafo;
	
	public ControladorGrafo(GrafoPanel panelGrf, List<Estacion> estaciones,List<Trayecto> rutas) {
		
		this.vistaGrafo = panelGrf;
		this.vistaGrafo.setController(this);

		Integer r=200,xc,yc;
        xc=panelGrf.getWidth()/2;
        yc=panelGrf.getHeight()/2;
        
        xc=500;
        yc=300;
        
        Double ang =2*Math.PI/(double)estaciones.size();
        Color color=new Color(0,0,0);
        
        Map<Estacion,LineaDeTransporte> utilizadas = rutas.stream().collect(Collectors.toMap(Trayecto::getOrigen,Trayecto::getLinea));
        utilizadas.put(rutas.get(rutas.size()-1).getDestino(), rutas.get(rutas.size()-1).getLinea());
        		
        //CREA Y DIBUJA LOS VERTICES
        for(int i=0;i<estaciones.size();i++) {
        	if(utilizadas.containsKey(estaciones.get(i))) {
        		color = utilizadas.get(estaciones.get(i)).getColorGrafo();
        	}else color = Color.BLACK;
            crearVertice(((Double)(xc+Math.cos(i*ang)*r)).intValue(),((Double)(yc+Math.sin(i*ang)*r)).intValue(),color,estaciones.get(i));
        }        
       
        
        for(Trayecto arista : rutas) {        	
        	AristaView auxiliar = new AristaView();
        	auxiliar.setOrigen(vistaGrafo.buscarVerticeView(arista.getOrigen().getId()));
        	auxiliar.setDestino(vistaGrafo.buscarVerticeView(arista.getDestino().getId()));        	
        	pintarArista(auxiliar);
        }
        
        
	}

	public void crearVertice(Integer coordenadaX, Integer coordenadaY, Color color, Estacion mc) {		
		VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
		v.setId(mc.getId());
		v.setNombre(mc.getNombre());
		this.vistaGrafo.agregar(v);
		this.vistaGrafo.repaint();
	}
	
	public void crearArista(AristaView arista) {		
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}
	
	public void pintarArista(AristaView arista) {
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}
}
