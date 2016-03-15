package laboratorium.lista3.object;

import laboratorium.lista3.exceptions.EmptyQueueException;
import laboratorium.lista3.exceptions.FullQueueException;

/**
 * 
 * @author Kacper
 *
 */
public interface Queue
{
	void enqueue(Object value) throws FullQueueException;

	Object dequeue() throws EmptyQueueException;

	void clear();

	int size();

	boolean isEmpty();
}
