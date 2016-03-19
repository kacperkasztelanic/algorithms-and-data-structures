package laboratorium.lista4.generic;

import java.util.List;

public class SelectionSort<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list)
	{
		int size = list.size();
		int min;
		T buffer;
		for (int i = 0; i < size - 1; i++)
		{
			min = i;
			for (int j = i + 1; j < size; j++)
			{
				if (list.get(j).compareTo(list.get(min)) < 0)
					min = j;
			}
			buffer = list.get(min);
			list.set(min, list.get(i));
			list.set(i, buffer);
		}
		return list;
	}
}
