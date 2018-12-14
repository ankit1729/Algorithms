package com.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.Queue.Queue;

/**
 * The GraphWeighted represents the weighted graph which can be both directed as well as undirected
 * The user has to pass edges in both the directions in case of undirected graph.
 * In case of directed graph, the user should pass edges in single direction from 
 * source vertex to destination vertex
 * 
 * 
 * @author ankchopr
 *
 * @param <K> key of the vertex
 * @param <V> value of the vertex
 */
public class GraphWeighted<K extends Comparable<K>,V> {
	
	HashMap<K,Vertex<K,V>> vertexListMap;
	// Hash Map containing Edges in the form of mapping between a vertex with 
	// hashMap of its connected vertices which are mapped further with Integer values
	// denoting weight of that particular edge
	private Map<Vertex<K,V>, Map<Vertex<K,V>, Integer>> edges;
	private Integer time;
	
	private boolean isDirected;
	
	
	private static class Vertex<K,V> {
		
		private K key;
		private V value;
		private Vertex<K,V> parent;
		private Integer distanceFromSource;
		private String color;
		private Integer discoveryTime;
		private Integer finsishTime;
		
		
		
		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public Vertex<K,V> getParent() {
			return parent;
		}
		public void setParent(Vertex<K,V> parent) {
			this.parent = parent;
		}
		public Integer getDistanceFromSource() {
			return distanceFromSource;
		}
		public void setDistanceFromSource(Integer distanceFromSource) {
			this.distanceFromSource = distanceFromSource;
		}
		
		@Override
		public boolean equals(Object obj){
			if(this == obj){
				return true;
			}
			else if(obj == null || getClass() != obj.getClass()){
				return false;
			}
			
			@SuppressWarnings("unchecked")
			Vertex<K,V> vertex = (Vertex<K,V>) obj;
			return this.value.equals(vertex.value) && this.key.equals(vertex.key);
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(this.value);
		}
		
		public String toString(){
			return value.toString();
		}
		
	}
	
	public GraphWeighted(boolean isDirected) {
		vertexListMap = new HashMap<>();
		edges = new HashMap<>();
		this.isDirected = isDirected;
	}
	
	public GraphWeighted(Map<K, V> vertexMap, boolean isDirected){
		Iterator<K> vertexKeyIterator = vertexMap.keySet().iterator();
		this.vertexListMap = new HashMap<>();
		this.edges = new HashMap<>();
		this.isDirected = isDirected;
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			V value = vertexMap.get(key);
			
			if(key == null || value == null){
				throw new IllegalArgumentException("Null key or Null value not accepted in Graph");
			}
			
			Vertex<K,V> vertex = new Vertex<>();
			vertex.setKey(key);
			vertex.setValue(value);
			this.vertexListMap.put(key,vertex);
		}
	}
	
	
	/**
	 * 
	 * @param edges A map of sourceVertex as key and 
	 * 				Map of destination Vertices along with their edge weights as Value
	 * @return
	 */
	public void addEdges(Map<V,Map<V,Integer>> edges){
		
		Iterator<V> sourceVertexIterator = edges.keySet().iterator();
		while(sourceVertexIterator.hasNext()){
			V sourceVertexValue = sourceVertexIterator.next();
			Vertex<K,V> vertex = getVertex(sourceVertexValue);
			if(vertex == null){
				throw new IllegalArgumentException(sourceVertexValue.toString()+" "
												+ "is not a part of vertex list of this Graph");
			}
			Map<V,Integer> connectedVerticesValueMap = edges.get(sourceVertexValue);
			Map<Vertex<K,V>,Integer> connectedVerticesMap = null;
			Iterator<V> connectedVerticesIterator = connectedVerticesValueMap.keySet().iterator();
			while(connectedVerticesIterator.hasNext()){
				V connectedVertexValue = connectedVerticesIterator.next();
				Vertex<K,V> connectedVertex = getVertex(connectedVertexValue);
				if(connectedVertex == null){
					throw new IllegalArgumentException(connectedVertexValue.toString()+" "
							+ "is not a part of vertex list of this Graph");
				}
				Integer edgeWeight = connectedVerticesValueMap.get(connectedVertexValue);
				
				if(connectedVerticesMap == null)
					connectedVerticesMap = new HashMap<>();
				connectedVerticesMap.put(connectedVertex, edgeWeight);
			}
			this.edges.put(vertex, connectedVerticesMap);
 		}
		
		// Putting the edges in the reverse direction for undirected Graph 
		if(!isDirected){
			Iterator<Vertex<K,V>> vertexIterator = this.edges.keySet().iterator();
			while(vertexIterator.hasNext()){
				Vertex<K,V> vertex = vertexIterator.next();
				Map<Vertex<K,V>, Integer> neighboursMap = this.edges.get(vertex);
				Iterator<Vertex<K,V>> neighborMapIterator = neighboursMap.keySet().iterator();
				while(neighborMapIterator.hasNext()){
					boolean reverseVertexAddedToEdgeMap = false;
					Vertex<K,V> neighbour = neighborMapIterator.next();
					Map<Vertex<K,V>,Integer> neighbourVertexEdgeMap = this.edges.get(neighbour);
					if(neighbourVertexEdgeMap == null){
						reverseVertexAddedToEdgeMap = true;
						neighbourVertexEdgeMap = new HashMap<>();
					}
					neighbourVertexEdgeMap.put(vertex, neighboursMap.get(neighbour));
					if(reverseVertexAddedToEdgeMap){
						this.edges.put(neighbour,neighbourVertexEdgeMap);
					}
				}
			}
		}
		
	}
	
	public void removeAllEdges(){
		Iterator<Vertex<K,V>> edgeSetIterator = this.edges.keySet().iterator();
		while(edgeSetIterator.hasNext()){
			Vertex<K,V> sourceVertex = edgeSetIterator.next();
			edgeSetIterator.remove();
		}
	}
	
	public Map<V,Map<V,Integer>> getAllEdges(){
		Map<V,Map<V,Integer>> edgeMap = null;
		if(this.edges != null){
			edgeMap = new HashMap<>();
			Iterator<Vertex<K,V>> edgesKeyIterator = this.edges.keySet().iterator();
			while(edgesKeyIterator.hasNext()){
				Map<V,Integer> neighbours = new HashMap<>();
				Vertex<K,V> vertex = edgesKeyIterator.next();
				Map<Vertex<K,V>,Integer> neighbourVertices = this.edges.get(vertex);
				Iterator<Vertex<K,V>> neighbourVerticesIterator = neighbourVertices.keySet().iterator();
				while(neighbourVerticesIterator.hasNext()){
					Vertex<K,V> neighbourVertex = neighbourVerticesIterator.next();
					Integer edgeWeight = neighbourVertices.get(neighbourVertex);
					neighbours.put(neighbourVertex.value, edgeWeight);
				}
				edgeMap.put(vertex.value, neighbours);
			}
		}
		return edgeMap;
	}
	
	
	/**
	 * 
	 * @param sourceVertex
	 * @param destinationVertex
	 * @param weight
	 * @return
	 */
	public boolean addEdge(V sourceVertex, V destinationVertex, Integer weight){
		Vertex<K,V> source = getVertex(sourceVertex);
		Vertex<K,V> dest = getVertex(destinationVertex);
		if(source == null || dest == null)
			return false;
		
		/*if(!(vertexListMap.containsValue(source) && vertexListMap.containsValue(dest)))
			return false;*/
		
		Map<Vertex<K,V>,Integer> neighbourVerticesMap = edges.get(source);
		if(neighbourVerticesMap == null){
			neighbourVerticesMap = new HashMap<>();
		}
		
		// This will put the edge with the new weight, irrespective of the fact that edge might
		// already be present with some other weight
		neighbourVerticesMap.put(dest, weight);
		this.edges.put(source, neighbourVerticesMap);
		
		// If graph is undirected, then add the edge in opposite direction also
		if(!this.isDirected){
			Map<Vertex<K,V>,Integer> destNeighbourVerticesMap = edges.get(dest);
			if(destNeighbourVerticesMap == null){
				destNeighbourVerticesMap = new HashMap<>();
			}
			
			// This will put the edge with the new weight, irrespective of the fact that edge might
			// already be present with some other weight
			destNeighbourVerticesMap.put(source, weight);
			this.edges.put(dest, destNeighbourVerticesMap);
		}
		return true;
	}
	
	private Vertex<K,V> getVertex(V vertexValue){
		Iterator<K> vertexKeyIterator = this.vertexListMap.keySet().iterator();
		
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			Vertex<K,V> vertex = vertexListMap.get(key);
			if(vertex.value == vertexValue || vertex.value.equals(vertexValue)){
				return vertex;
			}
		}
		return null;
	
	}
	
	
	
	/**
	 * This method adds a new vertex to the Graph.
	 * If key passed to this method is already present in the Graph, then the new value is replaced
	 * in the existing vertex with the same key and new Vertex is not created in the graph.
	 * If both key and value are already present in the graph, then this method return false.
	 * 
	 * @param key The key associated with the vertex
	 * @param value The value associated with the vertex
	 * @return whether new vertex is added in Graph or not
	 */
	public boolean addVertex(K key, V value){
		if(this.vertexListMap.containsKey(key)){
			Vertex<K,V> vertex = this.vertexListMap.get(key);
			// this condition is to check if key-value pair passed to the graph is already
			// present in Graph
			if(vertex.getValue() == value || vertex.getValue().equals(value))
				return false;
			// new value updated to existing vertex in Graph
			vertex.setValue(value);
			return true;
		}
		Vertex<K,V> vertex = new Vertex<>();
		vertex.setValue(value);
		vertex.setKey(key);
		this.vertexListMap.put(key, vertex);
		return true;
	}
	
	public List<V> getAllVerticesList(){
		List<V> vertexList = null;
		Iterator<K> vertexKeyIterator = this.vertexListMap.keySet().iterator();
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			Vertex<K,V> vertex = this.vertexListMap.get(key);
			
			if(vertexList == null){
				vertexList = new ArrayList<>();
			}
			
			vertexList.add(vertex.value);
		}
		
		return vertexList;
	}
	
	public Map<K, V> getAllVerticesInMap(){
		Map<K,V> vertexMap = null;
		Iterator<K> vertexKeyIterator = this.vertexListMap.keySet().iterator();
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			Vertex<K,V> vertex = this.vertexListMap.get(key);
			
			if(vertexMap == null){
				vertexMap = new HashMap<>();
			}
			
			vertexMap.put(key, vertex.value);
		}
		return vertexMap;
	}
	
	public Map<V,Integer> getNeighbouringEdges(V vertexValue){
		Map<V,Integer> neighbouringVertices = null;
		Vertex<K,V> vertex = getVertex(vertexValue);
		if(vertex != null){
			Map<Vertex<K,V>,Integer> neighbours = this.edges.get(vertex);
			Iterator<Vertex<K,V>> neighbourVertexIterator = neighbours.keySet().iterator();
			while(neighbourVertexIterator.hasNext()){
				Vertex<K,V> neighbour = neighbourVertexIterator.next();
				Integer weight = neighbours.get(neighbour);
				if(neighbouringVertices == null){
					neighbouringVertices = new HashMap<>();
				}
				neighbouringVertices.put(neighbour.value, weight);
			}
		}
		return neighbouringVertices;
	}
	
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		if(vertexListMap != null){
			buffer.append("Vertices are : \n");
			Iterator<K> vertexKeyIterator = this.vertexListMap.keySet().iterator();
			while(vertexKeyIterator.hasNext()){
				K key = vertexKeyIterator.next();
				Vertex<K,V> vertex = this.vertexListMap.get(key);
				buffer.append(vertex.toString()+"\n");
			}
		}
		
		if(this.edges != null){
			Iterator<Vertex<K,V>> edgesKeyIterator = this.edges.keySet().iterator();
			while(edgesKeyIterator.hasNext()){
				Vertex<K,V> vertex = edgesKeyIterator.next();
				buffer.append("Edges connected to vertex : "+vertex+" are : \n");
				Map<Vertex<K,V>,Integer> neighboursMap = this.edges.get(vertex);
				Iterator<Vertex<K,V>> neighbourVertexIterator = neighboursMap.keySet().iterator();
				while(neighbourVertexIterator.hasNext()){
					Vertex<K,V> neighbourVertex = neighbourVertexIterator.next();
					Integer edgeWeight = neighboursMap.get(neighbourVertex);
					buffer.append(neighbourVertex+" having edge Weight : "+ edgeWeight+"\n");
				}
			}
		}
		
		return buffer.toString();
	}
	
	public GraphWeighted<K, V> breadthFirstSearch(V source){
		Vertex<K,V> sourceVertex = null;
		GraphWeighted<K,V> breadthFirstTree = null;
		if(source != null)
			sourceVertex = getVertex(source);
		if(sourceVertex != null){
			breadthFirstTree = new GraphWeighted<>(this.isDirected);
			
			@SuppressWarnings("unchecked")
			//Map<K,Vertex<K,V>> vertexListMap = (Map<K, Vertex<K,V>>) this.vertexListMap.clone();
			Map<K,Vertex<K,V>> vertexListMap = this.vertexListMap;
			Iterator<K> vertexListMapIterator = vertexListMap.keySet().iterator();
			while(vertexListMapIterator.hasNext()){
				K key = vertexListMapIterator.next();
				Vertex<K,V> vertex = vertexListMap.get(key);
				if(vertex.equals(sourceVertex)){
					breadthFirstTree.addVertex(key, vertex.value);
					continue;
				}
				vertex.distanceFromSource = Integer.MAX_VALUE;
				vertex.parent = null;
				vertex.color = "white";
			}
			sourceVertex.parent = null;
			sourceVertex.color = "grey";
			sourceVertex.distanceFromSource = 0;
			
			Queue<Vertex<K,V>> queue = new Queue<>(20);
			queue.enQueue(sourceVertex);
			while(!queue.isEmpty()){
				Vertex<K,V> vertex = queue.deQueue();
				Map<Vertex<K,V>,Integer> neighbourEdges = this.edges.get(vertex);
				Iterator<Vertex<K,V>> neighbourVerticesIterator = neighbourEdges.keySet().iterator();
				while(neighbourVerticesIterator.hasNext()){
					Vertex<K,V> neighbour = neighbourVerticesIterator.next();
					Integer edgeWeight = neighbourEdges.get(neighbour);
					if(neighbour.color.equals("white")){
						neighbour.color = "grey";
						neighbour.parent = vertex;
						neighbour.distanceFromSource = vertex.distanceFromSource + 1;
						queue.enQueue(neighbour);
						breadthFirstTree.addVertex(neighbour.key, neighbour.value);
						breadthFirstTree.addEdge(vertex.value, neighbour.value, edgeWeight);
					}
				}
				vertex.color = "black";
			}
		}
		return breadthFirstTree;
	}
	
	public List<GraphWeighted<K, V>> depthFirstSearch(){
		
		List<GraphWeighted<K, V>> defthFirstForest = new ArrayList<>();
		//@SuppressWarnings("unchecked")
		//HashMap<K,Vertex<K,V>> vertexListMap = (HashMap<K, Vertex<K, V>>) this.vertexListMap.clone();
		HashMap<K,Vertex<K,V>> vertexListMap = this.vertexListMap;
		Iterator<K> vertexKeyIterator = vertexListMap.keySet().iterator();
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			Vertex<K, V> vertex = vertexListMap.get(key);
			vertex.color = "white";
			vertex.parent = null;
			vertex.distanceFromSource = Integer.MAX_VALUE;
		}
		this.time = 0;
		vertexKeyIterator = vertexListMap.keySet().iterator();
		while(vertexKeyIterator.hasNext()){
			GraphWeighted<K, V> depthFirstTree = new GraphWeighted<>(this.isDirected);
			K key = vertexKeyIterator.next();
			Vertex<K, V> vertex = vertexListMap.get(key);
			depthFirstTree.addVertex(key, vertex.value);
			if(vertex.color.equals("white")){
				dFSVisit(vertex,depthFirstTree);
				defthFirstForest.add(depthFirstTree);
			}
		}
		
		return defthFirstForest;
	}
	
	private void dFSVisit(Vertex<K, V> vertex,GraphWeighted<K, V> depthFirstTree){
		
		vertex.color = "grey";
		this.time = this.time + 1;
		vertex.discoveryTime = this.time;
		
		Map<Vertex<K, V>,Integer> neighbouringEdges = this.edges.get(vertex);
		Iterator<Vertex<K, V>> neighbourVertexIterator = neighbouringEdges.keySet().iterator();
		while(neighbourVertexIterator.hasNext()){
			Vertex<K, V> neighbour = neighbourVertexIterator.next();
			Integer edgeWeight = neighbouringEdges.get(neighbour);
			if(neighbour.color.equals("white")){
				neighbour.parent = vertex;
				depthFirstTree.addVertex(neighbour.key, neighbour.value);
				depthFirstTree.addEdge(vertex.value, neighbour.value, edgeWeight);
				dFSVisit(neighbour,depthFirstTree);
			}
		}
		vertex.color = "black";
		this.time = this.time + 1;
		vertex.finsishTime = this.time;
		
		//return depthFirstTree;
	}
	
}
