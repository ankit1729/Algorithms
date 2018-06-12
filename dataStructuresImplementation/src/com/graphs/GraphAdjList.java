package com.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.Queue.Queue;
import com.linkedList.LinkedList;
import com.model.Student;

public class GraphAdjList<E>{
	
	private List<Vertex<E>> vertexList;
	private LinkedList<Vertex<E>> adj [];
	private E root;
	
	
	private static class Vertex<E>{
		
		private E value;
		String color = "white";
		Vertex<E> parent;
		Integer distanceFromSource;
		int discoveryTime;
		int finishTime;
		
		public Vertex(E value) {
			
			this.value = value;
		}
		
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public boolean equals(Object obj){
			Vertex<E> vertex = (Vertex<E>) obj;
			return (this.value.equals(vertex.value)) ? true :false;
		}
		
		@Override 
		public int hashCode(){
			return value.hashCode();
		}
		 
		@Override
		public String toString(){
			return this.value.toString();
		}
	}	
	
	public GraphAdjList(List<E> vertexList) {
		
		this.vertexList = new ArrayList<>();
		// Initializing the vertices of the map
		Iterator<E> vertexListIterator = vertexList.iterator();
		while(vertexListIterator.hasNext()){
			E vertexValue = vertexListIterator.next();
			Vertex<E> vertex = new Vertex<E>(vertexValue);
			this.vertexList.add(vertex);
		}
		
		// After initializing the vertices, populate the adjacency List of vertices
		adj = new LinkedList[this.vertexList.size()];
		
		for(int i = 0; i < adj.length; i++){
			adj[i] =  new LinkedList<Vertex<E>>();
			adj[i].add(this.vertexList.get(i));
		}
	}
	
	
	/** Adding an edge from source to destination. Also from destination to Source,
	 *  as we are assuming that this is an undirected graph. 
	 *  This is an O(V) time complexity method as we iterate over the List of vertices
	 *  to get the specific source and destination vertices from the arguments 
	 *  passed to the API by calling 
	 *  private method of the class ("getVertexFromElement(E element)")
	 *  
	 *  @param source source vertex's item  
	 *  @param destination destination vertex's item
	 *  @param weight weight of the edge
	 *  @return boolean true of false based on edge is added or not
	**/
	public boolean addEdge(E source, E destination){
		
		Vertex<E> sourceVertex = null;
		Vertex<E> destVertex = null;
		
		sourceVertex = getVertexFromElement(source);
		destVertex = getVertexFromElement(destination);
		/*if(this.vertexList.contains(getVertexFromElement(destination)))
			destVertex = new Vertex<>(destination);*/
		
		// If condition to check both source and destination elements passed to the API
		// are contained in the vertex list of the graph
		if(sourceVertex == null || destVertex == null)
			return false;
		else{
			int sourceInd  = vertexList.indexOf(sourceVertex);
			int destInd = vertexList.indexOf(destVertex);
			//destVertex.weight = weight;
			
			LinkedList<Vertex<E>> sourceLinkedList = adj[sourceInd];
			sourceLinkedList.addAtLast(destVertex);
			LinkedList<Vertex<E>> destLinkedList = adj[destInd];
			destLinkedList.addAtLast(sourceVertex);
		}
		
		return true;
	}
	
	
	public GraphAdjList<E> bfsTraversal(E source){
		
		// Initialize all the vertices 
		for(Vertex<E> vertex : vertexList){
			vertex.color = "white";
			vertex.parent = null;
			vertex.distanceFromSource = Integer.MAX_VALUE;
		}
		
		// Initialize sourceVertex
		Vertex<E> sourceVertex = getVertexFromElement(source);
		sourceVertex.color = "grey";
		sourceVertex.parent = null;
		sourceVertex.distanceFromSource = 0;
		
		List<E> bfsTreeVertexList = new ArrayList<>();
		
		
		Queue<Vertex<E>> queue = new Queue<>(25);
		queue.enQueue(sourceVertex);
		Map<Vertex<E>, List<Vertex<E>>> neighboursMap = new HashMap<>();
		while(!queue.isEmpty()){
			Vertex<E> vertex = queue.deQueue();
			bfsTreeVertexList.add(vertex.value);
			int vertexInd = this.vertexList.indexOf(vertex);
			LinkedList<Vertex<E>> vertexLinkedList = adj[vertexInd];
			Vertex<E> vertexNeighbour = vertexLinkedList.getNext(vertex);
			List<Vertex<E>> neighbours ;
			while(vertexNeighbour != null){
				if(vertexNeighbour.color.equals("white")){
					vertexNeighbour.color = "grey";
					vertexNeighbour.parent = vertex;
					vertexNeighbour.distanceFromSource = vertex.distanceFromSource + 1; 
					queue.enQueue(vertexNeighbour);	
					neighbours = neighboursMap.get(vertex);
					if(neighbours == null)
						neighbours = new ArrayList<Vertex<E>>();
					neighbours.add(vertexNeighbour);
					neighboursMap.put(vertex, neighbours);
				}
				vertexNeighbour = vertexLinkedList.getNext(vertexNeighbour);
			}
			vertex.color = "black";
		}
		
		System.out.println(neighboursMap);
		
		GraphAdjList<E> bfsTree = new GraphAdjList<>(bfsTreeVertexList);
		bfsTree.root = sourceVertex.value; 
		Iterator<Vertex<E>> bfsTreeVerticesIterator = neighboursMap.keySet().iterator();
		while(bfsTreeVerticesIterator.hasNext()){
			Vertex<E> bfsTreeSourceVertex = bfsTreeVerticesIterator.next(); 
			List<Vertex<E>> neighbours = neighboursMap.get(bfsTreeSourceVertex);
			Iterator<Vertex<E>> neighboursIterator = neighbours.iterator();
			while(neighboursIterator.hasNext()){
				Vertex<E> bfsTreeDestVertex = neighboursIterator.next();
				bfsTree.addEdge(bfsTreeSourceVertex.value, bfsTreeDestVertex.value);
			}
		}
		
		return bfsTree;
	}

	
	private Vertex<E> getVertexFromElement(E element){
		Iterator<Vertex<E>> vertexListIterator = vertexList.iterator();
		while(vertexListIterator.hasNext()){
			Vertex<E> vertex = vertexListIterator.next();
			if(vertex.value.equals((element))){
				return vertex;
			}
		}
		return null;	
	}
	
	public List<E> getNeighbours(E sourceElem){
		List<E> neighbours = new ArrayList<>();
		Vertex<E> sourceVertex = getVertexFromElement(sourceElem);
		int i = this.vertexList.indexOf(sourceVertex);
		LinkedList<Vertex<E>> neighboursLinkedList = adj[i];
		
		
		return neighbours;
	}
	
	
	public static void main(String[] args) {
		
		Student student1 = new Student(1, 90, "R");
		Student student2 = new Student(2, 91, "S");
		Student student3 = new Student(3, 92, "T");
		Student student4 = new Student(4, 93, "U");
		Student student5 = new Student(5, 94, "V");
		Student student6 = new Student(6, 95, "W");
		Student student7 = new Student(7, 96, "X");
		Student student8 = new Student(8, 97, "Y");
		List<Student> studentList = new ArrayList<>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);
		studentList.add(student5);
		studentList.add(student6);
		studentList.add(student7);
		studentList.add(student8);		
		
		GraphAdjList<Student> studentGraph = new GraphAdjList<>(studentList);
		studentGraph.addEdge(student1, student2);
		studentGraph.addEdge(student1, student5);
		studentGraph.addEdge(student2, student6);
		studentGraph.addEdge(student3, student4);
		studentGraph.addEdge(student3, student6);
		studentGraph.addEdge(student3, student7);
		studentGraph.addEdge(student4, student7);
		studentGraph.addEdge(student4, student8);
		studentGraph.addEdge(student6, student7);
		studentGraph.addEdge(student7, student8);
		
		GraphAdjList<Student> bfsTree =studentGraph.bfsTraversal(student2);
		
	}
}
