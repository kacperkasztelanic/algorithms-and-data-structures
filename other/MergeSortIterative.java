package laboratorium.lista5.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import laboratorium.lista5.generic.ListSorter;

public class MergeSortIterative<T extends Comparable<T>> implements ListSorter<T>
{
	public List<T> sort(List<T> list)
	{
		return mergeSublists(createQueue(list));
	}

	private List<T> mergeSublists(Queue<List<T>> q)
	{
		while (q.size() > 1)
			q.offer(mergePair((List<T>) q.poll(), (List<T>) q.poll()));
		return (List<T>) q.poll();
	}

	private Queue<List<T>> createQueue(List<T> list)
	{
		Queue<List<T>> result = new LinkedList<>();
		Iterator<T> i = list.iterator();
		while (i.hasNext())
		{
			List<T> singletonList = new ArrayList<>(1);
			singletonList.add(i.next());
			result.offer(singletonList);
		}
		return result;
	}

	private List<T> mergePair(List<T> left, List<T> right)
	{
		List<T> result = new ArrayList<>(left.size() + right.size());
		ListIterator<T> l = left.listIterator();
		ListIterator<T> r = right.listIterator();
		while (l.hasNext() && r.hasNext())
		{
			T o1 = l.next();
			T o2 = r.next();
			if (o1.compareTo(o2) <= 0)
			{
				result.add(o1);
				r.previous();
			}
			else
			{
				result.add(o2);
				l.previous();
			}
		}
		while (l.hasNext())
			result.add(l.next());
		while (r.hasNext())
			result.add(r.next());
		return result;
	}
}
