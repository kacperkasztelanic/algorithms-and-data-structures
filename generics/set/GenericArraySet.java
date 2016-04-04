package laboratorium.lista6.generics.set;

import laboratorium.lista6.generics.common.GenericArrayIterator;
import laboratorium.lista6.generics.common.GenericIterator;

public class GenericArraySet<T> implements GenericSet<T>
{
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private final int INITIAL_CAPACITY;
	private T[] array;
	private int size;

	public GenericArraySet()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public GenericArraySet(int initialCapacity)
	{
		if (initialCapacity <= 0)
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		this.INITIAL_CAPACITY = initialCapacity;
		clear();
	}

	public GenericArraySet(T[] array)
	{
		INITIAL_CAPACITY = array.length;
		clear();
		size = INITIAL_CAPACITY;
		for (int i = 0; i < size; i++)
		{
			add(array[i]);
		}
	}

	public void add(T value)
	{
		if (!contains(value))
		{
			ensureCapacity(size + 1);
			array[size] = value;
			size++;
		}
	}

	public boolean contains(T value)
	{
		return indexOf(value) != -1;
	}

	public void remove(T value)
	{
		int index = indexOf(value);
		if (index < 0)
			return;
		int copyFrom = index + 1;
		if (index >= 0 && copyFrom < size)
			System.arraycopy(array, copyFrom, array, index, size - copyFrom);
		size--;
	}

	@SuppressWarnings("unchecked")
	public void clear()
	{
		array = (T[]) new Object[INITIAL_CAPACITY];
		size = 0;
	}

	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public GenericIterator<T> iterator()
	{
		return new GenericArrayIterator<T>(array, 0, size);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (!isEmpty())
		{
			GenericIterator<T> it = iterator();
			for (it.first(); !it.isDone(); it.next())
			{
				sb.append(it.current()).append(", ");
			}
			sb.setLength(sb.length() - 2);
		}
		sb.append(']').append("Size: ").append(size);
		return sb.toString();
	}

	private void ensureCapacity(int capacity)
	{
		if (array.length < capacity)
		{
			@SuppressWarnings("unchecked")
			T[] copy = (T[]) new Object[capacity + capacity / 2];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	private int indexOf(T value)
	{
		int index = 0;
		if (value == null)
			while (index < size && array[index] != null)
				index++;
		else
			while (index < size && !value.equals(array[index]))
				index++;
		return index < size ? index : -1;
	}
}
