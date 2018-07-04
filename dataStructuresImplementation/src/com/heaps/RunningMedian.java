package com.heaps;


import java.util.*;

public class RunningMedian {

	LinkedHashMap<Integer, Integer> map;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];
        Heap<Integer> maxHeap = new Heap<>(1);
        Heap<Integer> minHeap = new Heap<>(2);
        for (int i = 0; i < n; i++) {
            double median = 0.0;
            int aItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            a[i] = aItem;
            if(i == 0){
                maxHeap.insert(a[i]);
            }
            else if(i == 1){
                int maxHeapTop = maxHeap.getTop();
                if(a[i] > maxHeapTop){
                    minHeap.insert(a[i]);
                }
                else{
                    minHeap.insert(maxHeap.extractMax());
                    maxHeap.insert(a[i]);
                }
            }
            else{
                if(a[i] >= maxHeap.getTop()){
                    minHeap.insert(a[i]);
                }
                else if(a[i] < maxHeap.getTop()){
                    maxHeap.insert(a[i]);
                }
                
                if(maxHeap.heapSize - minHeap.heapSize > 1){
                       minHeap.insert(maxHeap.extractMax());
                }
                else if (minHeap.heapSize - maxHeap.heapSize > 1){
                    maxHeap.insert(minHeap.extractMin());
                }
            }
            
            if(maxHeap.heapSize > minHeap.heapSize){
                median = maxHeap.getTop();
            }
            else if (minHeap.heapSize > maxHeap.heapSize){
                median = minHeap.getTop();
            }
            else{
                median = (maxHeap.getTop() + minHeap.getTop())/2.0;
            }
            System.out.println(median);
        }

        scanner.close();
    }
}


