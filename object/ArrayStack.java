package laboratorium.lista3.object;

import laboratorium.lista3.exceptions.EmptyStackException;

/**
 * 
 * @author Kacper
 *
 */
public class ArrayStack implements Stack
{
	private static final int DEFAULT_STACK_SIZE = 10;
	private Object[] array;
	private int size;
	protected int top;

	public ArrayStack()
	{
		this(DEFAULT_STACK_SIZE);
	}

	public ArrayStack(int size)
	{
		array = new Object[size];
		clear();
	}

	public void push(Object value)
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

	public Object pop()
	{
		if (size <= 0 || top == -1)
			throw new EmptyStackException();
		Object value = array[top];
		top--;
		size--;
		if (top < 0 && size > 0)
			top = array.length - 1;
		return value;
	}

	public Object peek()
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
}
