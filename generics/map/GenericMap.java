package laboratorium.lista6.generics.map;

import laboratorium.lista6.generics.map.iterators.GenericMapIterator;

public interface GenericMap<K, V>
{
	V get(K key);

	void put(K key, V value);

	boolean containsKey(K key);

	V remove(K key);

	void clear();

	int size();

	boolean isEmpty();

	GenericMapIterator<K, V> iterator();
}
