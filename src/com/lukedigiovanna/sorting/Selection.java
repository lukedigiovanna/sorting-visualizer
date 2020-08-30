package com.lukedigiovanna.sorting;

public class Selection implements Algorithm {
	
	public void sort(Array array) {
		for (int i = 0; i < array.size() - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < array.size(); j++) 
				if (array.compare(j, minIndex) < 0)
					minIndex = j;
			array.swap(i, minIndex);
		}
	}
}
