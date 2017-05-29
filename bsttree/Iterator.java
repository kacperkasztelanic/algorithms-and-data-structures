package laboratorium.lista7.bsttree;

public interface Iterator<T>
{
	void first();

	void next();

	T current();

	boolean isDone();
}
