package laboratorium.lista3.other;

import java.util.Comparator;

import laboratorium.lista2.AbstractList;
import laboratorium.lista2.ArrayIterator;
import laboratorium.lista2.Iterator;

/**
 * 
 * @author Kacper Kasztelanic
 *
 */
public class ArrayList extends AbstractList
{
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	private final int INITIAL_CAPACITY;
	private final Comparator<Object> COMPARATOR;
	private Object[] array;
	private int size;

	public ArrayList()
	{
		this(DEFAULT_INITIAL_CAPACITY);
	}

	public ArrayList(Comparator<Object> comparator)
	{
		this(DEFAULT_INITIAL_CAPACITY, comparator);
	}

	public ArrayList(int initialCapacity)
	{
		this(initialCapacity, null);
	}

	public ArrayList(int initialCapacity, Comparator<Object> comparator)
	{
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
		this.INITIAL_CAPACITY = initialCapacity;
		this.COMPARATOR = comparator;
		clear();
	}

	public ArrayList(Object[] array)
	{
		this(array, null);
	}

	public ArrayList(Object[] array, Comparator<Object> comparator)
	{
		INITIAL_CAPACITY = size = array.length;
		clear();
		size = INITIAL_CAPACITY;
		this.COMPARATOR = comparator;
		for (int i = 0; i < size; i++)
		{
			this.array[i] = array[i];
		}
		sort(0);
	}

	public void add(Object value) throws IllegalArgumentException
	{
		insert(size, value);
	}

	public void delete(Object value)
	{
		int index = indexOf(value);
		if (index != -1)
			delete(index);
	}

	public void delete(int index) throws IndexOutOfBoundsException
	{
		checkOutOfBounds(index);
		int copyFrom = index + 1;
		if (copyFrom < size)
			System.arraycopy(array, copyFrom, array, index, size - copyFrom);
		size--;
	}

	public Object get(int index) throws IndexOutOfBoundsException
	{
		checkOutOfBounds(index);
		return array[index];
	}

	public void set(int index, Object value) throws IndexOutOfBoundsException
	{
		checkOutOfBounds(index);
		array[index] = value;
		sort(index);
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public int size()
	{
		return size;
	}

	public void clear()
	{
		array = new Object[INITIAL_CAPACITY];
		size = 0;
	}

	public Iterator iterator()
	{
		return new ArrayIterator(array, 0, size);
	}

	public Comparator<Object> comparator()
	{
		return COMPARATOR;
	}

	private int indexOf(Object value)
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

	private void ensureCapacity(int capacity)
	{
		if (array.length < capacity)
		{
			Object[] copy = new Object[capacity + capacity / 2];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	private void checkOutOfBounds(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	}

	@SuppressWarnings("unused")
	private void insertSorted(Object value) throws IllegalArgumentException
	{
		int indexToInsert = -1;
		if (COMPARATOR != null)
		{
			for (int i = 0; i < size && indexToInsert == -1; i++)
				if (COMPARATOR.compare(value, array[i]) > 0)
					indexToInsert = i;
			if (indexToInsert == -1)
				indexToInsert = size;
		}
		else if (value instanceof Comparable<?>)
		{
			@SuppressWarnings("unchecked") // Checked using instanceof operator
			Comparable<Object> cmp = (Comparable<Object>) value;
			for (int i = 0; i < size && indexToInsert == -1; i++)
				if (cmp.compareTo(array[i]) > 0)
					indexToInsert = i;
			if (indexToInsert == -1)
				indexToInsert = size;
		}
		else
			throw new IllegalArgumentException(
					"Objects stored in this list have to implement Comparable<?> interface or an instance of Comparator<?> must be provided.");
		insert(indexToInsert, value);
	}

	private void insert(int index, Object value) throws IndexOutOfBoundsException
	{
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + (size + 1));
		ensureCapacity(size + 1);
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = value;
		size++;
	}

	private void sort(int sortFromIndex) throws IllegalArgumentException
	{
		for (int j = sortFromIndex; j < size; j++)
		{
			for (int i = size - 1; i > 0; i--)
			{
				if (COMPARATOR != null)
				{
					if (COMPARATOR.compare(this.array[i], this.array[i - 1]) > 0)
					{
						Object buffer = this.array[i - 1];
						this.array[i - 1] = this.array[i];
						this.array[i] = buffer;
					}
				}
				else if (this.array[i] instanceof Comparable<?>)
				{
					@SuppressWarnings("unchecked") // Checked using instaceof
													// operator
					Comparable<Object> val1 = (Comparable<Object>) this.array[i];
					@SuppressWarnings("unchecked") // Checked using instaceof
													// operator
					Comparable<Object> val2 = (Comparable<Object>) this.array[i - 1];
					if (val1.compareTo(val2) > 0)
					{
						this.array[i - 1] = val1;
						this.array[i] = val2;
					}
				}
				else
				{
					throw new IllegalArgumentException(
							"Objects stored in this list have to implement Comparable<?> interface or an instance of Comparator<?> must be provided.");
				}
			}
		}
	}
}
