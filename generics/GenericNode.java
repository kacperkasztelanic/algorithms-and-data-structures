package laboratorium.lista3.generics;

/**
 * 
 * @author Kacper
 *
 * @param <T>
 */
public class GenericNode<T>
{
	private T value;
	private GenericNode<T> next;

	public GenericNode(T value)
	{
		this(value, null);
	}

	public GenericNode(T value, GenericNode<T> next)
	{
		this.value = value;
		this.next = next;
	}

	public T getValue()
	{
		return value;
	}

	public GenericNode<T> getNext()
	{
		return next;
	}

	public void setNext(GenericNode<T> next)
	{
		this.next = next;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public String toString()
	{
		return value.toString();
	}
}
