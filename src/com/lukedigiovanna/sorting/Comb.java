package com.lukedigiovanna.sorting;

public class Comb implements Algorithm {
	private int nextGap(int gap) {
		gap = (gap * 10)/13;
		if (gap < 1)
			gap = 1;
		return gap;
	}
	
	@Override
	public void sort(Array array) {
		boolean swapped = true;
		int gap = array.size();
		while (gap != 1 || swapped) {
			swapped = false;
			gap = nextGap(gap);
			for (int i = 0; i < array.size()-gap; i++) {
				if (array.compare(i+gap, i) < 0) {
					array.swap(i + gap, i);
					swapped = true;
				}
			}
		}
	}
}
