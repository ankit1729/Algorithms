package com.heaps;

import java.util.ArrayList;
import java.util.List;

public class Heap <E extends Comparable<E>>{
	
	Object[] elements;
    int heapSize;
	// 1 For MaxHeap and 2 for MinHeap
	private int heapType;
	
	public Heap(int heapType){
		checkHeapTypeArgument(heapType);
		elements = new Object[100000];
		//this.heapSize = elements.length;
		this.heapType = heapType;
	}
	
	public Heap(int heapType, E[] elements ){
		checkHeapTypeArgument(heapType);
		if(elements.length > 20)
			this.elements = new Object[elements.length];
		else{
			this.elements = new Object[20];
		}
		this.heapSize = elements.length;
		this.heapType = heapType;
		for(int i = 0; i < elements.length; i++){
			this.elements[i] = elements[i];
		}
		
	}
	
	private void checkHeapTypeArgument(int heapType){
		if(heapType !=1 && heapType != 2){
			throw new IllegalArgumentException("Pass 1 for MaxHeap and 2 for MinHeap");
		}
	}

	/** This is used to maxHeapify a partial Heap that is a heap which does not obey
	 * Max-Heap property only at index passed as argument.
	 * This is O(lg(n)) method as heap is a binary heap and we are recursing either 
	 * in the left half or in the right half
	 * 
	 * @param index
	 */
	//Access  specifier of this method is kept default(package - private) to enable 
	 // unit Testing on this in HeapTest.java class
	@SuppressWarnings("unchecked")
	 void maxHeapify(int index){
		
		int leftIndex = getLeftIndex(index);
		int rightIndex = getRightIndex(index);
		int largestElemInd = index; 
		
		if(leftIndex < this.heapSize && 
				((E)elements[leftIndex]).compareTo((E)elements[largestElemInd]) > 0){
			largestElemInd = leftIndex;
		}
		
		if(rightIndex < this.heapSize &&
				((E)elements[rightIndex]).compareTo((E)elements[largestElemInd]) > 0){
			largestElemInd = rightIndex;
		}
		
		if(largestElemInd != index){
			swap(index,largestElemInd);
			maxHeapify(largestElemInd);
		}
		
	}
	
	/** This is used to minHeapify a partial Heap that is a heap which does not obey
	 * Min-Heap property only at index passed as argument.
	 * This is O(lg(n)) method as heap is a binary heap and we are recursing either 
	 * in the left half or in the right half.
	 * 
	 * @param index 
	 */
	//Access  specifier of this method is kept default(package - private) to enable 
	 // unit Testing on this in HeapTest.java class
	@SuppressWarnings("unchecked")
	 void minHeapify(int index){
		
		int leftIndex = getLeftIndex(index);
		int rightIndex = getRightIndex(index);
		int smallestElemInd = index; 
		
		if(leftIndex < this.heapSize && 
				((E)elements[leftIndex]).compareTo((E)elements[smallestElemInd]) < 0){
			smallestElemInd = leftIndex;
		}
		
		if(rightIndex < this.heapSize &&
				((E)elements[rightIndex]).compareTo((E)elements[smallestElemInd]) < 0){
			smallestElemInd = rightIndex;
		}
		
		if(smallestElemInd != index){
			swap(index,smallestElemInd);
			minHeapify(smallestElemInd);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<E> buildHeap(){
		List<E> elements = new ArrayList<>();
		for(int i = (heapSize/2) -1; i >= 0; i--){
			if(heapType == 1){
				maxHeapify(i);
			}
			else{
				minHeapify(i);
			}
		}
		
		for(int i = 0; i < heapSize; i++){
			elements.add((E)this.elements[i]);
		}
		return elements;
	}
	
	 List<E> getHeap(){
		 List<E> heapAsList = new ArrayList<>();
		 for(int i = 0; i <= heapSize -1; i++){
			heapAsList.add((E) this.elements[i]);
		 }
		 return heapAsList;
	 }
	
	
	
	// Assumes Heap is a Max-Heap and sorts the elements in non decreasing order
	@SuppressWarnings("unchecked")
	public List<E> heapSort(){
		this.heapType = 1;
		buildHeap();
		List<E> elements = new ArrayList<>();
		int origHeapSize = this.heapSize;
		for(int i = heapSize -1; i > 0; i--){
			swap(0,heapSize -1);
			heapSize --;
			maxHeapify(0);
		}
		
		for(int i = 0; i < origHeapSize; i++){
			elements.add((E)this.elements[i]);
		}
		return elements;
	}
	
	public boolean insert(E elem){
		heapSize ++;
		if(this.heapType == 1){
			this.elements[heapSize -1] = Integer.MIN_VALUE;
			return increaseKey(heapSize -1, elem);
		}
		else {
			this.elements[heapSize -1] = Integer.MAX_VALUE;
			return decreaseKey(heapSize -1, elem);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean decreaseKey(int i, E newElem){
		
		if(i >= this.heapSize){
			throw new IllegalArgumentException("Index passed is out of range of current HeapSize");
		}
		else if(newElem.compareTo((E)this.elements[i]) >= 0){
			throw new IllegalArgumentException("New Key is greater than or equal to already Present key");
		}
		
		this.elements[i] = newElem;
		
		// If Heap is Min-Heap we compare with the parent elements and go up
		if(this.heapType == 2){
			int parentInd = getParentIndex(i);
			while(i > 0 && ((E)elements[i]).compareTo((E)this.elements[parentInd]) < 0){
				swap(i, parentInd);
				i = parentInd;
				parentInd = getParentIndex(i);
			}
		}
		
		// If heap is a Max-Heap, we just maxHeapify
		else if(this.heapType == 1){
			maxHeapify(i);
		}
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public boolean increaseKey(int i, E newElem){
		
		if(i >= this.heapSize){
			throw new IllegalArgumentException("Index passed is out of range of current HeapSize");
		}
		else if(newElem.compareTo((E)this.elements[i]) <= 0){
			throw new IllegalArgumentException("New Key is greater than or equal to already Present key");
		}
		
		this.elements[i] = newElem;
		
		// If Heap is Max-Heap we compare with the parent elements and go up
		if(this.heapType == 1){
			int parentInd = getParentIndex(i);
			while(i > 0 && ((E)elements[i]).compareTo((E)this.elements[parentInd]) > 0){
				swap(i, parentInd);
				i = parentInd;
				parentInd = getParentIndex(i);
			}
		}
		
		// If heap is a Min-Heap, we just maxHeapify
		else if(this.heapType == 2){
			minHeapify(i);
		}
		return true;
		
	}
	
	@SuppressWarnings("unchecked")
	public E extractMax(){
		E maxElem = null;
		if(this.heapSize > 0){
			if(this.heapType == 1){
				maxElem = (E)this.elements[0];
				this.elements[0] = this.elements[heapSize -1];
				heapSize --;
				maxHeapify(0);
			}
			// If heap type is Min-Heap, then max element will be in one of the leaves
			else if(this.heapType == 2){
				maxElem = (E)this.elements[heapSize -1];
				int maxElemInd = heapSize -1;
				for(int i = heapSize -1; i >= heapSize/2; i--){
					if(maxElem.compareTo((E)this.elements[i]) < 0){
						maxElem = (E)this.elements[i];
						maxElemInd = i;
					}
				}
				
				// Below code is to replace maxElem found with last element in heap
				// decrease the heapSize and minHeapify and re arrange the heap
				// by checking with parent nodes.
				this.elements[maxElemInd] = this.elements[heapSize -1];
				heapSize --;
				if(maxElemInd != heapSize -1){
					int index = maxElemInd;
					int parentIndex = getParentIndex(maxElemInd);
					while(index > 0 && 
							((E)this.elements[index]).compareTo((E)this.elements[parentIndex]) < 0){
						swap(index,parentIndex);
						index = parentIndex;
						parentIndex = index;
					}
				}
			}
		}
		return maxElem;
	}
	
	@SuppressWarnings("unchecked")
	public E extractMin(){
		E minElem = null;
		if(this.heapSize > 0){
			if(this.heapType == 2){
				minElem = (E)this.elements[0];
				this.elements[0] = this.elements[heapSize -1];
				heapSize --;
				minHeapify(0);
			}
			// If heap type is Max-Heap, then min element will be in one of the leaves
			else if(this.heapType == 1){
				minElem = (E)this.elements[heapSize -1];
				int minElemInd = heapSize -1;
				for(int i = heapSize -1; i >= heapSize/2; i--){
					if(minElem.compareTo((E)this.elements[i]) < 0){
						minElem = (E)this.elements[i];
						minElemInd = i;
					}
				}
				
				// Below code is to replace minElem found with last element in heap
				// decrease the heapSize and maxHeapify
				this.elements[minElemInd] = this.elements[heapSize -1];
				heapSize --;
				if(minElemInd != heapSize -1){
					int index = minElemInd;
					int parentIndex = getParentIndex(minElemInd);
					while(index > 0 && 
							((E)this.elements[index]).compareTo((E)this.elements[parentIndex]) > 0){
						swap(index,parentIndex);
						index = parentIndex;
						parentIndex = index;
					}
				}
			}
		}
		return minElem;
	}
	
	public boolean delete(E elem){
		int elemIndex = findElement(elem);
		if(elemIndex == -1){
			return false;
		}
		else if(elemIndex == -2){
			throw new IllegalArgumentException("Element to be deleted can not be null");
		}
		else{
			return delete(elemIndex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean delete(int elemIndex){
		if(elemIndex > heapSize -1)
			throw new IllegalArgumentException("Index to be deleted is greater than Heap Size");
		
		this.elements[elemIndex] = this.elements[this.heapSize -1];
		heapSize--;
		int parentIndex = getParentIndex(elemIndex);
		if(heapType == 1){
			if(((E)this.elements[elemIndex]).compareTo((E)this.elements[parentIndex]) > 0){
				while(elemIndex > 0 &&((E)this.elements[elemIndex]).compareTo((E)this.elements[parentIndex]) > 0){
					swap(elemIndex,parentIndex);
					elemIndex = parentIndex;
					parentIndex = getParentIndex(elemIndex);
				}
			}
			else{
				maxHeapify(elemIndex);
			}
		}
		else if(heapType == 2){
			if(((E)this.elements[elemIndex]).compareTo((E)this.elements[parentIndex]) < 0){
				while(elemIndex > 0 && ((E)this.elements[elemIndex]).compareTo((E)this.elements[parentIndex]) < 0){
					swap(elemIndex,parentIndex);
					elemIndex = parentIndex;
					parentIndex = getParentIndex(elemIndex);
				}
			}
			else{
				minHeapify(elemIndex);
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private int findElement(E elem){
		if(elem == null){
			return -2;
		}
		for(int i = 0; i <= heapSize -1; i++){
			if(elem.equals(this.elements[i]) && elem.compareTo((E)this.elements[i]) == 0){
				return i;
			}
		}
		return -1;
	}
	
	public int getIndex(E elem){
		if (elem == null)
			throw new IllegalArgumentException("element passed can not be null");
		return findElement(elem);
	}
	
	private void swap(int index, int swapElemInd) {
		Object temp = elements[index];
		elements[index] = elements[swapElemInd];
		elements[swapElemInd] = temp;
	}

	private int getLeftIndex(int index){
		// +1 as elements array is 0-index
		return (2*index) +1;
	}
	
	private int getRightIndex(int index){
		// +1 as elements array is 0-index
		return ((2*index) +1) +1;
	}
	
	private int getParentIndex(int index){
		if(index%2 == 0){
			return (index/2) -1;
		}
		else{
			return index/2;
		}
	}
	
	@SuppressWarnings("unchecked")
	public E getTop(){
		if(this.heapSize > 0){
			return (E)this.elements[0];
		}
		else{
			return null;
		}
	}
	
}
