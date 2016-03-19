package laboratorium.lista4.generic;

import java.util.List;

public class InsertionSort<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list)
	{
		for (int i = 1; i < list.size(); i++)
		{
			T value = list.get(i);
			T temp;
			int j;
			for (j = i; j > 0 && value.compareTo(temp = list.get(j - 1)) < 0; j--)
				list.set(j, temp);
			list.set(j, value);
		}
		return list;
	}
}
