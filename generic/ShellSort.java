package laboratorium.lista4.generic;

import java.util.List;

import laboratorium.lista4.ShellSortGapSequenceGenerator;
import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;

public class ShellSort<T extends Comparable<T>> implements ListSorter<T>
{
	private static final GapSequence DEFAULT_GAP_SEQUENCE = GapSequence.TOKUDA;
	private static final int DEFAULT_LIST_SIZE = 1000;
	private GapSequence gapSequenceType;
	private int[] gapSequenceArray;
	private int buffer;
	private int listSize;

	public ShellSort()
	{
		this(DEFAULT_GAP_SEQUENCE);
	}

	public ShellSort(GapSequence gapSequenceType)
	{
		this(gapSequenceType, DEFAULT_LIST_SIZE);
	}

	public ShellSort(GapSequence gapSequenceType, int listSize)
	{
		this.gapSequenceType = gapSequenceType;
		this.listSize = listSize;
		setGapSequence();
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

	private void setGapSequence()
	{
		switch (gapSequenceType)
		{
			case TOKUDA:
				gapSequenceArray = ShellSortGapSequenceGenerator.tokuda(listSize);
				break;
			case HIBBARD:
				gapSequenceArray = ShellSortGapSequenceGenerator.hibbard(listSize);
				break;
			case SHELL:
				gapSequenceArray = ShellSortGapSequenceGenerator.shell(listSize);
				break;
			case KNUTH:
				gapSequenceArray = ShellSortGapSequenceGenerator.knuth(listSize);
				break;
		}
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
		setGapSequence();
	}

	public int getListSize()
	{
		return listSize;
	}

	public void setListSize(int listSize)
	{
		this.listSize = listSize;
		setGapSequence();
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
