package laboratorium.lista7.other.visualisation;

public class BinarySearch
{
	public static int binarySearch(int[] tab, int key)
	{
		int lower = 0;
		int upper = tab.length - 1;
		int middle;
		while (lower <= upper)
		{
			middle = lower + (upper - lower) / 2;
			if (key < tab[middle])
			{
				upper = middle - 1;
			}
			else if (key > tab[middle])
			{
				lower = middle + 1;
			}
			else
			{
				return middle;
			}
		}
		return -1;
	}

	public static int interpolationSearch(int[] sortedArray, int toFind)
	{
		int low = 0;
		int high = sortedArray.length - 1;
		int mid;
		while (sortedArray[low] <= toFind && sortedArray[high] >= toFind)
		{
			if (sortedArray[high] - sortedArray[low] == 0)
				return (low + high) / 2;
			mid = (int) (low + ((long) (toFind - sortedArray[low]) * (long) (high - low))
					/ (long) (sortedArray[high] - sortedArray[low]));
			// mid = (int) (low + ((toFind - sortedArray[low]) * ((long)(high -
			// low)) / (long)(sortedArray[high] - sortedArray[low])));

			if (sortedArray[mid] < toFind)
				low = mid + 1;
			else if (sortedArray[mid] > toFind)
				high = mid - 1;
			else
				return mid;
		}
		if (sortedArray[low] == toFind)
			return low;
		else
			return -1;
	}
}
