package interfaces.grafico;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class AristaView {

    private VerticeView origen;
    private VerticeView destino;
    private Shape linea;
    private Stroke formatoLinea;
    private Paint color;

    public AristaView() {
    }

    public Paint getColor() {
        return origen.getColorBase();
    }

    public void setColor(Paint color) {
        this.color = color;
    }
 
    public boolean pertenece(Point2D p) {
        return this.linea.contains(p);
    }

    public Shape getLinea() {
        if(this.linea==null)  this.linea = new Line2D.Double(origen.getCoordenadaX() + 10, origen.getCoordenadaY() + 10, destino.getCoordenadaX() + 10, destino.getCoordenadaY() + 10);
        return linea;
    }

    public void setLinea(Shape linea) {
        this.linea = linea;
    }

    public Stroke getFormatoLinea() {
        if(this.formatoLinea==null)  this.formatoLinea = new BasicStroke(5.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);       
        return formatoLinea;
    }

    public void setFormatoLinea(Stroke formatoLinea) {
        this.formatoLinea = formatoLinea;
    }

    public VerticeView getOrigen() {
        return origen;
    }

    public void setOrigen(VerticeView origen) {
        this.origen = origen;
    }

    public VerticeView getDestino() {
        return destino;
    }

    public void setDestino(VerticeView destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "Arista{" + "origen=" + origen + ", destino=" + destino + ", linea=" + linea + ", formatoLinea=" + formatoLinea + ", gradiente=" + color + '}';
    }
}
