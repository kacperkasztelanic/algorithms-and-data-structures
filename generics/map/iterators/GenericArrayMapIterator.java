package laboratorium.lista6.generics.map.iterators;

import laboratorium.lista6.generics.map.GenericArrayMap;

public class GenericArrayMapIterator<K, V> implements GenericMapIterator<K, V>
{
	private final Object[] array;
	private final int first;
	private final int last;
	private int current;

	public GenericArrayMapIterator(Object[] array, int first, int length)
	{
		this.array = array;
		this.first = first;
		last = first + length - 1;
		current = -1;
	}

	public GenericArrayMapIterator(Object[] array)
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
	public GenericArrayMap<K, V>.MapEntry<K, V> current()
	{
		return (GenericArrayMap<K, V>.MapEntry<K, V>) array[current];
	}

	public boolean isDone()
	{
		return current < first || current > last;
	}
}
