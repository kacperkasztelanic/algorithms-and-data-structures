package laboratorium.lista4.generic;

import java.util.ArrayList;
import java.util.List;

import laboratorium.lista4.BasicSort;
import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;

public class GenericTestBasicSorting<T extends Comparable<? super T>>
{
	private List<T> listOfRandom;

	public GenericTestBasicSorting()
	{
	}

	public double testSorting(BasicSort sortMethod)
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
				sorter = new ShellSort<>(GapSequence.HIBBARD);
				break;
			case SHELLKNUTH:
				sorter = new ShellSort<>(GapSequence.KNUTH);
				break;
			case SHELLTOKUDA:
				sorter = new ShellSort<>(GapSequence.TOKUDA);
				break;
			case SHELLSHELL:
				sorter = new ShellSort<>(GapSequence.SHELL);
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

	public void setListOfRandom(List<T> listOfRandom)
	{
		this.listOfRandom = listOfRandom;
	}
}
