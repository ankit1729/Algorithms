package com.model;


public class Student implements Comparable<Student>{
	
	private Integer rollNumber;
	private double marks;
	private String name;
	
	public Student(int rollNumber, double marks, String name){
		this.rollNumber = rollNumber;
		this.marks = marks;
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "Student info : roll num : "+rollNumber+ " Name : "+name+" Marks : "+marks;
	}
	
	public int getRollNumber() {
		return rollNumber;
	}
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	public double getMarks() {
		return marks;
	}
	public void setMarks(double marks) {
		this.marks = marks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		Student student = null;
		if(obj instanceof Student)
			student = (Student)obj;
		else{
			throw new IllegalArgumentException("Type Student cannot be comapred with any other type");
		}
		
		return this.rollNumber == student.rollNumber ? true : false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}

	@Override
	public int compareTo(Student o) {
		return this.rollNumber.compareTo(o.rollNumber);
	}
		
}
