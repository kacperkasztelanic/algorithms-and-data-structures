package laboratorium.lista3.generics;

import laboratorium.lista3.exceptions.EmptyQueueException;
import laboratorium.lista3.exceptions.FullQueueException;

/**
 * 
 * @author Kacper
 *
 * @param <T>
 */
public interface GenericQueue<T>
{
	void enqueue(T value) throws FullQueueException;

	T dequeue() throws EmptyQueueException;

	void clear();

	int size();

	boolean isEmpty();
}
