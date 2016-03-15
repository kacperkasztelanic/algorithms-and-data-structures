package laboratorium.lista3.object;

import laboratorium.lista3.exceptions.EmptyStackException;

/**
 * 
 * @author Kacper
 *
 */
public interface Stack
{
	void push(Object value);

	Object pop() throws EmptyStackException;

	Object peek() throws EmptyStackException;

	void clear();

	int size();

	boolean isEmpty();
}
