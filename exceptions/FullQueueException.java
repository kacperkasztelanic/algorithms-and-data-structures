package laboratorium.lista3.exceptions;

/**
 * 
 * @author Kacper
 *
 */
public class FullQueueException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public FullQueueException()
	{
		super("Queue is full.");
	}

	public FullQueueException(String text)
	{
		super(text);
	}
}
