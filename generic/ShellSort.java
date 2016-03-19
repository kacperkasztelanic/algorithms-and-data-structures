package laboratorium.lista4.generic;

import java.util.List;

import laboratorium.lista4.ShellSortGapSequenceGenerator;
import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;

public class ShellSort<T extends Comparable<T>> implements ListSorter<T>
{
	private GapSequence gapSequenceType;
	private int[] gapSequenceArray;
	private int buffer;
	private int listSize;

	public ShellSort(GapSequence gapSequenceType, int listSize)
	{
		this.gapSequenceType = gapSequenceType;
		this.listSize = listSize;
		setGapSequence(listSize);
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

	private void setGapSequence(int length)
	{
		switch (gapSequenceType)
		{
			case TOKUDA:
				gapSequenceArray = ShellSortGapSequenceGenerator.tokuda(length);
				break;
			case PRATT:
				gapSequenceArray = ShellSortGapSequenceGenerator.pratt(length);
				break;
			case HIBBARD:
				gapSequenceArray = ShellSortGapSequenceGenerator.hibbard(length);
				break;
			case SHELL:
				gapSequenceArray = ShellSortGapSequenceGenerator.shell(length);
				break;
			case KNUTH:
				gapSequenceArray = ShellSortGapSequenceGenerator.knuth(length);
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
	}

	public int getListSize()
	{
		return listSize;
	}

	public void setListSize(int listSize)
	{
		this.listSize = listSize;
	}
}
