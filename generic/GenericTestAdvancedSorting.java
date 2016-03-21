package laboratorium.lista5.generic;

import java.util.ArrayList;
import java.util.List;

import laboratorium.lista5.AdvancedSort;
import laboratorium.lista5.other.MergeSortIterative;
import laboratorium.lista5.other.MergeSortLI;

public class GenericTestAdvancedSorting<T extends Comparable<T>>
{
	private List<T> listOfRandom;

	public GenericTestAdvancedSorting(List<T> list)
	{
		this.listOfRandom = list;
	}

	public double testSorting(AdvancedSort sortMethod)
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = null;
		switch (sortMethod)
		{
			case QUICKSORT:
				sorter = new QuickSort<>();
				break;
			case MERGESORT:
				sorter = new MergeSort<>();
				break;
			case HEAPSORT:
				sorter = new HeapSort<>();
				break;
			case MERGESORTITERATIVE:
				sorter = new MergeSortIterative<>();
				break;
			case MERGESORTLI:
				sorter = new MergeSortLI<>();
				break;
		}
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public List<T> getListOfRandom()
	{
		return listOfRandom;
	}
}
