package com.lukedigiovanna.sorting;

public class Merge implements Algorithm {
	public void sort(Array array) {
		sort(array, 0, array.size() - 1);
	}
	
	public void sort(Array array, int startIndex, int endIndex) {
		if (startIndex < endIndex) {
			int middleIndex = (startIndex + endIndex)/2;
			sort(array, startIndex, middleIndex);
			sort(array, middleIndex + 1, endIndex);
			
			merge(array, startIndex, middleIndex, endIndex);
		}
	}
	
	private void merge(Array array, int startIndex, int middleIndex, int endIndex) {
		int len1 = middleIndex - startIndex + 1; //add 1 because it includes the middle index
		int len2 = endIndex - middleIndex;
		
		int[] leftBuffer = new int[len1];
		int[] rightBuffer = new int[len2];
		
		for (int i = 0; i < len1; i++) 
			leftBuffer[i] = array.get(startIndex + i);
		for (int i = 0; i < len2; i++)
			rightBuffer[i] = array.get(middleIndex + 1 + i);
	
		int i = 0, j = 0;
		int k = startIndex;
		while (i < len1 && j < len2) {
			if (array.compareNumbers(leftBuffer[i], rightBuffer[j]) < 0) {
				array.set(k, leftBuffer[i]);
				i++;
			} else {
				array.set(k, rightBuffer[j]);
				j++;
			}
			k++;
		}
		
		while (i < len1) {
			array.set(k, leftBuffer[i]);
			k++;
			i++;
		}
		
		while (j < len2) {
			array.set(k, rightBuffer[j]);
			k++;
			j++;
		}
	}
}
