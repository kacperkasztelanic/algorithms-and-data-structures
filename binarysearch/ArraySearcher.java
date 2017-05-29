package laboratorium.lista7.binarysearch;

public class ArraySearcher
{
	private int counter = 0;

	public int getCounter()
	{
		return counter;
	}

	public void resetCounter()
	{
		counter = 0;
	}

	public int sequentialSearch(int[] array, int value)
	{
		int cmp = 0;
		int i = 0;
		while (i < array.length && (cmp = value - array[i]) > 0)
		{
			counter++;
			i++;
		}
		return i < array.length && cmp == 0 ? i : -1;
	}

	public int binarySearchExe1(int[] array, int value)
	{
		int lower = 0;
		int upper = array.length - 1;
		while (lower <= upper)
		{
			counter++;
			int mid = (lower + upper) >> 1;
			int cmp = value - array[mid];
			if (cmp < 0)
				upper = mid - 1;
			else if (cmp > 0)
				lower = mid + 1;
			else
				return mid;
		}
		return lower <= upper ? lower : -1;
	}

	public int binarySearch(int[] array, int value)
	{
		int lower = 0;
		int upper = array.length - 1;
		while (lower <= upper)
		{
			counter++;
			int mid = lower + ((upper - lower) >> 1);
			int cmp = value - array[mid];
			if (cmp < 0)
				upper = mid - 1;
			else if (cmp > 0)
				lower = mid + 1;
			else
				return mid;
		}
		return lower <= upper ? lower : -1;
	}

	public int recursiveBinarySearch(int[] array, int value)
	{
		return recursiveBinarySearchHelper(array, value, 0, array.length - 1);
	}

	private int recursiveBinarySearchHelper(int[] array, int value, int lower, int upper)
	{
		counter++;
		if (lower > upper)
			return -1;
		int index = lower + ((upper - lower) >> 1);
		int cmp = value - array[index];
		if (cmp < 0)
			return recursiveBinarySearchHelper(array, value, lower, index - 1);
		else if (cmp > 0)
			return recursiveBinarySearchHelper(array, value, index + 1, upper);
		else
			return index;
	}

	public int interpolationSearch(int[] array, int value)
	{
		int lower = 0;
		int upper = array.length - 1;
		while (array[lower] <= value && value <= array[upper])
		{
			counter++;
			int mid = (int) (lower + ((long) (value - array[lower]) * (upper - lower)) / (array[upper] - array[lower]));
			if (value > array[mid])
				lower = mid + 1;
			else if (value < array[mid])
				upper = mid - 1;
			else
				return mid;
		}
		return -1;
	}
}
