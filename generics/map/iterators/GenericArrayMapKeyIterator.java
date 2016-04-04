package laboratorium.lista6.generics.map.iterators;

import laboratorium.lista6.generics.common.GenericIterator;
import laboratorium.lista6.generics.map.GenericArrayMap;

public class GenericArrayMapKeyIterator<K, V> implements GenericIterator<K>
{
	private final Object[] array;
	private final int first;
	private final int last;
	private int current;

	public GenericArrayMapKeyIterator(Object[] array, int first, int length)
	{
		this.array = array;
		this.first = first;
		last = first + length - 1;
		current = -1;
	}

	public GenericArrayMapKeyIterator(Object[] array)
	{
		this.array = array;
		first = 0;
		last = array.length - 1;
		current = -1;
	}

	public void first()
	{
		current = 0;
	}

	public void last()
	{
		current = array.length - 1;
	}

	public void next()
	{
		current++;
	}

	public void previous()
	{
		current--;
	}

	@SuppressWarnings("unchecked")
	public K current()
	{
		return ((GenericArrayMap<K, V>.MapEntry<K, V>) array[current]).getKey();
	}

	public boolean isDone()
	{
		return current < first || current > last;
	}
}
