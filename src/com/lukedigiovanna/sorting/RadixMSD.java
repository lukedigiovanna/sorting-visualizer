package com.lukedigiovanna.sorting;

public class RadixMSD implements Algorithm {
	
	private static int base = 2;
	
	private int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}
	
	private int digit(int number, int place) {
		return number%place/(place/base);
	}
	
	private void countSort(Array array, int i1, int i2, int exp) {
		int[] index = new int[base];
		for (int i = i1; i < i2; i++) 
			index[digit(array.get(i), exp)]++;
		for (int i = 1; i < index.length; i++)
			index[i] += index[i-1];
		
		int[] output = new int[i2 - i1];
		for (int i = i2 - 1; i >= i1; i--) {
			int num = array.get(i);
			int ind = digit(num, exp);
			output[index[ind]-1] = num;
			index[ind]--;
		}
		
		System.out.println(output.length); 
		
		for (int i = i1; i < i2; i++)
			array.set(i, output[i - i1]);
	}
	
	@Override
	public void sort(Array array) {
		int max = max(array.get());
	
		int exp = 1;
		while (max > 0) {
			exp *= base;
			max /= base;
		} // find the greatest exponential of base
		
		System.out.println(exp);
		
		while (exp >= base) {
			int range = exp;
			for (int i1 = 0; i1 < array.size(); i1 += range) {
				System.out.println(i1 + " -> "+Math.min(i1 + range, array.size()));
				countSort(array, i1, Math.min(i1 + range, array.size()), exp);
			}
			exp /= base;
		}
	}

}
