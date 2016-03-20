package laboratorium.lista4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import laboratorium.lista4.generic.GenericTestSorting;

public class Test
{
	public enum Mode
	{
		RANDOM, ORDERED, REVERSED, PARTIALLY_ORDERED;
	}

	private List<Vehicle> listOfRandom;
	private int n;
	private int partiallyOrderedNumberOfSections = 4;
	private GenericTestSorting<Vehicle> sorting;

	public Test(int n, Mode mode)
	{
		this.n = n;
		listOfRandom = new ArrayList<>(n);
		generateRandom(mode);
		sorting = new GenericTestSorting<>(listOfRandom);
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

	public Results sortMultipleTimes(int n, Sort mode)
	{
		double[] times = new double[n];
		for (int i = 0; i < n; i++)
		{
			switch (mode)
			{
				case BUBBLESORT:
					times[i] = sorting.testSorting(Sort.BUBBLESORT);
					break;
				case INSERTIONSORT:
					times[i] = sorting.testSorting(Sort.INSERTIONSORT);
					break;
				case SELECTIONSORT:
					times[i] = sorting.testSorting(Sort.SELECTIONSORT);
					break;
				case SHELLHIBBARD:
					times[i] = sorting.testSorting(Sort.SHELLHIBBARD);
					break;
				case SHELLSHELL:
					times[i] = sorting.testSorting(Sort.SHELLSHELL);
					break;
				case SHELLTOKUDA:
					times[i] = sorting.testSorting(Sort.SHELLTOKUDA);
					break;
				case SHELLKNUTH:
					times[i] = sorting.testSorting(Sort.SHELLKNUTH);
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
		sb.append(String.format(pattern, "BubbleSort:", sortMultipleTimes(times, Sort.BUBBLESORT).getAverage()));
		sb.append(String.format(pattern, "InsertionSort:", sortMultipleTimes(times, Sort.INSERTIONSORT).getAverage()));
		sb.append(String.format(pattern, "SelectionSort:", sortMultipleTimes(times, Sort.SELECTIONSORT).getAverage()));
		sb.append(String.format(pattern, "ShellSort (Hibbard): ",
				sortMultipleTimes(times, Sort.SHELLHIBBARD).getAverage()));
		sb.append(
				String.format(pattern, "ShellSort (Knuth): ", sortMultipleTimes(times, Sort.SHELLKNUTH).getAverage()));
		sb.append(
				String.format(pattern, "ShellSort (Shell): ", sortMultipleTimes(times, Sort.SHELLSHELL).getAverage()));
		sb.append(String.format(pattern, "ShellSort (Tokuda): ",
				sortMultipleTimes(times, Sort.SHELLTOKUDA).getAverage()));
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
		int number = 25000;
		int times = 10;
		System.out.println("Random");
		Test t = new Test(number, Mode.RANDOM);
		String report = t.doAllSortings(times, false);
		System.out.println(report);
		t.saveReport(report, number + "Random.txt");

		System.out.println("Ordered");
		Test t2 = new Test(number, Mode.ORDERED);
		String report2 = t2.doAllSortings(times, false);
		System.out.println(report2);
		t.saveReport(report2, number + "Ordered.txt");

		System.out.println("Reversed");
		Test t3 = new Test(number, Mode.REVERSED);
		String report3 = t3.doAllSortings(times, false);
		System.out.println(report3);
		t.saveReport(report3, number + "Reversed.txt");

		// System.out.println("Partially Ordered");
		// Test t4 = new Test(number, Mode.PARTIALLY_ORDERED);
		// t4.setPartiallyOrderedNumberOfSections(5);
		// String report4 = t4.doAllSortings(times, false);
		// System.out.println(report4);
	}
}
