package com.lukedigiovanna.sorting;

public class Bubble implements Algorithm {
	@Override
	public void sort(Array array) {
//		boolean isSorted = true;
//		for (int i = 0; i < array.size()-1; i++) {
//			if (array.compare(i+1, i) < 0) {
//				array.swap(i + 1, i);
//				isSorted = false;
//			}
//		}
//		if (!isSorted)
//			sort(array);
		for (int end = array.size() - 1; end > 0; end--) {
			for (int i = 0; i < end; i++) {
				if (array.compare(i+1, i) < 0) {
					array.swap(i + 1, i);
				}
			}
		}
	}
}
