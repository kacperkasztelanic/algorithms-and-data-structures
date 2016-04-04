package laboratorium.lista6.nongenerics.set;

import laboratorium.lista6.nongenerics.common.Iterator;

public interface Set
{
	void add(String value);

	boolean contains(String value);

	void remove(String value);

	void clear();

	int size();

	boolean isEmpty();

	Iterator iterator();
}
