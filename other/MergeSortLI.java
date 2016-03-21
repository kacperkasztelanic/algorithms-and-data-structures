package laboratorium.lista5.other;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import laboratorium.lista5.generic.ListSorter;

public class MergeSortLI<T extends Comparable<T>> implements ListSorter<T>
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
		ListIterator<T> l = left.listIterator();
		ListIterator<T> r = right.listIterator();
		while (l.hasNext() && r.hasNext())
		{
			T o1 = l.next();
			T o2 = r.next();
			if (o1.compareTo(o2) <= 0)
			{
				result.add(o1);
				r.previous();
			}
			else
			{
				result.add(o2);
				l.previous();
			}
		}
		while (l.hasNext())
			result.add(l.next());
		while (r.hasNext())
			result.add(r.next());
		return result;
	}
}
