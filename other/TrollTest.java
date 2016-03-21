package laboratorium.lista4.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;
import laboratorium.lista4.generic.BubbleSort;
import laboratorium.lista4.generic.InsertionSort;
import laboratorium.lista4.generic.ListSorter;
import laboratorium.lista4.generic.SelectionSort;
import laboratorium.lista4.generic.ShellSort;

public class TrollTest
{
	public static int n = 10;

	public static void trollBubble()
	{
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(random.nextInt(100));
		System.out.println("Unsorted: " + list + " Size: " + list.size());
		ListSorter<Integer> sorter = new BubbleSort<>();
		list = sorter.sort(list);
		System.out.println("Sorted: " + list + " Size: " + list.size());
	}

	public static void trollSelect()
	{
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(random.nextInt(100));
		System.out.println("Unsorted: " + list + " Size: " + list.size());
		ListSorter<Integer> sorter = new SelectionSort<>();
		list = sorter.sort(list);
		System.out.println("Sorted: " + list + " Size: " + list.size());
	}

	public static void trollInsert()
	{
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(random.nextInt(100));
		System.out.println("Unsorted: " + list + " Size: " + list.size());
		ListSorter<Integer> sorter = new InsertionSort<>();
		list = sorter.sort(list);
		System.out.println("Sorted: " + list + " Size: " + list.size());
	}

	public static void trollShell()
	{
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++)
			list.add(random.nextInt(100));
		System.out.println("Unsorted: " + list + " Size: " + list.size());
		ShellSort<Integer> sorter = new ShellSort<>(GapSequence.TOKUDA, list.size());
		// sorter.setGapSequenceType(GapSequence.TOKUDA);
		// sorter.setGapSequenceType(GapSequence.SHELL);
		// sorter.setGapSequenceType(GapSequence.PRATT);
		list = sorter.sort(list);
		System.out.println("Sorted: " + list + " Size: " + list.size());
		System.out.println(Arrays.toString(sorter.getGapSequenceArray()));
		System.out.println(sorter.getGapSequenceType());
	}

	public static void main(String[] args)
	{
		trollShell();
		trollBubble();
		trollInsert();
		trollSelect();
	}

}
