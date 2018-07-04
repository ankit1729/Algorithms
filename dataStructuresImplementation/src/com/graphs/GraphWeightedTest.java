package com.graphs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.model.Student;

public class GraphWeightedTest {
	
	static GraphWeighted<Integer, Student> weightedGraph;
	static Map<Student,Map<Student,Integer>> edges;
	static Map<Integer, Student> studentVertexMap;
	
	
	@Test
	public void addEdgeTest(){
		
		//GraphWeighted<Integer, Student> weightedGraph = prepareGraph();
		//System.out.println(this.weightedGraph);
		Map<Integer,Student> vertexMapExpected = expectedVertexMapForAddTest();
		Map<Integer,Student> vertexMapResult = weightedGraph.getAllVerticesInMap();
		MatcherAssert.assertThat(vertexMapResult, CoreMatchers.is(vertexMapExpected));
		
		//Adding Edges
		weightedGraph.addEdge(studentVertexMap.get(1), studentVertexMap.get(2), 4);
		weightedGraph.addEdge(studentVertexMap.get(2), studentVertexMap.get(1), 4);
		weightedGraph.addEdge(studentVertexMap.get(2), studentVertexMap.get(3), 6);
		weightedGraph.addEdge(studentVertexMap.get(3), studentVertexMap.get(2), 6);
		weightedGraph.addEdge(studentVertexMap.get(3), studentVertexMap.get(4), 7);
		weightedGraph.addEdge(studentVertexMap.get(4), studentVertexMap.get(3), 7);
		weightedGraph.addEdge(studentVertexMap.get(4), studentVertexMap.get(2), 1);
		weightedGraph.addEdge(studentVertexMap.get(2), studentVertexMap.get(4), 1);
		weightedGraph.addEdge(studentVertexMap.get(4), studentVertexMap.get(5), 8);
		weightedGraph.addEdge(studentVertexMap.get(5), studentVertexMap.get(4), 8);
		weightedGraph.addEdge(studentVertexMap.get(5), studentVertexMap.get(2), 3);
		weightedGraph.addEdge(studentVertexMap.get(2), studentVertexMap.get(5), 3);
		weightedGraph.addEdge(studentVertexMap.get(1), studentVertexMap.get(5), 9);
		weightedGraph.addEdge(studentVertexMap.get(5), studentVertexMap.get(1), 9);
		
		Iterator<Integer> expectedVertexKeyIterator = vertexMapExpected.keySet().iterator();
		while(expectedVertexKeyIterator.hasNext()){
			Integer vertexKey = expectedVertexKeyIterator.next();
			Map<Student,Integer> neighbours = getNeighboursExpected(vertexKey);
			MatcherAssert.assertThat(weightedGraph.getNeighbouringEdges(vertexMapResult.get(vertexKey)), 
					CoreMatchers.is(neighbours));
		}
		
	}
	
	@Test
	public void removeAllEdgesTest(){
		weightedGraph.removeAllEdges();
		Map<Student,Map<Student,Integer>> edges = weightedGraph.getAllEdges(); 
		MatcherAssert.assertThat(edges.size(), CoreMatchers.is(0));
	}
	
	@Test
	public void addEdgesTest(){
		weightedGraph.removeAllEdges();
		weightedGraph.addEdges(edges);
		Map<Student,Map<Student,Integer>> edgesMap = weightedGraph.getAllEdges();
		MatcherAssert.assertThat(edgesMap, CoreMatchers.is(edges));
		
	}
	
	@BeforeClass
	public static void prepareGraph(){
		
		Student student1 = new Student(1, 90, "ABC");
		Student student2 = new Student(2, 100, "XYZ");
		Student student3 = new Student(3, 95, "PQR");
		Student student4 = new Student(4, 97, "MNO");
		Student student5 = new Student(5, 99, "ZXC");
		
		studentVertexMap = new HashMap<>();
		studentVertexMap.put(student1.getRollNumber(), student1);
		studentVertexMap.put(student2.getRollNumber(), student2);
		studentVertexMap.put(student3.getRollNumber(), student3);
		studentVertexMap.put(student4.getRollNumber(), student4);
		studentVertexMap.put(student5.getRollNumber(), student5);
		
		
		
		weightedGraph = new GraphWeighted<>(studentVertexMap);
		//System.out.println(weightedGraph);
		
		
	}
	
	private static Map<Integer,Student> expectedVertexMapForAddTest(){
		Student student1 = new Student(1, 90, "ABC");
		Student student2 = new Student(2, 100, "XYZ");
		Student student3 = new Student(3, 95, "PQR");
		Student student4 = new Student(4, 97, "MNO");
		Student student5 = new Student(5, 99, "ZXC");
		
		Map<Integer, Student> studentVertexMap = new HashMap<>();
		studentVertexMap.put(student1.getRollNumber(), student1);
		studentVertexMap.put(student2.getRollNumber(), student2);
		studentVertexMap.put(student3.getRollNumber(), student3);
		studentVertexMap.put(student4.getRollNumber(), student4);
		studentVertexMap.put(student5.getRollNumber(), student5);
		
		return studentVertexMap;
	}
	
	private Map<Student,Integer> getNeighboursExpected(Integer studentVertexKey){
		Map<Student,Map<Student,Integer>> edges = GraphWeightedTest.edges;
		Iterator<Student> studentIterator = edges.keySet().iterator();
		
		Map<Student,Integer> neighboringEdges = null;
		while(studentIterator.hasNext()){
			Student student = studentIterator.next();
			if(student.getRollNumber() == studentVertexKey){
				neighboringEdges = edges.get(student);
				break;
			}
		}
		
		return neighboringEdges;
	}
	
	@BeforeClass
	public static void prepareEdgesMap(){
		edges = new HashMap<>();
		
		Map<Integer, Student> studentVertexMap = expectedVertexMapForAddTest();
		
		Iterator<Integer> studentIterator = studentVertexMap.keySet().iterator();
			
		while(studentIterator.hasNext()){
			Integer studentId = studentIterator.next();
			Map<Student,Integer> neighbours = new HashMap<>();
			Student student = studentVertexMap.get(studentId);
			if(studentId.equals(1)){
				neighbours.put(new Student(2, 100, "XYZ"), 4);
				neighbours.put(new Student(5, 99, "ZXC"), 9);
			}
			else if (studentId.equals(2)){
				neighbours.put(new Student(1, 90, "ABC"), 4);
				neighbours.put(new Student(3, 95, "PQR"), 6);
				neighbours.put(new Student(4, 97, "MNO"), 1);
				neighbours.put(new Student(5, 99, "ZXC"), 3);
			}
			else if (studentId.equals(3)){
				neighbours.put(new Student(2, 100, "XYZ"), 6);
				neighbours.put(new Student(4, 97, "MNO"), 7);
			}
			else if (studentId.equals(4)){
				neighbours.put(new Student(3, 95, "PQR"), 7);
				neighbours.put(new Student(2, 100, "XYZ"), 1);
				neighbours.put(new Student(5, 99, "ZXC"), 8);
			}
			else if (studentId.equals(5)){
				neighbours.put(new Student(4, 97, "MNO"), 8);
				neighbours.put(new Student(2, 100, "XYZ"), 3);
				neighbours.put(new Student(1, 90, "ABC"), 9);
			}
			
			edges.put(student, neighbours);
		}
		
		
	}
}
