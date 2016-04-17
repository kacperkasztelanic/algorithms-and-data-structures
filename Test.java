package laboratorium.lista7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import laboratorium.lista7.binarysearch.ArraySearcher;
import laboratorium.lista7.tree.BinarySearchTree;
import laboratorium.lista7.tree.Iterator;
import laboratorium.lista7.tree.Vehicle;

public class Test
{
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
		it = bst.iterator();
		it.first();
		report.append("In-Order").append(System.lineSeparator());
		while (!it.isDone())
		{
			report.append(it.current()).append(" ");
			it.next();
		}
		return report.toString();
	}

	public static String testBinarySearch()
	{
		StringBuilder report = new StringBuilder();
		return report.toString();
	}

	public static int[] generateArray(int length)
	{
		int[] array = new int[length];
		Random random = new Random();
		for (int i = 0; i < array.length; i++)
			array[i] = random.nextInt(10000000);
		Arrays.sort(array);
		return array;
	}

	public static void testSearch()
	{
		// int n = 100;
		// int find = 90;
		// int[] array = new int[n];
		// for (int i = 0; i < n; i++)
		// array[i] = 5 * i;
		int[] array = generateArray(1000000);
		int find = array[1819];
		// System.out.println(array[ArraySearcher.binarySearch(array, find)]);
		// System.out.println(array[ArraySearcher.linearSearch(array, find)]);
		// System.out.println(array[ArraySearcher.recursiveBinarySearch(array,
		// find)]);
		// System.out.println(array[ArraySearcher.interpolationSearch(array,
		// find)]);
		long start = System.nanoTime();
		ArraySearcher.recursiveBinarySearch(array, find);
		long end = System.nanoTime();
		System.out.println((end - start) / 1000);
		start = System.nanoTime();
		ArraySearcher.linearSearch(array, find);
		end = System.nanoTime();
		System.out.println((end - start) / 1000);
		start = System.nanoTime();
		ArraySearcher.binarySearch(array, find);
		end = System.nanoTime();
		System.out.println((end - start) / 1000);
		// start = System.nanoTime();
		// ArraySearcher.interpolationSearch(array, find);
		// end = System.nanoTime();
		// System.out.println(end - start);

		System.out.println(ArraySearcher.binarySearch(array, find));
		System.out.println(ArraySearcher.linearSearch(array, find));
		System.out.println(ArraySearcher.recursiveBinarySearch(array, find));
		// System.out.println(ArraySearcher.interpolationSearch(array, find));
		// System.out.println(ArraySearcher.interpolationSearch2(array, find));
	}

	public static void main(String[] args)
	{
		System.out.println(integerTestTree());
		// System.out.println(testBinarySearch());
		// testSearch();
	}
}
