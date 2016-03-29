package laboratorium.lista4.other;

import java.util.ArrayList;
import java.util.List;

import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;
import laboratorium.lista4.generic.BubbleSort;
import laboratorium.lista4.generic.InsertionSort;
import laboratorium.lista4.generic.ListSorter;
import laboratorium.lista4.generic.SelectionSort;
import laboratorium.lista4.generic.ShellSort;

public class GenericTestSortingOld<T extends Comparable<? super T>>
{
	private List<T> listOfRandom;

	public GenericTestSortingOld(List<T> list)
	{
		this.listOfRandom = list;
	}

	public double bubble()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new BubbleSort<>();
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public double shellTokuda()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new ShellSort<>(GapSequence.TOKUDA);
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public double shellShell()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new ShellSort<>(GapSequence.SHELL);
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public double shellHibbard()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new ShellSort<>(GapSequence.HIBBARD);
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public double shellKnuth()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new ShellSort<>(GapSequence.KNUTH);
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public double insert()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new InsertionSort<>();
		long start = System.nanoTime();
		sorter.sort(list);
		long end = System.nanoTime();
		return (end - start) * 0.000001;
	}

	public double select()
	{
		List<T> list = new ArrayList<>(listOfRandom);
		ListSorter<T> sorter = new SelectionSort<>();
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
