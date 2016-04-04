package laboratorium.lista6.generics.map.iterators;

import laboratorium.lista6.generics.map.GenericArrayMap;

public interface GenericMapIterator<K, V>
{
	void first();

	void next();

	GenericArrayMap<K, V>.MapEntry<K, V> current();

	boolean isDone();

	void last();

	void previous();
}
