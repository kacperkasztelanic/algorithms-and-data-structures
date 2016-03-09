package laboratorium.lista3.generics;

import laboratorium.lista3.EmptyStackException;

public class GenericArrayStack<T> implements GenericStack<T>
{
	private static final int DEFAULT_STACK_SIZE = 10;
	private T[] array;
	private int size;
	private int getTop;

	public GenericArrayStack()
	{
		this(DEFAULT_STACK_SIZE);
	}

	@SuppressWarnings("unchecked")
	public GenericArrayStack(int size)
	{
		array = (T[]) new Object[size];
		clear();
	}

	public void push(T value)
	{
		if (getTop + 1 >= array.length)
		{
			array[0] = value;
			getTop = 0;
		}
		else
		{
			array[getTop + 1] = value;
			if (size != array.length)
				size++;
			getTop++;
		}
	}

	public T pop()
	{
		if (size <= 0 || getTop == -1)
			throw new EmptyStackException();
		T value = array[getTop];
		getTop--;
		size--;
		if (getTop < 0 && size > 0)
			getTop = array.length - 1;
		return value;
	}

	public T peek()
	{
		if (getTop < 0)
			throw new EmptyStackException();
		return array[getTop];
	}

	public void clear()
	{
		size = 0;
		getTop = -1;
	}

	public int size()
	{
		return size;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public int getTop()
	{
		return getTop;
	}
}
