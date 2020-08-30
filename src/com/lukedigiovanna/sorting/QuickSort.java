package com.lukedigiovanna.sorting;

public class QuickSort implements Algorithm {
	private int partition(Array array, int low, int high) {
		int i = low;
		
		for (int j = low; j < high; j++) {
			if (array.compare(j, high) < 0) {
				array.swap(i, j);
				i++;
			}
		}
		
		array.swap(i, high);
		return i;
	}
	
	@Override
	public void sort(Array array) {
		quickSort(array, 0, array.size() - 1);
	}
	
	private void quickSort(Array array, int low, int high) {
		if (low < high) {
			int pInd = partition(array, low, high);
			
			quickSort(array, low, pInd - 1);
			quickSort(array, pInd + 1, high);
		}
	}
}
