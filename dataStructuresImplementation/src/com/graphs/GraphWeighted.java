package com.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
	
	Map<K,Vertex<V>> vertexListMap;
	
	// Hash Map containing Edges in the form of mapping between a vertex with 
	// hashMap of its connected vertices which are mapped further with Integer values
	// denoting weight of that particular edge
	private Map<Vertex<V>, Map<Vertex<V>, Integer>> edges;
	
	
	private static class Vertex<V> {
		
		private V value;
		private Vertex<V> parent;
		private Integer distanceFromSource;
		
		
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public Vertex<V> getParent() {
			return parent;
		}
		public void setParent(Vertex<V> parent) {
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
			Vertex<V> vertex = (Vertex<V>) obj;
			return this.value.equals(vertex.value);
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(this.value);
		}
		
		public String toString(){
			return value.toString();
		}
		
	}
	
	public GraphWeighted() {
		vertexListMap = new HashMap<>();
		edges = new HashMap<>();
	}
	
	public GraphWeighted(Map<K, V> vertexMap){
		Iterator<K> vertexKeyIterator = vertexMap.keySet().iterator();
		this.vertexListMap = new HashMap<>();
		this.edges = new HashMap<>();
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			V value = vertexMap.get(key);
			
			if(key == null || value == null){
				throw new IllegalArgumentException("Null key or Null value not accepted in Graph");
			}
			
			Vertex<V> vertex = new Vertex<>();
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
			Vertex<V> vertex = getVertex(sourceVertexValue);
			if(vertex == null){
				throw new IllegalArgumentException(sourceVertexValue.toString()+" "
												+ "is not a part of vertex list of this Graph");
			}
			Map<V,Integer> connectedVerticesValueMap = edges.get(sourceVertexValue);
			Map<Vertex<V>,Integer> connectedVerticesMap = null;
			Iterator<V> connectedVerticesIterator = connectedVerticesValueMap.keySet().iterator();
			while(connectedVerticesIterator.hasNext()){
				V connectedVertexValue = connectedVerticesIterator.next();
				Vertex<V> connectedVertex = getVertex(connectedVertexValue);
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
		
	}
	
	public void removeAllEdges(){
		Iterator<Vertex<V>> edgeSetIterator = this.edges.keySet().iterator();
		while(edgeSetIterator.hasNext()){
			Vertex<V> sourceVertex = edgeSetIterator.next();
			edgeSetIterator.remove();
		}
	}
	
	public Map<V,Map<V,Integer>> getAllEdges(){
		Map<V,Map<V,Integer>> edgeMap = null;
		if(this.edges != null){
			edgeMap = new HashMap<>();
			Iterator<Vertex<V>> edgesKeyIterator = this.edges.keySet().iterator();
			while(edgesKeyIterator.hasNext()){
				Map<V,Integer> neighbours = new HashMap<>();
				Vertex<V> vertex = edgesKeyIterator.next();
				Map<Vertex<V>,Integer> neighbourVertices = this.edges.get(vertex);
				Iterator<Vertex<V>> neighbourVerticesIterator = neighbourVertices.keySet().iterator();
				while(neighbourVerticesIterator.hasNext()){
					Vertex<V> neighbourVertex = neighbourVerticesIterator.next();
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
		Vertex<V> source = getVertex(sourceVertex);
		Vertex<V> dest = getVertex(destinationVertex);
		if(source == null || dest == null)
			return false;
		
		/*if(!(vertexListMap.containsValue(source) && vertexListMap.containsValue(dest)))
			return false;*/
		
		Map<Vertex<V>,Integer> neighbourVerticesMap = edges.get(source);
		if(neighbourVerticesMap == null){
			neighbourVerticesMap = new HashMap<>();
		}
		neighbourVerticesMap.put(dest, weight);
		this.edges.put(source, neighbourVerticesMap);
		return true;
	}
	
	private Vertex<V> getVertex(V vertexValue){
		Iterator<K> vertexKeyIterator = this.vertexListMap.keySet().iterator();
		
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			Vertex<V> vertex = vertexListMap.get(key);
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
			Vertex<V> vertex = this.vertexListMap.get(key);
			// this condition is to check if key-value pair passed to the graph is already
			// present in Graph
			if(vertex.getValue() == value || vertex.getValue().equals(value))
				return false;
			// new value updated to existing vertex in Graph
			vertex.setValue(value);
			return true;
		}
		Vertex<V> vertex = new Vertex<>();
		vertex.setValue(value);
		this.vertexListMap.put(key, vertex);
		return true;
	}
	
	public List<V> getAllVerticesList(){
		List<V> vertexList = null;
		Iterator<K> vertexKeyIterator = this.vertexListMap.keySet().iterator();
		while(vertexKeyIterator.hasNext()){
			K key = vertexKeyIterator.next();
			Vertex<V> vertex = this.vertexListMap.get(key);
			
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
			Vertex<V> vertex = this.vertexListMap.get(key);
			
			if(vertexMap == null){
				vertexMap = new HashMap<>();
			}
			
			vertexMap.put(key, vertex.value);
		}
		return vertexMap;
	}
	
	public Map<V,Integer> getNeighbouringEdges(V vertexValue){
		Map<V,Integer> neighbouringVertices = null;
		Vertex<V> vertex = getVertex(vertexValue);
		if(vertex != null){
			Map<Vertex<V>,Integer> neighbours = this.edges.get(vertex);
			Iterator<Vertex<V>> neighbourVertexIterator = neighbours.keySet().iterator();
			while(neighbourVertexIterator.hasNext()){
				Vertex<V> neighbour = neighbourVertexIterator.next();
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
				Vertex<V> vertex = this.vertexListMap.get(key);
				buffer.append(vertex.toString()+"\n");
			}
		}
		
		if(this.edges != null){
			Iterator<Vertex<V>> edgesKeyIterator = this.edges.keySet().iterator();
			while(edgesKeyIterator.hasNext()){
				Vertex<V> vertex = edgesKeyIterator.next();
				buffer.append("Edges connected to vertex : "+vertex+" are : \n");
				Map<Vertex<V>,Integer> neighboursMap = this.edges.get(vertex);
				Iterator<Vertex<V>> neighbourVertexIterator = neighboursMap.keySet().iterator();
				while(neighbourVertexIterator.hasNext()){
					Vertex<V> neighbourVertex = neighbourVertexIterator.next();
					Integer edgeWeight = neighboursMap.get(neighbourVertex);
					buffer.append(neighbourVertex+" having edge Weight : "+ edgeWeight+"\n");
				}
			}
		}
		
		return buffer.toString();
	}
	
	
	
	public static void main(String[] args) {
		Map<Integer,Integer> map = new HashMap<>();
		map.put(1, 10);
		map.put(2, 20);
		map.put(3, 30);
		map.put(4, 20);
	}
}
