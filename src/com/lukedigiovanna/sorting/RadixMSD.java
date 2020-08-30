package com.lukedigiovanna.sorting;

public class RadixMSD implements Algorithm {
	
	private int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}
	
	private void countSort(Array array, int i1, int i2, int exp) {
		int[] index = new int[10];
		for (int i = i1; i < i2; i++) 
			index[array.get(i)%exp/(exp/10)]++;
		for (int i = 1; i < index.length; i++)
			index[i] += index[i-1];
		
		int[] output = new int[i2 - i1];
		for (int i = array.size() - 1; i >= 0; i--) {
			int num = array.get(i);
			int ind = num%exp/(exp/10);
			output[index[ind]-1] = num;
			index[ind]--;
		}
		
		for (int i = i1; i < i2; i++)
			array.set(i, output[i]);
	}
	
	@Override
	public void sort(Array array) {
		int max = max(array.get());
		
		int exp = 1;
		while (max > 0) {
			exp *= 10;
			max /= 10;
		}
		
		while (exp >= 10) {
			int range = exp/10;
			for (int i1 = 0; i1 < array.size(); i1 += range) {
				countSort(array, i1, i1 + range, exp);
			}
			exp /= 10;
		}
	}

}
