package laboratorium.lista3.generics;

public interface GenericQueue<T>
{
	void enqueue(T value);

	T dequeue();

	void clear();

	int size();

	boolean isEmpty();
}
