# Sorting Visualizer and Audibilizer

You must have Java downloaded for this application to run

Download: [jar file](https://lukedigi.com/projects/sorting/Sorting%20Visualizer.jar)

## What's sorting?
Sorting is a common problem while programming, especially when working with data and can be necessary for efficient searching algorithms (such as binary search). When we sort, we arrange a list of comparable type from least to greatest. Comparable data includes not only numbers but possibly other data (perhaps alphabetical order of words). There are many algorithms we can use to sort lists of data. For smaller sets of a data, very crude approaches are generally ok to implement. These algorithms include selection and insertion sort and make many comparisons between elements of the data, which cause the algorithm to take a long time to run, especially for larger sets of data.

## Efficiency
There is a notation, called Big O notation, which indicates about how efficient an algorithm is for a problem size, n. For example, the Big O notation of selection sort is O(n²). This means that the algorithm has to make about n² comparisons for a data set with size n. As n gets larger, this approaches infinity fairly quick, which makes this algorithm become very inefficient. Fortunately, there are much more efficient sorting algorithms that we can use.

Here is the Big O notation for a few sorting algorithms
![algorithm efficiencies](https://i.ibb.co/mqwhSjX/sorting-complexitychart.png)

The algorithm to use for a particular problem that requires sorting often depends on a few factors such as what we can access about the list of data (e.g. how can we compare the elements?), the initial state of the data (e.g. is it already mostly in ascending order?, what are we sorting? numbers? words? etc.), and how much data we need to sort. For most cases, though, using an efficient algorithm such as Quick Sort is good enough. There are other methods of sorting that don't require any comparisons such as count sort and radix sort. However, these require that the data we are sorting are purely whole numbers and they often require the use of a lot of memory.

## This Program
This program visualizes several sorting algorithms using different visualization techniques such as a bar chart or color circle. These algorithms are slowed down by orders of magnitude to show the individual array accesses and number comparisons, but are much quicker when used practically. When a bar turns red, that means it has been recently accessed or compared by the algorithm. The algorithms are also "audibilized", meaning that each time an element is accessed or compared, a pitch is played corresponding to that elements value (high value - high pitch and vice versa). We can get some pretty cool looking and sounding algorithms out of this!

### Heap Sort
[![head sort]()](https://i.ibb.co/Qc97MvK/sorting1.gif)

### 
