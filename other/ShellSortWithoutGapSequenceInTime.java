package laboratorium.lista4.other;

import java.util.List;

import laboratorium.lista4.ShellSortGapSequenceGenerator;
import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;
import laboratorium.lista4.generic.ListSorter;

public class ShellSortWithoutGapSequenceInTime<T extends Comparable<? super T>> implements ListSorter<T>
{
	private static final GapSequence DEFAULT_GAP_SEQUENCE = GapSequence.KNUTH;
	private static final int DEFAULT_LIST_SIZE = 1000;
	private GapSequence gapSequenceType;
	private int[] gapSequenceArray;
	private int buffer;
	private int listSize;

	public ShellSortWithoutGapSequenceInTime()
	{
		this(DEFAULT_GAP_SEQUENCE);
	}

	public ShellSortWithoutGapSequenceInTime(GapSequence gapSequenceType)
	{
		this(gapSequenceType, DEFAULT_LIST_SIZE);
	}

	public ShellSortWithoutGapSequenceInTime(GapSequence gapSequenceType, int listSize)
	{
		this.gapSequenceType = gapSequenceType;
		this.listSize = listSize;
		setGapSequenceArray();
	}

	public List<T> sort(List<T> list)
	{
		for (int i = 0; i < gapSequenceArray.length; i++)
		{
			for (int j = gapSequenceArray[i]; j < list.size(); j++)
			{
				buffer = gapSequenceArray[i];
				int position = j;
				T temp;
				T value = list.get(position);
				while (position >= buffer && value.compareTo(temp = list.get(position - buffer)) < 0)
				{
					list.set(position, temp);
					position -= buffer;
				}
				list.set(position, value);
			}
		}
		return list;
	}

	private void setGapSequenceArray()
	{
		gapSequenceArray = ShellSortGapSequenceGenerator.generateGapSequence(gapSequenceType, listSize);
	}

	public int[] getGapSequenceArray()
	{
		return gapSequenceArray;
	}

	public GapSequence getGapSequenceType()
	{
		return gapSequenceType;
	}

	public void setGapSequenceType(GapSequence gapSequenceType)
	{
		this.gapSequenceType = gapSequenceType;
		setGapSequenceArray();
	}

	public int getListSize()
	{
		return listSize;
	}

	public void setListSize(int listSize)
	{
		this.listSize = listSize;
		setGapSequenceArray();
	}

	public static GapSequence getDefaultGapSequence()
	{
		return DEFAULT_GAP_SEQUENCE;
	}

	public static int getDefaultListSize()
	{
		return DEFAULT_LIST_SIZE;
	}
}
