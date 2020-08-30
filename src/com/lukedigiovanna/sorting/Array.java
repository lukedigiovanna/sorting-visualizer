package com.lukedigiovanna.sorting;

import java.util.ArrayList;
import java.util.List;

public class Array {
	private int[] array;
	
	private double delay = 0;
	
	private int arrayAccesses = 0;
	private int comparisons = 0;
	
	public Array(int numOfElements) {
		array = new int[numOfElements];
		for (int i = 0; i < array.length; i++)
			array[i] = i + 1;
	}
	
	public Array(int[] arr) {
		this.array = arr;
	}
	
	public void setDelay(double delay) {
		this.delay = delay;
	}
	
	public int[] get() {
		return this.array;
	}
	
	private List<Integer> mostRecentAccesses = new ArrayList<Integer>();
	
	private void addAccess(int index) {
		mostRecentAccesses.add(0, index);
		if (mostRecentAccesses.size() > 3)
			mostRecentAccesses.remove(3);
	}
	
	public int get(int index) {
		sleep(delay * 0.5); // value array access as less computation
		addAccess(index);
		arrayAccesses++;
		return this.array[index];
	}
	
	public boolean isMostRecentAccess(int index) {
		return mostRecentAccesses.contains(index);
	}
	
	public void set(int index, int value) {
		sleep(delay * 0.5);
		this.array[index] = value;
	}
	
	public int size() {
		return this.array.length;
	}
	
	public boolean isSorted() {
		for (int i = 0; i < array.length-1; i++) 
			if (compare(i,i+1) > 0)
				return false;
		return true;
	}
	
	public void shuffle() {
		for (int i = 0; i < array.length * 2; i++) {
			int i1 =(int)(Math.random() * array.length), i2 = (int)(Math.random() * array.length);
			int temp = array[i1];
			array[i1] = array[i2];
			array[i2] = temp;
		}
	}
	
	public void swap(int index1, int index2) {
		sleep();
		arrayAccesses+=2;
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/*
	 * Returns -1 if index 1 is less. 
	 * Return 0 if they are equal.
	 * Returns 1 if index 1 is greater.
	 */
	public int compare(int index1, int index2) {
		addAccess(index1);
		addAccess(index2);
		return compareNumbers(array[index1], array[index2]);
	}
	
	public int compareNumbers(int num1, int num2) {
		sleep();
		comparisons++;
		if (num1 < num2)
			return -1;
		else if (num1 > num2)
			return 1;
		else return 0;
	}
	
	public int getAccesses() {
		return this.arrayAccesses;
	}
	
	public int getComparisons() {
		return this.comparisons;
	}
	
	public void resetMetrics() {
		this.arrayAccesses = 0;
		this.comparisons = 0;
	}
	
	private void sleep() {
		sleep(delay);
	}
	
	private void sleep(double seconds) {
		long nano = (long)(seconds * 1000000000);
		long start = System.nanoTime();
		while (System.nanoTime() - start < nano);
	}
}
