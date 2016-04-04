package laboratorium.lista6.nongenerics.map.iterators;

import laboratorium.lista6.nongenerics.common.ArrayIterator;
import laboratorium.lista6.nongenerics.common.Iterator;
import laboratorium.lista6.nongenerics.map.ArrayMap;

public class ArrayMapValueIterator extends ArrayIterator implements Iterator
{
	public ArrayMapValueIterator(Object[] array, int first, int length)
	{
		super(array, first, length);
	}

	public ArrayMapValueIterator(Object[] array)
	{
		super(array);
	}

	public Integer current()
	{
		ArrayMap.MapEntry entry = (ArrayMap.MapEntry) array[current];
		return entry.getValue();
	}
}
