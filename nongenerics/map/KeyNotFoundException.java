package laboratorium.lista6.nongenerics.map;

public class KeyNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public KeyNotFoundException()
	{
		super("Key not found.");
	}

	public KeyNotFoundException(String text)
	{
		super(text);
	}
}
