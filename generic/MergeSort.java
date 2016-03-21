package laboratorium.lista5.generic;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list) throws IndexOutOfBoundsException
	{
		if (list.size() < 1)
			throw new IndexOutOfBoundsException("Index: 0, Size: " + list.size());
		return mergesort(list, 0, list.size() - 1);
	}

	private List<T> mergesort(List<T> list, int startIndex, int endIndex)
	{
		if (startIndex == endIndex)
		{
			List<T> result = new ArrayList<>();
			result.add(list.get(startIndex));
			return result;
		}
		int splitIndex = startIndex + (endIndex - startIndex) / 2;
		return merge(mergesort(list, startIndex, splitIndex), mergesort(list, splitIndex + 1, endIndex));
	}

	private List<T> merge(List<T> left, List<T> right)
	{
		List<T> result = new ArrayList<>();
		int l = 0;
		int r = 0;
		while (l < left.size() && r < right.size())
		{
			T o1 = left.get(l);
			T o2 = right.get(r);
			if (o1.compareTo(o2) <= 0)
			{
				result.add(o1);
				l++;
			}
			else
			{
				result.add(o2);
				r++;
			}
		}
		while (l < left.size())
			result.add(left.get(l++));
		while (r < right.size())
			result.add(right.get(r++));
		return result;
	}
}
