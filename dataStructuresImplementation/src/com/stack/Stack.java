package com.stack;

public class Stack <E>{
	
	Object [] elements;
	int top = -1;
	
	public Stack() {
		elements = new Object[20];
	}
	
	public boolean push(E element){
		if(top == this.elements.length -1){
			throw new IllegalStateException("Stack is full. Overflow");
		}
		else if (element == null){
			throw new IllegalArgumentException("Null value cannote be pushed to the Stack");
		}
		
		this.elements[++top] = element;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public E pop(){
		if(top == -1){
			throw new IllegalStateException("Stack is empty. UnderFlow");
		}
		
		E returnElem = (E) this.elements[top];
		top --;
		return returnElem;
	}
	
	public E getTopElements(){
		if(top == -1){
			throw new IllegalStateException("Stack is empty");
		}
		return (E) this.elements[top];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
