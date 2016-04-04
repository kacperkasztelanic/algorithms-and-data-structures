package laboratorium.lista6.nongenerics.map;

import laboratorium.lista6.nongenerics.common.ArrayIterator;
import laboratorium.lista6.nongenerics.common.Iterator;

public class ArrayMap implements Map
{
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private final int INITIAL_CAPACITY;
	private MapEntry[] array;
	private int size;

	public ArrayMap()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public ArrayMap(int initialCapacity)
	{
		if (initialCapacity <= 0)
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		this.INITIAL_CAPACITY = initialCapacity;
		clear();
	}

	public int get(String key) throws KeyNotFoundException
	{
		int index = indexOf(key);
		if (index != -1)
			return array[index].getValue();
		throw new KeyNotFoundException("Key not found: " + key);
	}

	public void put(String key, int value)
	{
		if (!containsKey(key))
		{
			ensureCapacity(size + 1);
			array[size] = new MapEntry(key, value);
			size++;
		}
		else
			array[indexOf(key)].setValue(value);
	}

	public boolean containsKey(String key)
	{
		return indexOf(key) != -1;
	}

	public int remove(String key) throws KeyNotFoundException
	{
		int index = indexOf(key);
		if (index < 0)
			throw new KeyNotFoundException("Key not found: " + key);
		int copyFrom = index + 1;
		int value = array[index].getValue();
		if (copyFrom < size)
			System.arraycopy(array, copyFrom, array, index, size - copyFrom);
		size--;
		return value;
	}

	public void clear()
	{
		array = new MapEntry[INITIAL_CAPACITY];
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

	public Iterator keyIterator()
	{
		return new ArrayIterator(array, 0, size);
	}

	public Iterator valueIterator()
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
				MapEntry current = (MapEntry) it.current();
				sb.append(current.getKey()).append("|").append(current.getValue()).append(", ");
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
			MapEntry[] copy = new MapEntry[capacity + capacity / 2];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	private int indexOf(String key)
	{
		int index = 0;
		if (key == null)
			while (index < size && array[index] != null)
				index++;
		else
			while (index < size && !key.equals(array[index].getKey()))
				index++;
		return index < size ? index : -1;
	}

	public class MapEntry
	{
		private String key;
		private int value;

		MapEntry(String key, int value)
		{
			this.key = key;
			this.value = value;
		}

		public String getKey()
		{
			return key;
		}

		public int getValue()
		{
			return value;
		}

		public void setValue(int value)
		{
			this.value = value;
		}

		public String toString()
		{
			return "[" + key + "|" + value + "]";
		}
	}
}
