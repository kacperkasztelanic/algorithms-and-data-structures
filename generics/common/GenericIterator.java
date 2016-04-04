package laboratorium.lista6.generics.common;

public interface GenericIterator<T>
{
	void first();

	void next();

	T current();

	boolean isDone();

	void last();

	void previous();
}
