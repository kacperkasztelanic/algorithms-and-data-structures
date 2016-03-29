package laboratorium.lista4;

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

import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;
import laboratorium.lista4.generic.BubbleSort;
import laboratorium.lista4.generic.GenericTestBasicSorting;
import laboratorium.lista4.generic.InsertionSort;
import laboratorium.lista4.generic.ListSorter;
import laboratorium.lista4.generic.SelectionSort;
import laboratorium.lista4.generic.ShellSort;

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
	private GenericTestBasicSorting<Vehicle> sorting;

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
		sorting = new GenericTestBasicSorting<>();
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

	public Results sortMultipleTimes(int n, int toOmit, BasicSort method)
	{
		double[] times = new double[n];
		for (int i = 0; i < n; i++)
		{
			switch (method)
			{
				case BUBBLESORT:
					times[i] = sorting.testSorting(BasicSort.BUBBLESORT);
					break;
				case INSERTIONSORT:
					times[i] = sorting.testSorting(BasicSort.INSERTIONSORT);
					break;
				case SELECTIONSORT:
					times[i] = sorting.testSorting(BasicSort.SELECTIONSORT);
					break;
				case SHELLHIBBARD:
					times[i] = sorting.testSorting(BasicSort.SHELLHIBBARD);
					break;
				case SHELLSHELL:
					times[i] = sorting.testSorting(BasicSort.SHELLSHELL);
					break;
				case SHELLTOKUDA:
					times[i] = sorting.testSorting(BasicSort.SHELLTOKUDA);
					break;
				case SHELLKNUTH:
					times[i] = sorting.testSorting(BasicSort.SHELLKNUTH);
					break;
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
			for (BasicSort bs : BasicSort.values())
				sb.append(String.format(pattern, bs.toString(), sortMultipleTimes(times, toOmit, bs).getAverage()));
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
		Map<Mode, Map<BasicSort, List<Double>>> results = new HashMap<>();
		for (int i : number)
		{
			setNumberOfElements(i);
			for (Mode m : Mode.values())
			{
				if (!results.containsKey(m))
					results.put(m, new HashMap<>());
				generateRandom(m);
				sorting.setListOfRandom(listOfRandom);
				for (BasicSort bs : BasicSort.values())
				{
					if (!results.get(m).containsKey(bs))
						results.get(m).put(bs, new ArrayList<>());
					results.get(m).get(bs).add(sortMultipleTimes(times, toOmit, bs).getAverage());
				}
			}
		}
		for (Mode m : Mode.values())
		{
			sb.append(m.toString()).append(System.lineSeparator());
			for (BasicSort bs : BasicSort.values())
			{
				sb.append(bs.toString()).append(": |");
				for (int i = 0; i < number.length; i++)
				{
					sb.append(String.format("%.4f", results.get(m).get(bs).get(i))).append("|");
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

	public String showTimes(int n, BasicSort sort, Mode mode, int times, int toOmit)
	{
		setNumberOfElements(n);
		generateRandom(mode);
		sorting.setListOfRandom(listOfRandom);
		Results results = sortMultipleTimes(times, toOmit, sort);
		return Arrays.toString(results.getTimes());
	}

	public static List<Vehicle> sortList(int n, int sections, Mode mode, BasicSort sort)
	{
		Test test = new Test(n, sections);
		test.generateRandom(mode);
		List<Vehicle> sortedList = new ArrayList<>(test.getListOfRandom());
		ListSorter<Vehicle> sorter = null;
		switch (sort)
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
		return sorter.sort(sortedList);
	}

	public static void main(String[] args)
	{
		int times = 200;
		int toOmit = 150;
		int[] array = { 100, 200 };
		Test t = new Test();
		String report = t.sorting(times, toOmit, array);
		System.out.print(report);
		// System.out.println(t.showTimes(100, BasicSort.BUBBLESORT,
		// Mode.RANDOM, times, toOmit));
		System.out.println(sortList(10, 4, Mode.RANDOM, BasicSort.BUBBLESORT));
	}
}
