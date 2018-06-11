package com.linkedList;


public class LinkedList<E> {

	private Node<E> head;
	private Node<E> tail;
	//private Node<E> current;
	
	static class Node<E>{
		E item;
		Node<E> next;
		Node<E> previous;
		
		public Node(E item) {
			this.item = item;
		}
	}
	
	public void add(E item){
		Node<E> node = new Node<>(item);
		node.next = this.head;
		if(this.head != null)
			this.head.previous = node;
		this.head = node;
		if(tail == null){
			this.tail = node;
		}
		//current = head;
	}
	
	public E getHead(){
		if(this.head != null)
			return head.item;
		return null;
	}
	
	public E get(int index){
		Node<E> x = head;
		int i =0;
		while(i != index){
			x = x.next;
			i++;
		}
		return x.item;
	}
	
	public void addAtLast(E item){
		Node<E> node = new Node<>(item);
		node.previous = this.tail;
		if(this.tail != null)
			this.tail.next = node;
		this.tail = node;
		if(head == null){
			this.head = node;
		}
		//current = head;
	}
	
	public E getNext(E elem){
		if(elem == null)
			return null;
		Node<E> start = this.head;
		while(start != null){
			if(start.item.equals(elem) && start.next != null){
				return start.next.item;
			}
			start = start.next;
		}
		return null;
	}
	
}
