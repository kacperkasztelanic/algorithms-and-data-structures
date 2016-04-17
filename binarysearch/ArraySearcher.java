package laboratorium.lista7.binarysearch;

public class ArraySearcher
{
	public static int binarySearch(int[] array, int value)
	{
		int lower = 0;
		int upper = array.length - 1;
		int index = 0;
		int cmp = 0;
		while (lower <= upper && (cmp = (value - array[index = (lower + upper) / 2])) != 0)
			if (cmp < 0)
				upper = index - 1;
			else
				lower = index + 1;
		return lower <= upper && cmp == 0 ? index : -1;
	}

	public static int linearSearch(int[] array, int value)
	{
		int cmp = 0;
		int i = 0;
		while (i < array.length && (cmp = value - array[i]) > 0)
			i++;
		return i < array.length && cmp == 0 ? i : -1;
	}

	public static int recursiveBinarySearch(int[] array, int value)
	{
		return recursiveBinarySearchHelper(array, value, 0, array.length - 1);
	}

	private static int recursiveBinarySearchHelper(int[] array, int value, int lower, int upper)
	{
		if (lower > upper)
			return -1;
		int index = (lower + upper) / 2;
		int cmp = value - array[index];
		if (cmp < 0)
			return recursiveBinarySearchHelper(array, value, lower, index - 1);
		if (cmp > 0)
			return recursiveBinarySearchHelper(array, value, index + 1, upper);
		return index;
	}

	public static int interpolationSearch(int[] array, int value)
	{
		int index = -1;
		int lower = 0;
		int upper = array.length - 1;
		while (array[lower] != array[upper] && array[lower] <= value && value <= array[upper])
		{
			index = lower + ((value - array[lower]) * (upper - lower) / (array[upper] - array[lower]));
			if (array[index] < value)
				lower = index + 1;
			else if (value < array[index])
				upper = index - 1;
			else
				return index;
		}
		return -1;
	}

	// public static int interpolationSearch2(int[] array, int value)
	// {
	// int index = -1;
	// int lower = 0;
	// int upper = array.length - 1;
	// do index= poc+(k-k(poc))/(k(kon)-k(poc))*(kon-poc)
	// if (k >k(b)){
	//
	// }
	// else
	// {
	//
	// }
	// while (poc >=kon)
	// sprawdü element na pozycji poc
	//
	// }
}
