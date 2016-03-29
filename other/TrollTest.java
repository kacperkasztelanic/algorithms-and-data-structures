package laboratorium.lista5.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import laboratorium.lista5.AdvancedSort;
import laboratorium.lista5.generic.HeapSort;
import laboratorium.lista5.generic.ListSorter;
import laboratorium.lista5.generic.QuickSort;

public class TrollTest
{
	private static int n = 10;
	private static int boundary = 100;

	public static void trollSort(AdvancedSort method)
	{
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(random.nextInt(boundary));
		List<Integer> copy = new ArrayList<>(list);
		ListSorter<Integer> sorter = null;
		switch (method)
		{
			case HEAPSORT:
			{
				System.out.println("HeapSort");
				sorter = new HeapSort<>();
				break;
			}
			case MERGESORT:
			{
				System.out.println("MergeSort");
				sorter = new MergeSortLI<>();
				break;
			}
			case QUICKSORT:
			{
				System.out.println("QuickSort");
				sorter = new QuickSort<>();
				break;
			}
			case MERGESORTITERATIVE:
			{
				System.out.println("MergeSortIterative");
				sorter = new MergeSortIterative<>();
				break;
			}
			case MERGESORTLI:
			{
				System.out.println("MergeSortLI");
				sorter = new MergeSortLI<>();
				break;
			}
			case MERGESORTJAVA:
			{
				System.out.println("MergeSortJava");
				sorter = new MergeSortJava<>();
				break;
			}
			case COLLECTIONS:
			{
				System.out.println("Collections");
				System.out.println(list);
				Collections.sort(list);
				System.out.println(list);
				System.out.println(true);
				return;
			}
		}
		System.out.println(list.toString());
		list = sorter.sort(list);
		Collections.sort(copy);
		System.out.println(list.toString());
		System.out.println(copy.equals(list));
	}

	public static void main(String[] args)
	{
		for (AdvancedSort method : AdvancedSort.values())
			trollSort(method);
	}
}