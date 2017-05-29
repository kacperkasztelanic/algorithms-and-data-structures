package laboratorium.lista7.other;

import java.util.Arrays;
import java.util.Random;

import laboratorium.lista7.binarysearch.ArraySearcher;

public class ArraySearcherOther
{
	public static int counter;

	public static int getCounter()
	{
		return counter;
	}

	public static int binarySearchOther(int[] array, int value)
	{
		counter = 0;
		int lower = 0;
		int upper = array.length - 1;
		int index = 0;
		int cmp = 0;
		while (lower <= upper && (cmp = (value - array[index = (lower + upper) / 2])) != 0)
		{
			counter++;
			if (cmp < 0)
				upper = index - 1;
			else
				lower = index + 1;
		}
		return lower <= upper && cmp == 0 ? index : -1;
	}

	public String showExe1Error()
	{
		Random random = new Random();
		ArraySearcher searcher = new ArraySearcher();
		int[] a = new int[500000000];
		for (int i = 0; i < a.length; i++)
			a[i] = random.nextInt();
		Arrays.sort(a);
		int find = a[random.nextInt(a.length)];
		StringBuilder report = new StringBuilder();
		report.append("Searching for: ").append(find).append(System.lineSeparator());
		report.append(searcher.binarySearchExe1(a, find)).append(System.lineSeparator());
		report.append(searcher.binarySearch(a, find)).append(System.lineSeparator());
		return report.toString();
	}
}
