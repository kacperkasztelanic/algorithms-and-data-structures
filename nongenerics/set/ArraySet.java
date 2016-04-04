package laboratorium.lista6.nongenerics.set;

import laboratorium.lista6.nongenerics.common.ArrayIterator;
import laboratorium.lista6.nongenerics.common.Iterator;

public class ArraySet implements Set
{
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private final int INITIAL_CAPACITY;
	private String[] array;
	private int size;

	public ArraySet()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public ArraySet(int initialCapacity)
	{
		if (initialCapacity <= 0)
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		this.INITIAL_CAPACITY = initialCapacity;
		clear();
	}

	public ArraySet(String[] array)
	{
		INITIAL_CAPACITY = array.length;
		clear();
		size = INITIAL_CAPACITY;
		for (int i = 0; i < size; i++)
		{
			add(array[i]);
		}
	}

	public void add(String value)
	{
		if (!contains(value))
		{
			ensureCapacity(size + 1);
			array[size] = value;
			size++;
		}
	}

	public boolean contains(String value)
	{
		return indexOf(value) != -1;
	}

	public void remove(String value)
	{
		int index = indexOf(value);
		if (index < 0)
			return;
		int copyFrom = index + 1;
		if (copyFrom < size)
			System.arraycopy(array, copyFrom, array, index, size - copyFrom);
		size--;
	}

	public void clear()
	{
		array = new String[INITIAL_CAPACITY];
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

	public Iterator iterator()
	{
		return new ArrayIterator(array, 0, size);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (!isEmpty())
		{
			Iterator it = iterator();
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
			String[] copy = new String[capacity + capacity / 2];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	private int indexOf(String value)
	{
		int index = 0;
		if (value == null)
			while (index < size && array[index] != null)
				index++;
		else
			while (index < size && !value.equals(array[index].toString()))
				index++;
		return index < size ? index : -1;
	}
}
