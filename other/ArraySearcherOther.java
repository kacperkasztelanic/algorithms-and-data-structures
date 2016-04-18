package laboratorium.lista7.other;

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
}
