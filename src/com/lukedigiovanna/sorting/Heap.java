package com.lukedigiovanna.sorting;

public class Heap implements Algorithm {
	private void heapify(Array array, int n, int i) {
		int largest = i;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		
		if (l < n && array.compare(l, largest) > 0)
			largest = l;
		
		if (r < n && array.compare(r, largest) > 0)
			largest = r;
		
		if (largest != i) {
			array.swap(i, largest);
			heapify(array, n, largest);
		}
	}
	
	public void sort(Array array) {
		int n = array.size();
		
		for (int i = n/2 - 1; i >= 0; i--) 
			heapify(array, n, i);
		
		for (int i = n - 1; i > 0; i--) {
			array.swap(0, i);
			
			heapify(array, i, 0);
		}
	}
}
