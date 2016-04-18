package laboratorium.lista7.other;

public class InterpolationSearch
{
	private int counter = 0;
	private int size;

	public int search(int[] sortedArray, int toFind)
	{
		counter = 0;
		int low = 0;
		int high = sortedArray.length - 1;
		size = sortedArray.length;
		int mid;
		while (sortedArray[low] <= toFind && sortedArray[high] >= toFind)
		{
			counter++;
			if (sortedArray[high] - sortedArray[low] == 0)
				return (low + high) / 2;
			/** out of range is possible here **/
			mid = low + (int) (((long) (toFind - sortedArray[low]) * (high - low))
					/ (sortedArray[high] - sortedArray[low]));

			if (sortedArray[mid] < toFind)
				low = mid + 1;
			else if (sortedArray[mid] > toFind)
				high = mid - 1;
			else
				return mid;
		}
		if (sortedArray[low] == toFind)
			return low;
		/** not found **/
		else if (sortedArray[low] > toFind)
			return -low - 1;
		else if (sortedArray[high] < toFind)
			return -high - 2;
		else
			return 99999999;
	}

	public String toString()
	{
		int n = counter;
		counter = 0;
		int s = size;
		size = 0;
		return "Number of iterations to found Value:\t" + n + "\nArray size:\t" + s;
	}

	public int getN()
	{
		return counter;
	}

	public static double calculateAverage(int[] results)
	{
		int sum = 0;
		for (int i = 0; i < results.length; i++)
			sum += results[i];
		return (double) (sum / results.length);
	}
}