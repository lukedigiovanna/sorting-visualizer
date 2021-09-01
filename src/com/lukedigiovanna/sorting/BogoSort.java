package com.lukedigiovanna.sorting;

public class BogoSort implements Algorithm {
	@Override
	public void sort(Array array) {
		while (!array.isSorted()) {
			int ind0 = (int)(Math.random() * array.size());
			int ind1 = (int)(Math.random() * array.size());
			array.swap(ind0, ind1);
		}
	}
}
