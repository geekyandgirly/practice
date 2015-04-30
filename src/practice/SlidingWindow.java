package practice;

import java.util.Arrays;

public class SlidingWindow {
	int[] data;
	int start;
	int end;
	
	private MaxHeap maxHeap;
	
	public SlidingWindow(int[] data, int n, int k) {
		if (data == null || data.length == 0 || n > data.length || k > n) {
			throw new IllegalStateException("invalid input");
		}
		this.data = data;
		start = 0;
		end = n-1;
		
		int[] window = Arrays.copyOfRange(data, start, end);
		QuickSort qs = new QuickSort(window, k);
		qs.quickSortToK(0, n-1);
		
		for (int i = 0; i< window.length; i++) {
  		System.out.print(window[i]);
		}
		maxHeap = new MaxHeap(Arrays.copyOfRange(window, 0, k-1));
	}
	
	public void move() {
		start ++;
		end ++;
		if (end == data.length) {
			return;
		}
		int oldNum = data[start];
		int newNum = data[end];
		
		maxHeap.remove(oldNum);
		
		if (newNum <= maxHeap.getMax()) {
			maxHeap.insert(newNum);
		} else {
			
		}
		
	}
	
	private class MaxHeap {
		private int[] heap;
		
		public MaxHeap(int[] data) {
			heap = data;
			heapify(0);
		}
		
		public int getMax() {
			return heap[0];
		}
		
		public void insert(int element) {
			
		}
		
		public void remove(int element) {
			
		}
		
		public void heapify(int i) {			
			int left = i*2 + 1;
			int right = i*2 +2;
			
			if (left < heap.length) {
				heapify(left);
			}
		  if (right < heap.length) {
			  heapify(right);
		  }

			int largest = i;
			if (heap[left] > heap[i]) {
				largest = left;
			}
			
			if (heap[right] > heap[largest]) {
				largest = right;
			}
			
			if (largest != i) {
				int temp = heap[i];
				heap[i] = heap[largest];
				heap[largest] = heap[i];
			}
		}
	}

}
