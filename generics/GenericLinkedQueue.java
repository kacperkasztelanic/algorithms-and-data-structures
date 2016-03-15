package laboratorium.lista3.generics;

import laboratorium.lista3.exceptions.EmptyQueueException;
import laboratorium.lista3.exceptions.FullQueueException;

/**
 * 
 * @author Kacper
 *
 * @param <T>
 */
public class GenericLinkedQueue<T> implements GenericQueue<T>
{
	private static final int DEFAULT_MAX_SIZE = 10;
	private final int maxLength;
	private GenericNode<T> head = new GenericNode<>(null);
	private int size;

	public GenericLinkedQueue()
	{
		this(DEFAULT_MAX_SIZE);
	}

	public GenericLinkedQueue(int maxLength)
	{
		this.maxLength = maxLength;
		clear();
	}

	public void enqueue(T value) throws FullQueueException
	{
		if (size >= maxLength)
			throw new FullQueueException("Queue is full - max size: " + maxLength + ", size: " + size);
		GenericNode<T> last = head.getNext();
		if (last == null)
			head.setNext(new GenericNode<>(value));
		else
		{
			while (last.getNext() != null)
				last = last.getNext();
			last.setNext(new GenericNode<>(value));
		}
		size++;
	}

	public T dequeue() throws EmptyQueueException
	{
		if (isEmpty())
			throw new EmptyQueueException();
		GenericNode<T> value = head.getNext();
		head.setNext(size > 1 ? value.getNext() : null);
		size--;
		return value.getValue();
	}

	public void clear()
	{
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

	public boolean isFull()
	{
		return size == maxLength;
	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder("[");
		if (!isEmpty())
		{
			GenericNode<T> value = head.getNext();
			while (value != null)
			{
				sb.append(value.getValue()).append(", ");
				value = value.getNext();
			}
			sb.setLength(sb.length() - 2);
		}
		sb.append("]").append(" Size: " + size + ", MaxSize: " + maxLength);
		return sb.toString();
	}
}
