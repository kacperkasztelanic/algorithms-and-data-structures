package laboratorium.lista6.nongenerics.map;

import laboratorium.lista6.nongenerics.common.Iterator;

public interface Map
{
	int get(String key) throws KeyNotFoundException;

	void put(String key, int value);

	boolean containsKey(String key);

	int remove(String key) throws KeyNotFoundException;

	void clear();

	int size();

	boolean isEmpty();

	Iterator iterator();
}
