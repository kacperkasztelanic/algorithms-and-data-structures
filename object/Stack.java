package laboratorium.lista3.object;

/**
 * 
 * @author Kacper
 *
 */
public interface Stack
{
	void push(Object value);

	Object pop();

	Object peek();

	void clear();

	int size();

	boolean isEmpty();
}
