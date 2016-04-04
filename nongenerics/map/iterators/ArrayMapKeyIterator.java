package laboratorium.lista6.nongenerics.map.iterators;

import laboratorium.lista6.nongenerics.common.ArrayIterator;
import laboratorium.lista6.nongenerics.common.Iterator;
import laboratorium.lista6.nongenerics.map.ArrayMap;

public class ArrayMapKeyIterator extends ArrayIterator implements Iterator
{
	public ArrayMapKeyIterator(Object[] array, int first, int length)
	{
		super(array, first, length);
	}

	public ArrayMapKeyIterator(Object[] array)
	{
		super(array);
	}

	public String current()
	{
		ArrayMap.MapEntry entry = (ArrayMap.MapEntry) array[current];
		return entry.getKey();
	}
}
