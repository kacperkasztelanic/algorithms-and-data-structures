package laboratorium.lista6.generics.set;

import laboratorium.lista6.generics.common.GenericIterator;

public interface GenericSet<T>
{
	void add(T value);

	boolean contains(T value);

	void remove(T value);

	void clear();

	int size();

	boolean isEmpty();

	GenericIterator<T> iterator();
}
