package laboratorium.lista3.exceptions;

/**
 * 
 * @author Kacper
 *
 */
public class EmptyQueueException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public EmptyQueueException()
	{
		super("Queue is empty.");
	}

	public EmptyQueueException(String text)
	{
		super(text);
	}
}
