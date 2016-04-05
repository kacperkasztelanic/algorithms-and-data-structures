package laboratorium.lista5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import laboratorium.lista5.generic.GenericTestAdvancedSorting;
import laboratorium.lista5.generic.HeapSort;
import laboratorium.lista5.generic.ListSorter;
import laboratorium.lista5.generic.MergeSort;
import laboratorium.lista5.generic.MergeSortJava;
import laboratorium.lista5.generic.QuickSort;

public class Test
{
	public enum Mode
	{
		RANDOM("Random"), ORDERED("Ordered"), REVERSED("Reversed"), PARTIALLY_ORDERED("Partially Ordered");
		private String mode;

		private Mode(String mode)
		{
			this.mode = mode;
		}

		public String toString()
		{
			return mode;
		}
	}

	private List<Vehicle> listOfRandom;
	private int numberOfElements;
	private int partiallyOrderedNumberOfSections;
	private GenericTestAdvancedSorting<Vehicle> sorting;

	public Test()
	{
		this(1000);
	}

	public Test(int n)
	{
		this(n, 4);
	}

	public Test(int n, int sections)
	{
		this.numberOfElements = n;
		this.partiallyOrderedNumberOfSections = sections;
		listOfRandom = new ArrayList<>();
		sorting = new GenericTestAdvancedSorting<>();
	}

	private void generateRandom(Mode mode)
	{
		listOfRandom.clear();
		Random random = new Random();
		for (int i = 0; i < numberOfElements; i++)
			listOfRandom.add(new Vehicle(random.nextInt(10000), random.nextInt(100) + 1916));
		switch (mode)
		{
			case RANDOM:
				break;
			case ORDERED:
				Collections.sort(listOfRandom);
				break;
			case REVERSED:
				Collections.sort(listOfRandom);
				Collections.reverse(listOfRandom);
				break;
			case PARTIALLY_ORDERED:
			{
				int size = listOfRandom.size();
				int rest = size % partiallyOrderedNumberOfSections;
				int oneSection = size / partiallyOrderedNumberOfSections;
				if (oneSection <= 0)
				{
					partiallyOrderedNumberOfSections = 1;
					oneSection = rest;
				}
				List<List<Vehicle>> listOfLists = new ArrayList<>(partiallyOrderedNumberOfSections);
				for (int i = 0; i < partiallyOrderedNumberOfSections - 1; i++)
				{
					listOfLists.add(new ArrayList<>(oneSection));
					listOfLists.get(i).addAll(listOfRandom.subList(oneSection * i, (i + 1) * oneSection));
					Collections.sort(listOfLists.get(i));
				}
				listOfLists.add(new ArrayList<>());
				listOfLists.get(partiallyOrderedNumberOfSections - 1).addAll(
						listOfRandom.subList(oneSection * (partiallyOrderedNumberOfSections - 1), listOfRandom.size()));
				Collections.sort(listOfLists.get(partiallyOrderedNumberOfSections - 1));
				listOfRandom.clear();
				for (int i = 0; i < partiallyOrderedNumberOfSections; i++)
				{
					listOfRandom.addAll(listOfLists.get(i));
				}
				break;
			}
		}
	}

	private double calculateAverage(double[] n, int start)
	{
		double sum = 0;
		for (int i = start; i < n.length; i++)
			sum += n[i];
		return sum / (n.length - start);
	}

	public Results sortMultipleTimes(int n, int toOmit, AdvancedSort method)
	{
		double[] times = new double[n];
		for (int i = 0; i < n; i++)
		{
			switch (method)
			{
				case QUICKSORT:
					times[i] = sorting.testSorting(AdvancedSort.QUICKSORT);
					break;
				case MERGESORT:
					times[i] = sorting.testSorting(AdvancedSort.MERGESORT);
					break;
				case HEAPSORT:
					times[i] = sorting.testSorting(AdvancedSort.HEAPSORT);
					break;
				case MERGESORTJAVA:
					times[i] = sorting.testSorting(AdvancedSort.MERGESORTJAVA);
					break;
				// case MERGESORTITERATIVE:
				// times[i] =
				// sorting.testSorting(AdvancedSort.MERGESORTITERATIVE);
				// break;
				// case MERGESORTLI:
				// times[i] = sorting.testSorting(AdvancedSort.MERGESORTLI);
				// break;
				// case COLLECTIONS:
				// times[i] = sorting.testSorting(AdvancedSort.COLLECTIONS);
				// break;
			}
		}
		return new Results(times, calculateAverage(times, n > toOmit ? toOmit : 0));
	}

	public String doAllSortings(int times, int toOmit)
	{
		String pattern = "*%-16s%.4f ms%n";
		StringBuilder sb = new StringBuilder(
				"Sorted " + numberOfElements + " elements, " + times + " times:" + System.lineSeparator());
		for (Mode m : Mode.values())
		{
			sb.append(m.equals(Mode.PARTIALLY_ORDERED)
					? m.toString() + " - " + partiallyOrderedNumberOfSections + " sections" : m.toString())
					.append(System.lineSeparator());
			generateRandom(m);
			sorting.setListOfRandom(listOfRandom);
			for (AdvancedSort as : AdvancedSort.values())
				sb.append(String.format(pattern, as.toString(), sortMultipleTimes(times, toOmit, as).getAverage()));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String benchmarkSorting(int timesEach, int toOmit, int[] numberOfElements)
	{
		StringBuilder sb = new StringBuilder();
		for (int i : numberOfElements)
		{
			setNumberOfElements(i);
			sb.append(doAllSortings(timesEach, toOmit));
		}
		return sb.toString();
	}

	public String sorting(int times, int toOmit, int[] number)
	{
		StringBuilder sb = new StringBuilder();
		Map<Mode, Map<AdvancedSort, List<Double>>> results = new HashMap<>();
		for (int i : number)
		{
			setNumberOfElements(i);
			for (Mode m : Mode.values())
			{
				if (!results.containsKey(m))
					results.put(m, new HashMap<>());
				generateRandom(m);
				sorting.setListOfRandom(listOfRandom);
				for (AdvancedSort as : AdvancedSort.values())
				{
					if (!results.get(m).containsKey(as))
						results.get(m).put(as, new ArrayList<>());
					results.get(m).get(as).add(sortMultipleTimes(times, toOmit, as).getAverage());
				}
			}
		}
		for (Mode m : Mode.values())
		{
			sb.append(m.toString()).append(System.lineSeparator());
			for (AdvancedSort as : AdvancedSort.values())
			{
				sb.append(as.toString()).append(": |");
				for (int i = 0; i < number.length; i++)
				{
					sb.append(String.format("%.4f", results.get(m).get(as).get(i))).append("|");
				}
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}

	public void saveReport(String text, String fileName)
	{
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
		{
			bw.write(text);
		}
		catch (IOException e)
		{
			System.err.println("Cannot save to file: " + fileName + ".");
		}
	}

	public int getPartiallyOrderedNumberOfSections()
	{
		return partiallyOrderedNumberOfSections;
	}

	public void setPartiallyOrderedNumberOfSections(int partiallyOrderedNumberOfSections)
	{
		this.partiallyOrderedNumberOfSections = partiallyOrderedNumberOfSections;
	}

	public List<Vehicle> getListOfRandom()
	{
		return listOfRandom;
	}

	public int getNumberOfElements()
	{
		return numberOfElements;
	}

	public void setNumberOfElements(int n)
	{
		this.numberOfElements = n;
	}

	public String showTimes(int n, AdvancedSort sort, Mode mode, int times, int toOmit)
	{
		setNumberOfElements(n);
		generateRandom(mode);
		sorting.setListOfRandom(listOfRandom);
		Results results = sortMultipleTimes(times, toOmit, sort);
		return Arrays.toString(results.getTimes());
	}

	public static List<Vehicle> sortList(int n, int sections, Mode mode, AdvancedSort sort)
	{
		Test test = new Test(n, sections);
		test.generateRandom(mode);
		List<Vehicle> sortedList = new ArrayList<>(test.getListOfRandom());
		ListSorter<Vehicle> sorter = null;
		switch (sort)
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
			case MERGESORTJAVA:
				sorter = new MergeSortJava<>();
				break;
			// case MERGESORTITERATIVE:
			// sorter = new MergeSortIterative<>();
			// break;
			// case MERGESORTLI:
			// sorter = new MergeSortLI<>();
			// break;
			// case COLLECTIONS:
			// Collections.sort(sortedList);
			// return sortedList;
		}
		return sorter.sort(sortedList);
	}

	public static void main(String[] args)
	{
		int times = 40;
		int toOmit = 20;
		int[] array = { 2000, 1000, 2000, 5000, 10000, 15000 };
		Test t = new Test();
		String report = t.sorting(times, toOmit, array);
		System.out.print(report);
		// t.saveReport(report, "AdvancedLoop.txt");

		System.out.println(t.showTimes(100, AdvancedSort.QUICKSORT, Mode.RANDOM, times, toOmit));

		System.out.println(sortList(10, 4, Mode.RANDOM, AdvancedSort.QUICKSORT));
	}
}
