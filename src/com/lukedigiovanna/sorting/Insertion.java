package com.lukedigiovanna.sorting;

public class Insertion implements Algorithm {
	public void sort(Array array) {
		for (int i = 1; i < array.size(); i++) {
			for (int j = i; j > 0; j--) {
				if (array.compare(j, j-1) < 0) {
					array.swap(j, j-1);
				} else {
					break;
				}
			}
		}
	}
}
