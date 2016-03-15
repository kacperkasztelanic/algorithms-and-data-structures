package laboratorium.lista3.exceptions;

/**
 * 
 * @author Kacper
 *
 */
public class EmptyStackException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public EmptyStackException()
	{
		super("Stack is empty");
	}

	public EmptyStackException(String text)
	{
		super(text);
	}
}
