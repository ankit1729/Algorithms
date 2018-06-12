package com.heaps;

import java.util.ArrayList;
import java.util.List;

public class Heap <E extends Comparable<E>>{
	
	Object[] elements;
	private int heapSize;
	// 1 For MaxHeap and 2 for MinHeap
	private int heapType;
	
	public Heap(int heapType){
		checkHeapTypeArgument(heapType);
		elements = new Object[20];
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
	

}
