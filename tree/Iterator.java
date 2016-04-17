package laboratorium.lista7.tree;

public interface Iterator<T>
{
	void first();

	void next();

	T current();

	boolean isDone();
}
