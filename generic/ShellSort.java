package laboratorium.lista4.generic;

import java.util.List;

import laboratorium.lista4.ShellSortGapSequenceGenerator;
import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;

public class ShellSort<T extends Comparable<? super T>> implements ListSorter<T>
{
	private static final GapSequence DEFAULT_GAP_SEQUENCE = GapSequence.KNUTH;
	private GapSequence gapSequenceType;
	private int[] gapSequenceArray;
	private int buffer;

	public ShellSort()
	{
		this(DEFAULT_GAP_SEQUENCE);
	}

	public ShellSort(GapSequence gapSequenceType)
	{
		this.gapSequenceType = gapSequenceType;
	}

	public List<T> sort(List<T> list)
	{
		setGapSequenceArray(list.size());
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

	private void setGapSequenceArray(int listSize)
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
	}

	public static GapSequence getDefaultGapSequence()
	{
		return DEFAULT_GAP_SEQUENCE;
	}
}
