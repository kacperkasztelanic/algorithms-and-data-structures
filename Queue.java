package laboratorium.lista3;

public interface Queue
{
	void enqueue(Object value);

	Object dequeue();

	void clear();

	int size();

	boolean isEmpty();
}
