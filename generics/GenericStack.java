package laboratorium.lista3.generics;

public interface GenericStack<T>
{
	void push(T value);

	T pop();

	T peek();

	void clear();

	int size();

	boolean isEmpty();
}
