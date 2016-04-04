package laboratorium.lista6.nongenerics.common;

public interface Iterator
{
	void first();

	void next();

	Object current();

	boolean isDone();

	void last();

	void previous();
}
