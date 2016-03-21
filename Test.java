package laboratorium.lista5;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import laboratorium.lista5.generic.GenericTestAdvancedSorting;

public class Test
{
	public enum Mode
	{
		RANDOM, ORDERED, REVERSED, PARTIALLY_ORDERED;
	}

	private List<Vehicle> listOfRandom;
	private int n;
	private int partiallyOrderedNumberOfSections = 4;
	private GenericTestAdvancedSorting<Vehicle> sorting;

	public Test(int n, Mode mode)
	{
		this.n = n;
		listOfRandom = new ArrayList<>(n);
		generateRandom(mode);
		sorting = new GenericTestAdvancedSorting<>(listOfRandom);
	}

	private void generateRandom(Mode mode)
	{
		Random random = new Random();
		for (int i = 0; i < n; i++)
			listOfRandom.add(new Vehicle(random.nextInt(1000), random.nextInt(100) + 1916));
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

	public Results sortMultipleTimes(int n, AdvancedSort mode)
	{
		double[] times = new double[n];
		for (int i = 0; i < n; i++)
		{
			switch (mode)
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
				case MERGESORTITERATIVE:
					times[i] = sorting.testSorting(AdvancedSort.MERGESORTITERATIVE);
					break;
				case MERGESORTLI:
					times[i] = sorting.testSorting(AdvancedSort.MERGESORTLI);
					break;
			}
		}
		return new Results(times, calculateAverage(times, n > 4 ? 4 : 0));
	}

	public String doAllSortings(int times, boolean verbose)
	{
		String pattern = "*%-15s%.4f ms%n";
		StringBuilder sb = new StringBuilder(
				"Sorted " + n + " elements, " + times + " times:" + System.lineSeparator());
		sb.append(String.format(pattern, "QuickSort:", sortMultipleTimes(times, AdvancedSort.QUICKSORT).getAverage()));
		sb.append(String.format(pattern, "MergeSort:", sortMultipleTimes(times, AdvancedSort.MERGESORT).getAverage()));
		sb.append(String.format(pattern, "MergeSortLI:",
				sortMultipleTimes(times, AdvancedSort.MERGESORTLI).getAverage()));
		sb.append(String.format(pattern, "MSIterative:",
				sortMultipleTimes(times, AdvancedSort.MERGESORTITERATIVE).getAverage()));
		sb.append(String.format(pattern, "HeapSort:", sortMultipleTimes(times, AdvancedSort.HEAPSORT).getAverage()));
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

	public static void main(String[] args)
	{
		int number = 100000;
		int times = 10;
		System.out.println("Random");
		Test t = new Test(number, Mode.RANDOM);
		String report = t.doAllSortings(times, false);
		System.out.println(report);
		// t.saveReport(report, number + "Random.txt");

		System.out.println("Ordered");
		Test t2 = new Test(number, Mode.ORDERED);
		String report2 = t2.doAllSortings(times, false);
		System.out.println(report2);
		// t.saveReport(report2, number + "Ordered.txt");

		System.out.println("Reversed");
		Test t3 = new Test(number, Mode.REVERSED);
		String report3 = t3.doAllSortings(times, false);
		System.out.println(report3);
		// t.saveReport(report3, number + "Reversed.txt");

		// System.out.println("Partially Ordered");
		// Test t4 = new Test(number, Mode.PARTIALLY_ORDERED);
		// t4.setPartiallyOrderedNumberOfSections(5);
		// String report4 = t4.doAllSortings(times, false);
		// System.out.println(report4);
	}
}
