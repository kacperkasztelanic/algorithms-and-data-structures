package laboratorium.lista5.generic;

import java.util.List;

public class HeapSort<T extends Comparable<? super T>> implements ListSorter<T>
{
	private int n;

	public List<T> sort(List<T> list)
	{
		heapify(list);
		for (int i = n; i > 0; i--)
		{
			swap(list, 0, i);
			n--;
			maxHeapify(list, 0);
		}
		return list;
	}

	private void heapify(List<T> list)
	{
		n = list.size() - 1;
		for (int i = n / 2; i >= 0; i--)
			maxHeapify(list, i);
	}

	private void maxHeapify(List<T> list, int i)
	{
		int l = 2 * i;
		int r = 2 * i + 1;
		int max = i;
		if (l <= n && list.get(l).compareTo(list.get(i)) > 0)
			max = l;
		if (r <= n && list.get(r).compareTo(list.get(max)) > 0)
			max = r;
		if (max != i)
		{
			swap(list, max, i);
			maxHeapify(list, max);
		}
	}

	private void swap(List<T> list, int i, int j)
	{
		T buffer = list.get(i);
		list.set(i, list.get(j));
		list.set(j, buffer);
	}
}
