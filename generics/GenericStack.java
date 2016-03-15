package laboratorium.lista3.generics;

import laboratorium.lista3.exceptions.EmptyStackException;

/**
 * 
 * @author Kacper
 *
 * @param <T>
 */
public interface GenericStack<T>
{
	void push(T value);

	T pop() throws EmptyStackException;

	T peek() throws EmptyStackException;

	void clear();

	int size();

	boolean isEmpty();
}
