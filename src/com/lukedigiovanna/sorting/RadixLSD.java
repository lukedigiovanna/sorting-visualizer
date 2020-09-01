package com.lukedigiovanna.sorting;

public class RadixLSD implements Algorithm {
	
	private int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}
	
	private void countSort(Array array, int exp) {
		int[] index = new int[BASE];
		for (int i = 0; i < array.size(); i++) 
			index[array.get(i)%exp/(exp/BASE)]++;
		for (int i = 1; i < index.length; i++)
			index[i] += index[i-1];
		
		int[] output = new int[array.size()];
		for (int i = array.size() - 1; i >= 0; i--) {
			int num = array.get(i);
			int ind = num%exp/(exp/BASE);
			output[index[ind]-1] = num;
			index[ind]--;
		}
		
		for (int i = 0; i < array.size(); i++)
			array.set(i, output[i]);
	}
	
	private final int BASE = 4;
	
	@Override
	public void sort(Array array) {
		int max = max(array.get());
		
		int exp = BASE;
		while (max > 0) {
			countSort(array, exp);
			max /= BASE;
			exp *= BASE;
		}
	}

}
