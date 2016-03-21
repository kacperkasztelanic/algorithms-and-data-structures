package laboratorium.lista4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShellSortGapSequenceGenerator
{
	public enum GapSequence
	{
		TOKUDA, SHELL, HIBBARD, KNUTH;
	}

	private ShellSortGapSequenceGenerator()
	{
	}

	private static int[] sequenceIntArray(List<Integer> sequence)
	{
		return sequence.stream().mapToInt(i -> i).toArray();
	}

	public static int[] shell(int listSize)
	{
		List<Integer> sequence = new ArrayList<>();
		int gap = listSize;
		while ((gap /= 2) > 0)
			sequence.add(gap);
		if (sequence.isEmpty())
			sequence.add(1);
		return sequenceIntArray(sequence);
	}

	public static int[] hibbard(int listSize)
	{
		List<Integer> sequence = new ArrayList<>();
		int gap = 1;
		int k = 2;
		while (gap <= listSize / 3)
		{
			sequence.add(gap);
			gap = (int) Math.pow(2, k++) - 1;
		}
		if (sequence.isEmpty())
			sequence.add(1);
		Collections.reverse(sequence);
		return sequenceIntArray(sequence);
	}

	public static int[] knuth(int listSize)
	{
		List<Integer> sequence = new ArrayList<>();
		int maxGap = (int) Math.ceil(listSize / 3);
		int gap = 1;
		int k = 1;
		while (gap <= maxGap)
		{
			gap = (int) (Math.pow(3, k++) - 1) / 2;
			sequence.add(gap);
		}
		if (sequence.isEmpty())
			sequence.add(1);
		Collections.reverse(sequence);
		return sequenceIntArray(sequence);
	}

	public static int[] tokuda(int listSize)
	{
		List<Integer> sequence = new ArrayList<>();
		int gap = 1;
		int k = 2;
		while (gap <= listSize / 3)
		{
			sequence.add(gap);
			gap = (int) Math.ceil((Math.pow(9, k) - Math.pow(4, k)) / (5 * Math.pow(4, k - 1)));
			k++;
		}
		if (sequence.isEmpty())
			sequence.add(1);
		Collections.reverse(sequence);
		return sequenceIntArray(sequence);
	}
}