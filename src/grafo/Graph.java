package grafo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Graph <T> {
	private List<Edge<T>> edges;
	private List<Vertex<T>> vertexs;

	public Graph(){
		this.edges = new ArrayList<Edge<T>>();
		this.vertexs = new ArrayList<Vertex<T>>();
	}

	public Graph<T> addNodo(T nodo){
		 this.addNodo(new Vertex<T>(nodo));
		 return this;
	}

	private void addNodo(Vertex<T> nodo){
		this.vertexs.add(nodo);
	}
	
	public Graph<T> conectar(T n1,T n2,Integer id,Double valor){
		this.connect(getVertex(n1), getVertex(n2), id, valor);
		return this;
	}

	private void connect(Vertex<T> nodo1,Vertex<T> nodo2,Integer id, Double valor){
		this.edges.add(new Edge<T>(nodo1,nodo2,id,valor));
	}
	
	public Vertex<T> getVertex(T valor){
		return this.vertexs.get(this.vertexs.indexOf(new Vertex<T>(valor)));
	}

	public List<T> getNeighbourhood(T valor){ 
		Vertex<T> aNode = this.getVertex(valor);
		List<T> output = new ArrayList<T>();
		for(Edge<T> theEdges : this.edges){
			if( theEdges.getOrigin().equals(aNode)){
				output.add(theEdges.getEnd().getValue());
			}
		}
		return output;
	}
	
	private List<Vertex<T>> getNeighbourhood(Vertex<T> unNodo){ 
		List<Vertex<T>> salida = new ArrayList<Vertex<T>>();
		for(Edge<T> theEdges : this.edges){
			if( theEdges.getOrigin().equals(unNodo)){
				salida.add(theEdges.getEnd());
			}
		}
		return salida;
	}
	
	public void printEdges(){
		System.out.println(this.edges.toString());
	}

	public Integer inputDegree(Vertex<T> vertice){
		Integer res =0;
		for(Edge<T> arista : this.edges){
			if(arista.getEnd().equals(vertice)) ++res;
		}
		return res;
	}

	public Integer outpuDegree(Vertex<T> vertice){
		Integer res =0;
		for(Edge<T> arista : this.edges){
			if(arista.getOrigin().equals(vertice)) ++res;
		}
		return res;
	}
	
    protected List<Edge<T>> findEdge(T v1, T v2){
    	Vertex<T> origen = this.getVertex(v1);
    	Vertex<T> destino = this.getVertex(v2);
    	return findEdge(origen,destino);
    }

    public boolean isAdjacent(T v1, T v2) {
    	return this.isAdjacent(this.getVertex(v1), this.getVertex(v2));
    }
    
    private boolean isAdjacent(Vertex<T> v1,Vertex<T> v2){
    	List<Vertex<T>> ady = this.getNeighbourhood(v1);
        for(Vertex<T> unAdy : ady){
        	if(unAdy.equals(v2)) return true;
        }
        return false;
    }
   
    protected List<Edge<T>> findEdge(Vertex<T> v1, Vertex<T> v2){
    	return edges.stream().filter(e -> e.getOrigin().equals(v1) && e.getEnd().equals(v2)).collect(Collectors.toList());
    }

    public List<Edge<T>> caminoDijkstra(T v1, T v2) {
    	List<Edge<T>> salida = new ArrayList<Edge<T>>();
    	
    	Vertex<T> origen = getVertex(v1);
    	Vertex<T> destino = getVertex(v2);
    	
    	Map<Vertex<T>,Double> costo = new LinkedHashMap<Vertex<T>,Double>();
    	PriorityQueue<Vertex<T>> monticulo = new PriorityQueue<Vertex<T>>(10,(ve1,ve2) -> costo.get(ve1).compareTo(costo.get(ve2)));
    	List<Vertex<T>> visitados = new ArrayList<Vertex<T>>();
    	
    	costo.put(origen, 0.0); //Lo inicializo en cero
    	monticulo.offer(origen);
    	
    	for(Vertex<T> v : vertexs) {
    		if(!v.equals(origen)) {
    			costo.put(v, Double.MAX_VALUE); //Lo inicializo en infinito
    		}
    	}
    	
    	while(!monticulo.isEmpty()) {
    		Vertex<T> actual = monticulo.poll();
    		visitados.add(actual);
    		
    		List<Vertex<T>> adyacentes = getNeighbourhood(actual);
    		@SuppressWarnings("unused")
			List<Edge<T>> arcosSalientes = edges.stream().filter(ed -> ed.getOrigin().equals(actual)).collect(Collectors.toList());
    		
    		for(Vertex<T> ady : adyacentes) {
    			if(!visitados.contains(ady)) {
    				monticulo.offer(ady);
    				
    				Edge<T> arco = findEdge(actual, ady).stream().min((ed1,ed2) -> ed1.getValue().compareTo(ed2.getValue())).get();
    				Double costoNuevo = costo.get(actual)+arco.getValue().doubleValue();
    				
    				if(costoNuevo < costo.get(ady)) costo.replace(ady, costoNuevo); 
    			}
    		}
    	}
    	
    	Double costoMinDestino = costo.get(destino);
    	
    	if(costoMinDestino.equals(Double.MAX_VALUE)) return salida;
    	
    	List<Vertex<T>> camino = new ArrayList<Vertex<T>>();
    	
    	buscarCamino(origen,destino,costoMinDestino,camino);
    	
    	for(int i = camino.size()-1;i>0;i--) {
    		Edge<T> arco = findEdge(camino.get(i), camino.get(i-1)).stream().min((ed1,ed2) -> ed1.getValue().compareTo(ed2.getValue())).get();
    		salida.add(arco);
    	}
    	
    	return salida;
    }
    
    private Boolean buscarCamino(Vertex<T> origen, Vertex<T> destino, Double costoMax, List<Vertex<T>> salida){
    	if(costoMax < 0) return false;
    	
    	if(origen.equals(destino)) {
    		salida.add(destino);
    		return true;
    	}
    	
    	for(Vertex<T> ady : getNeighbourhood(origen)) {
    		Edge<T> arco = findEdge(origen, ady).stream().min((ed1,ed2) -> ed1.getValue().compareTo(ed2.getValue())).get();
    		Boolean rtdo = buscarCamino(ady,destino,costoMax-arco.getValue(),salida);
    		if(rtdo) {
    			salida.add(origen);
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<List<Vertex<T>>> caminos(T v1,T v2){
    	return this.caminos(new Vertex(v1), new Vertex(v2));
    }
    
    public List<List<Vertex<T>>> caminos(Vertex<T> v1,Vertex<T> v2){
    	List<List<Vertex<T>>> salida = new ArrayList<List<Vertex<T>>>();
    	List<Vertex<T>> marcados = new ArrayList<Vertex<T>>();
    	marcados.add(v1);
    	buscarCaminosAux(v1,v2,marcados,salida);
    	return salida;
   }
    
    private void buscarCaminosAux(Vertex<T> v1,Vertex<T> v2, List<Vertex<T>> marcados, List<List<Vertex<T>>> todos) {
    	List<Vertex<T>> adyacentes = getNeighbourhood(v1);
    	List<Vertex<T>> copiaMarcados = null;
    	
    	for(Vertex<T> ady: adyacentes){
    		copiaMarcados = marcados.stream().collect(Collectors.toList());
    		
    		if(ady.equals(v2)) {
    			copiaMarcados.add(v2);
    			todos.add(new ArrayList<Vertex<T>>(copiaMarcados));
    		} else {
    			if(!copiaMarcados.contains(ady)) {
    					copiaMarcados.add(ady);
    						this.buscarCaminosAux(ady,v2,copiaMarcados,todos);
    			}
    		}
    	}
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public List<List<Edge<T>>> caminosRutas(T v1, T v2){
    	return this.caminosRutas(new Vertex(v1), new Vertex(v2));
    }
    
    public List<List<Edge<T>>> caminosRutas(Vertex<T> v1, Vertex<T> v2){
    	List<List<Edge<T>>> salida =  new ArrayList<List<Edge<T>>>();
    	List<Edge<T>> marcados = new ArrayList<Edge<T>>();
    	
    	buscarCaminosRutasAux(v1,v2,marcados,salida);
    	return salida;
    }
    
    private void buscarCaminosRutasAux(Vertex<T> v1, Vertex<T> v2,List<Edge<T>> marcados, List<List<Edge<T>>> todos) {
    	List<Edge<T>> salientes = edges.stream().filter(r -> r.getOrigin().equals(v1)).collect(Collectors.toList());
    	List<Edge<T>> copiaMarcados = null;
    	
    	for(Edge<T> ady : salientes) {
    		copiaMarcados = marcados.stream().collect(Collectors.toList());
    		
    		if(ady.getEnd().equals(v2)) {
    			copiaMarcados.add(ady);
    			todos.add(new ArrayList<Edge<T>>(copiaMarcados));
    		}
    		else {
    			if(!copiaMarcados.contains(ady)) {
    				copiaMarcados.add(ady);
    				buscarCaminosRutasAux(ady.getEnd(),v2,copiaMarcados,todos);
    			}
    		}
    	}
    }
    
}
