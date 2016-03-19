package laboratorium.lista4;

import java.util.List;

import laboratorium.lista4.generic.ListSorter;

public class ShellSortOld<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list)
	{
		int j;
		int size = list.size();
		for (int gap = size / 2; gap > 0; gap /= 2)
		{
			for (int i = gap; i < size; i++)
			{
				T tmp = list.get(i);
				for (j = i; j >= gap && tmp.compareTo(list.get(j - gap)) < 0; j -= gap)
				{
					list.set(j, list.get(j - gap));
				}
				list.set(j, tmp);
			}
		}
		return list;
	}
}
