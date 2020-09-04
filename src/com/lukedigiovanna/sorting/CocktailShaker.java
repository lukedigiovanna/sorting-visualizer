package com.lukedigiovanna.sorting;

public class CocktailShaker implements Algorithm {

	@Override
	public void sort(Array array) {
		int start = 0;
		int end = array.size();
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = start; i < end - 1; i++) {
				if (array.compare(i+1, i) < 0) {
					array.swap(i + 1, i);
					swapped = true;
				}
			}
			end--;
			for (int i = end; i > start; i--) {
				if (array.compare(i - 1, i) > 0) {
					array.swap(i - 1, i);
					swapped = true;
				}
			}
			start++;
		}
	}

}
