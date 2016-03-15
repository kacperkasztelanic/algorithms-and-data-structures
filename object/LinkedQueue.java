package laboratorium.lista3.object;

import laboratorium.lista2.Node;
import laboratorium.lista3.exceptions.EmptyQueueException;
import laboratorium.lista3.exceptions.FullQueueException;

/**
 * 
 * @author Kacper
 *
 */
public class LinkedQueue implements Queue
{
	private static final int DEFAULT_MAX_SIZE = 10;
	private final int maxLength;
	private Node head = new Node(null);
	private int size;

	public LinkedQueue()
	{
		this(DEFAULT_MAX_SIZE);
	}

	public LinkedQueue(int maxLength)
	{
		this.maxLength = maxLength;
		clear();
	}

	public void enqueue(Object value)
	{
		if (size >= maxLength)
			throw new FullQueueException("Queue is full - max size: " + maxLength + ", size: " + size);
		Node last = head.getNext();
		if (last == null)
			head.setNext(new Node(value));
		else
		{
			while (last.getNext() != null)
				last = last.getNext();
			last.setNext(new Node(value));
		}
		size++;
	}

	public Object dequeue()
	{
		if (isEmpty())
			throw new EmptyQueueException();
		Node value = head.getNext();
		head.setNext(size > 1 ? value.getNext() : null);
		size--;
		return value;
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
			Node value = head.getNext();
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
