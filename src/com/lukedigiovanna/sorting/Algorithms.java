package com.lukedigiovanna.sorting;

/**
 * Stores an instance of each algorithm
 *
 */
public class Algorithms {
	public static Algorithm
		SELECTION = new Selection(),
		HEAP = new Heap(),
		INSERTION = new Insertion(),
		SHELL = new Shell(),
		BUBBLE = new Bubble(),
		COCKTAIL_SHAKER = new CocktailShaker(),
		COMB = new Comb(),
		MERGE = new Merge(),
		QUICK_SORT = new QuickSort(),
		COUNT = new CountSort(),
		RADIX_LSD = new RadixLSD(),
		RADIX_MSD = new RadixMSD(),
		BOGO = new BogoSort();
}
