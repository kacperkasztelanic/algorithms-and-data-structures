package laboratorium.lista4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import laboratorium.lista3.Vehicle;
import laboratorium.lista4.ShellSortGapSequenceGenerator.GapSequence;
import laboratorium.lista4.generic.ShellSort;

public class TrollTestShellSort
{
	public static void main(String[] args)
	{
		Random random = new Random();
		List<Vehicle> list = new ArrayList<>();
		for (int i = 0; i < 10; i++)
			list.add(new Vehicle(i, random.nextInt(20) + 1980));
		System.out.println("Unsorted: " + list + " Size: " + list.size());
		ShellSort<Vehicle> sorter = new ShellSort<>(GapSequence.TOKUDA, list.size());
		// sorter.setGapSequenceType(GapSequence.TOKUDA);
		// sorter.setGapSequenceType(GapSequence.SHELL);
		// sorter.setGapSequenceType(GapSequence.PRATT);
		list = sorter.sort(list);
		System.out.println("Sorted: " + list + " Size: " + list.size());
		System.out.println(Arrays.toString(sorter.getGapSequenceArray()));
		System.out.println(sorter.getGapSequenceType());
	}

}
