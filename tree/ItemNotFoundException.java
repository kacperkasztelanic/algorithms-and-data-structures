package laboratorium.lista7.tree;

public class ItemNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException()
	{
		super("Item not found.");
	}

	public ItemNotFoundException(String text)
	{
		super("Item not found: " + text);
	}
}
