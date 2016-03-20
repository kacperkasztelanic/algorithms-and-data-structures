package laboratorium.lista4.generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;
import laboratorium.lista4.Sort;

public class GenericTestSorting<T extends Comparable<T>>
{
	private List<T> listOfRandom;

	public GenericTestSorting(List<T> list)
	{
		this.listOfRandom = list;
	}

	public double testSorting(Sort sortMethod)
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = null;
		switch (sortMethod)
		{
			case BUBBLESORT:
				sorter = new BubbleSort<>();
				break;
			case INSERTIONSORT:
				sorter = new InsertionSort<>();
				break;
			case SELECTIONSORT:
				sorter = new SelectionSort<>();
				break;
			case SHELLHIBBARD:
				sorter = new ShellSort<>(GapSequence.HIBBARD, list.size());
				break;
			case SHELLKNUTH:
				sorter = new ShellSort<>(GapSequence.KNUTH, list.size());
				break;
			case SHELLTOKUDA:
				sorter = new ShellSort<>(GapSequence.TOKUDA, list.size());
				break;
			case SHELLSHELL:
				sorter = new ShellSort<>(GapSequence.SHELL, list.size());
				break;
			case SHELLPRATT:
				sorter = new ShellSort<>(GapSequence.PRATT, list.size());
				break;
			case COLLECTIONS:
			{
				long start = System.nanoTime();
				Collections.sort(list);
				long end = System.nanoTime();
				return (end - start) * 0.000001;
			}
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
