package laboratorium.lista6.generics.common;

public class GenericArrayIterator<T> implements GenericIterator<T>
{
	private final T[] array;
	private final int first;
	private final int last;
	private int current;

	public GenericArrayIterator(T[] array, int first, int length)
	{
		this.array = array;
		this.first = first;
		last = first + length - 1;
		current = -1;
	}

	public GenericArrayIterator(T[] array)
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

	public T current()
	{
		return array[current];
	}

	public boolean isDone()
	{
		return current < first || current > last;
	}

}
