package com.lukedigiovanna.sorting;

public class Shell implements Algorithm {

	@Override
	public void sort(Array array) {
		for (int gap = array.size()/2; gap > 0; gap/=2) {
			for (int i = gap; i < array.size(); i++) {
				int temp = array.get(i);
				
				int j;
				for (j = i; j >= gap && array.get(j-gap) > temp; j -= gap)
					array.set(j, array.get(j-gap));
				
				array.set(j, temp);
			}
		}
	}
	
}
