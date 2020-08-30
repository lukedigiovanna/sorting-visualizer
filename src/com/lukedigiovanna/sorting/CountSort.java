package com.lukedigiovanna.sorting;

public class CountSort implements Algorithm {
	private int max(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++)
			if (arr[i] > max)
				max = arr[i];
		return max;
	}
	
	public void sort(Array array) {
		int[] index = new int[max(array.get())+1];
		for (int i = 0; i < array.size(); i++)
			index[array.get(i)]++;
		for (int i = 1; i < index.length; i++)
			index[i] += index[i-1];
		
		int[] output = new int[array.size()];
		for (int i = array.size()-1; i >= 0; i--) {
			int num = array.get(i);
			output[index[num]-1] = num;
			index[num]--;
		}
		
		for (int i = 0; i < array.size(); i++)
			array.set(i, output[i]);
	}
}
