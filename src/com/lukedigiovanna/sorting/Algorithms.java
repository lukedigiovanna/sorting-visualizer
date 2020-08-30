package com.lukedigiovanna.sorting;

/**
 * Stores an instance of each algorithm
 *
 */
public class Algorithms {
	public static Algorithm
		SELECTION = new Selection(),
		INSERTION = new Insertion(),
		MERGE = new Merge(),
		QUICK_SORT = new QuickSort(),
		COUNT = new CountSort(),
		RADIX_LSD = new RadixLSD(),
		RADIX_MSD = new RadixMSD();
}
