package laboratorium.lista4.generic;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list)
	{
		int size = list.size();
		for (int i = 1; i < size; i++)
		{
			for (int j = 0; j < size - i; j++)
			{
				if ((list.get(j)).compareTo(list.get(j + 1)) > 0)
				{
					T buffer;
					buffer = list.get(j + 1);
					list.set(j + 1, list.get(j));
					list.set(j, buffer);
				}
			}
		}
		return list;
	}
}
