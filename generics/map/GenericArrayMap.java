package laboratorium.lista6.generics.map;

import laboratorium.lista6.generics.common.GenericIterator;
import laboratorium.lista6.generics.map.iterators.GenericArrayMapIterator;
import laboratorium.lista6.generics.map.iterators.GenericArrayMapKeyIterator;
import laboratorium.lista6.generics.map.iterators.GenericArrayMapValueIterator;
import laboratorium.lista6.generics.map.iterators.GenericMapIterator;

public class GenericArrayMap<K, V> implements GenericMap<K, V>
{
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private final int INITIAL_CAPACITY;
	private Object[] array;
	private int size;

	public GenericArrayMap()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public GenericArrayMap(int initialCapacity)
	{
		if (initialCapacity <= 0)
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		this.INITIAL_CAPACITY = initialCapacity;
		clear();
	}

	public V get(K key)
	{
		int index = indexOf(key);
		if (index != -1)
			return cast(index).getValue();
		return null;
	}

	public void put(K key, V value)
	{
		if (!containsKey(key))
		{
			ensureCapacity(size + 1);
			array[size] = new MapEntry<>(key, value);
			size++;
		}
		else
			cast(indexOf(key)).setValue(value);
	}

	public boolean containsKey(K key)
	{
		return indexOf(key) != -1;
	}

	public V remove(K key)
	{
		int index = indexOf(key);
		if (index < 0)
			return null;
		int copyFrom = index + 1;
		V value = cast(index).getValue();
		if (copyFrom < size)
			System.arraycopy(array, copyFrom, array, index, size - copyFrom);
		size--;
		return value;
	}

	public void clear()
	{
		array = new Object[INITIAL_CAPACITY];
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

	public GenericMapIterator<K, V> iterator()
	{
		return new GenericArrayMapIterator<K, V>(array, 0, size);
	}

	public GenericIterator<K> keyIterator()
	{
		return new GenericArrayMapKeyIterator<K, V>(array, 0, size);
	}

	public GenericIterator<V> valueIterator()
	{
		return new GenericArrayMapValueIterator<K, V>(array, 0, size);
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		if (!isEmpty())
		{
			GenericMapIterator<K, V> it = iterator();
			for (it.first(); !it.isDone(); it.next())
			{
				sb.append(it.current().getKey()).append("|").append(it.current().getValue()).append(", ");
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
			GenericArrayMap<K, V>.MapEntry<K, V>[] copy = (GenericArrayMap<K, V>.MapEntry<K, V>[]) new Object[capacity
					+ capacity / 2];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	private int indexOf(K key)
	{
		int index = 0;
		if (key == null)
			while (index < size && array[index] != null)
				index++;
		else
			while (index < size && !key.equals(cast(index).getKey()))
				index++;
		return index < size ? index : -1;
	}

	@SuppressWarnings("unchecked")
	private GenericArrayMap<K, V>.MapEntry<K, V> cast(int index)
	{
		return (GenericArrayMap<K, V>.MapEntry<K, V>) array[index];
	}

	public class MapEntry<L, W>
	{
		private L key;
		private W value;

		MapEntry(L key, W value)
		{
			this.key = key;
			this.value = value;
		}

		public L getKey()
		{
			return key;
		}

		public W getValue()
		{
			return value;
		}

		public void setValue(W value)
		{
			this.value = value;
		}

		public String toString()
		{
			return "[" + key + "|" + value + "]";
		}
	}
}
