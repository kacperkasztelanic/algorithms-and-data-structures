package laboratorium.lista6.nongenerics.common;

public class ArrayIterator implements Iterator
{
	protected final Object[] array;
	protected final int first;
	protected final int last;
	protected int current;

	public ArrayIterator(Object[] array, int first, int length)
	{
		this.array = array;
		this.first = first;
		last = first + length - 1;
		current = -1;
	}

	public ArrayIterator(Object[] array)
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

	public Object current()
	{
		return array[current];
	}

	public boolean isDone()
	{
		return current < first || current > last;
	}

}
