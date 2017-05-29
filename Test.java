package laboratorium.lista7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import laboratorium.lista7.binarysearch.ArraySearcher;
import laboratorium.lista7.bsttree.BinarySearchTree;
import laboratorium.lista7.bsttree.Iterator;

public class Test
{
	public enum Search
	{
		SEQUENTIAL, BINARY, INTERPOLATION;
	}

	private int[] array;
	private Random random = new Random();
	private ArraySearcher searcher = new ArraySearcher();

	public int[] getArray()
	{
		return array;
	}

	public static String testVehicleTree()
	{
		StringBuilder report = new StringBuilder();
		BinarySearchTree<Vehicle> tree = new BinarySearchTree<>((Vehicle v1, Vehicle v2) ->
		{
			int result = v1.getYearOfConstruction() - v2.getYearOfConstruction();
			return result != 0 ? result : v1.getId() - v2.getId();
		});
		List<Vehicle> list = new ArrayList<>();
		list.add(new Vehicle(1, 2005));
		list.add(new Vehicle(4, 2007));
		list.add(new Vehicle(3, 2002));
		list.add(new Vehicle(6, 2010));
		list.add(new Vehicle(7, 2001));
		report.append(tree.toString()).append(System.lineSeparator());
		for (Vehicle v : list)
		{
			tree.insert(v);
			report.append(tree.toString()).append(System.lineSeparator());
		}
		for (Vehicle v : list)
		{
			tree.delete(v);
			report.append(tree.toString()).append(System.lineSeparator());
		}
		return report.toString();
	}

	public static String integerTestTree()
	{
		StringBuilder report = new StringBuilder();
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		int[] a = { 7, 1, 0, 3, 2, 5, 4, 6, 9, 8, 10 };
		report.append("To insert:").append(System.lineSeparator()).append(Arrays.toString(a))
				.append(System.lineSeparator());
		for (int i : a)
			bst.insert(i);
		Iterator<Integer> it = bst.preOrderIterator();
		it.first();
		report.append("Pre-Order: ").append(System.lineSeparator());
		while (!it.isDone())
		{
			report.append(it.current()).append(" ");
			it.next();
		}
		report.append(System.lineSeparator());
		it = bst.postOrderIterator();
		it.first();
		report.append("Post-Order").append(System.lineSeparator());
		while (!it.isDone())
		{
			report.append(it.current()).append(" ");
			it.next();
		}
		report.append(System.lineSeparator());
		it = bst.inOrderIterator();
		it.first();
		report.append("In-Order").append(System.lineSeparator());
		while (!it.isDone())
		{
			report.append(it.current()).append(" ");
			it.next();
		}
		return report.toString();
	}

	public double calculateAverage(int[] results)
	{
		int sum = 0;
		for (int i = 0; i < results.length; i++)
			sum += results[i];
		return ((double) sum / results.length);
	}

	public void generateArray(int length, int upperbound)
	{
		int[] array = new int[length];
		Random random = new Random();
		for (int i = 0; i < array.length; i++)
			array[i] = random.nextInt(upperbound);
		Arrays.sort(array);
		this.array = array;
	}

	public double testSearch(int times, Search mode)
	{
		int[] results = new int[times];
		for (int i = 0; i < times; i++)
		{
			switch (mode)
			{
				case BINARY:
					searcher.binarySearchExe1(array, array[random.nextInt(array.length - 1)]);
					break;
				case INTERPOLATION:
					searcher.interpolationSearch(array, array[random.nextInt(array.length - 1)]);
					break;
				case SEQUENTIAL:
					searcher.sequentialSearch(array, array[random.nextInt(array.length - 1)]);
					break;
			}

			results[i] = searcher.getCounter();
			searcher.resetCounter();
		}
		return calculateAverage(results);
	}

	public String showSearchResults(int length, int upperbound, int value)
	{
		generateArray(length, upperbound);
		StringBuilder report = new StringBuilder();
		report.append(Arrays.toString(array)).append(System.lineSeparator());
		report.append("Searching for: ").append(value).append(System.lineSeparator());
		for (Search s : Search.values())
			switch (s)
			{
				case BINARY:
					report.append(s.toString()).append(": ").append(searcher.binarySearch(array, value))
							.append(System.lineSeparator());
					break;
				case INTERPOLATION:
					report.append(s.toString()).append(": ").append(searcher.interpolationSearch(array, value))
							.append(System.lineSeparator());
					break;
				case SEQUENTIAL:
					report.append(s.toString()).append(": ").append(searcher.sequentialSearch(array, value))
							.append(System.lineSeparator());
					break;
			}
		return report.toString();
	}

	public static void main(String[] args)
	{
		System.out.println(integerTestTree());
		System.out.println(testVehicleTree());
		Test t = new Test();
		int times = 100;
		for (int i = 10; i <= 10000000; i *= 10)
		{
			t.generateArray(i, 10000000);
			System.out.println("Array length: " + i);
			System.out.println("Sequential: " + t.testSearch(times, Search.SEQUENTIAL));
			System.out.println("Binary: " + t.testSearch(times, Search.BINARY));
			System.out.println("Interpolation: " + t.testSearch(times, Search.INTERPOLATION));
		}
		System.out.println(t.showSearchResults(100, 10000, 5));
	}
}
