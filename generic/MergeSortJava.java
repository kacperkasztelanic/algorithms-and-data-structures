package laboratorium.lista5.generic;

import java.util.ArrayList;
import java.util.List;

public class MergeSortJava<T extends Comparable<? super T>> implements ListSorter<T>
{
	private final int INSERTIONSORT_THRESHOLD = 7;

	public List<T> sort(List<T> list)
	{
		if (list.size() < 1)
			return list;
		List<T> result = new ArrayList<>(list);
		mergeSort(list, result, 0, list.size());
		return result;
	}

	private void mergeSort(List<T> list, List<T> result, int startIndex, int endIndex)
	{
		int length = endIndex - startIndex;
		if (length < INSERTIONSORT_THRESHOLD)
		{
			for (int i = startIndex; i < endIndex; i++)
				for (int j = i; j > startIndex && (result.get(j - 1).compareTo(result.get(j)) > 0); j--)
					swap(result, j, j - 1);
			return;
		}
		int mid = (startIndex + endIndex) >>> 1;
		mergeSort(result, list, startIndex, mid);
		mergeSort(result, list, mid, endIndex);
		for (int i = startIndex, p = startIndex, q = mid; i < endIndex; i++)
		{
			if (q >= endIndex || p < mid && (list.get(p).compareTo(list.get(q))) <= 0)
				result.set(i, list.get(p++));
			else
				result.set(i, list.get(q++));
		}
	}

	private void swap(List<T> list, int a, int b)
	{
		T buffer = list.get(a);
		list.set(a, list.get(b));
		list.set(b, buffer);
	}

}
