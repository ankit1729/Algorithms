package com.Queue;


public class Queue<E> {
	private int head = -1;
	private int tail = -1;
	private Object [] elements;
	int size;
	
	public Queue(int capacity) {
		size = capacity;
		elements = new Object [size];
	}
	
	public boolean enQueue(E element){
		// Queue is already full, so returning false
		if(head ==  tail + 1 || (head == -1 && tail == size -1))
			return false;
		
		
		if(tail == size - 1){
			tail = 0;
		}
		else{
			tail ++;
		}
		elements[tail] = element;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public E deQueue(){
		// Queue is already empty
		if(head == tail)
			return null;
		
		if(head == size - 1){
			head = 0;
		}
		else{
			head ++;
		}
		return (E) elements[head];
	}
	
	
	public void traverseQueue(){
		int start = head ;
		int end = tail ;
		for(int i = start ; i <= end; i++){
			System.out.println(elements[i]);
		}
	}
	
	public boolean isEmpty(){
		if(head == tail)
			return true;
		
		return false;
	}
	
}
