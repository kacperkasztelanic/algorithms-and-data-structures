package laboratorium.lista3.generics;

import laboratorium.lista3.exceptions.EmptyStackException;

/**
 * 
 * @author Kacper
 *
 * @param <T>
 */
public class GenericArrayStack<T> implements GenericStack<T>
{
	private static final int DEFAULT_STACK_SIZE = 10;
	private T[] array;
	private int size;
	private int top;

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
		if (top + 1 >= array.length)
		{
			array[0] = value;
			top = 0;
		}
		else
		{
			array[top + 1] = value;
			if (size != array.length)
				size++;
			top++;
		}
	}

	public T pop() throws EmptyStackException
	{
		if (size <= 0 || top == -1)
			throw new EmptyStackException();
		T value = array[top];
		top--;
		size--;
		if (top < 0 && size > 0)
			top = array.length - 1;
		return value;
	}

	public T peek() throws EmptyStackException
	{
		if (top < 0)
			throw new EmptyStackException();
		return array[top];
	}

	public void clear()
	{
		size = 0;
		top = -1;
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
		return top;
	}

	public String toString()
	{
		int localSize = size;
		int localTop = top;
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty())
		{
			while (localSize-- > 0)
			{
				sb.append(array[localTop--]).append(", ");
				if (localTop < 0 && localSize > 0)
					localTop = array.length - 1;
			}
			sb.setLength(sb.length() - 2);
		}
		sb.append("]").append(" Size: " + size + ", MaxSize: " + array.length);
		return sb.toString();
	}
}
