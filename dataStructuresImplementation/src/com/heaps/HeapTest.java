package com.heaps;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.heaps.Heap;
import com.model.Student;

public class HeapTest {

	@BeforeClass
    public static void runOnceBeforeClass() {
        System.out.println("@BeforeClass - runOnceBeforeClass");
    }

    // Run once, e.g close connection, cleanup
    @AfterClass
    public static void runOnceAfterClass() {
        System.out.println("@AfterClass - runOnceAfterClass");
    }

    // Should rename to @BeforeTestMethod
    // e.g. Creating an similar object and share for all @Test
    @Before
    public void runBeforeTestMethod() {
        System.out.println("@Before - runBeforeTestMethod");
    }

    // Should rename to @AfterTestMethod
    @After
    public void runAfterTestMethod() {
        System.out.println("@After - runAfterTestMethod");
    }

    @Test
    public void maxHeapifyTest() {
        System.out.println("@Test - MaxHeapify");
        Student [] students = maxHeapifyTestData();
        Heap<Student> maxHeap = new Heap<>(1, students);
        maxHeap.maxHeapify(1);
        for(int i = 0; i < students.length; i++){
        	System.out.println(maxHeap.elements[i]);
        }
    }

    @Test
    public void minHeapifyTest() {
        System.out.println("@Test - MinHeapify");
        Student [] students = minHeapifyTestData();
        Heap<Student> minHeap = new Heap<>(2,students);
        minHeap.minHeapify(1);
        for(int i = 0; i < students.length; i++){
        	System.out.println(minHeap.elements[i]);
        }
    }
    
    private Student[] maxHeapifyTestData(){
    
    	Student [] students = new Student[11];
    	students[0] = new Student(11,90,"ABC");
    	students[1] = new Student(4,91,"ABC1");
    	students[2] = new Student(9,92,"ABC2");
    	students[3] = new Student(6,93,"ABC3");
    	students[4] = new Student(10,94,"ABC4");
    	students[5] = new Student(5,95,"ABC5");
    	students[6] = new Student(3,96,"ABC6");
    	students[7] = new Student(2,97,"ABC7");
    	students[8] = new Student(1,98,"ABC8");
    	students[9] = new Student(8,99,"ABC9");
    	students[10] = new Student(7,100,"ABC10");
    
    	return students;
    }
    
    private Student[] minHeapifyTestData(){
    	Student [] students = new Student[11];
    	students[0] = new Student(1,90,"ABC");
    	students[1] = new Student(9,91,"ABC1");
    	students[2] = new Student(2,92,"ABC2");
    	students[3] = new Student(7,93,"ABC3");
    	students[4] = new Student(3,94,"ABC4");
    	students[5] = new Student(6,95,"ABC5");
    	students[6] = new Student(5,96,"ABC6");
    	students[7] = new Student(8,97,"ABC7");
    	students[8] = new Student(10,98,"ABC8");
    	students[9] = new Student(11,99,"ABC9");
    	students[10] = new Student(4,100,"ABC10");
    
    	return students;
    }
}
