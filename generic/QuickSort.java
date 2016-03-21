package laboratorium.lista5.generic;

import java.util.List;

public class QuickSort<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list) throws IndexOutOfBoundsException
	{
		if (list.size() < 1)
			throw new IndexOutOfBoundsException("Index: 0, Size: " + list.size());
		return quickSort(list, 0, list.size() - 1);
	}

	private List<T> quickSort(List<T> list, int left, int right)
	{
		T pivot = list.get((left + right) / 2);
		int i = left;
		int j = right;
		while (i <= j)
		{
			while (list.get(i).compareTo(pivot) < 0)
				i++;
			while (list.get(j).compareTo(pivot) > 0)
				j--;
			if (i <= j)
			{
				T buffer = list.get(i);
				list.set(i, list.get(j));
				list.set(j, buffer);
				i++;
				j--;
			}
		}
		if (j > left)
			quickSort(list, left, j);
		if (i < right)
			quickSort(list, i, right);
		return list;
	}
}
