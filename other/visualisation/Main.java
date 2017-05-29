package laboratorium.lista7.other.visualisation;

import java.util.Arrays;
import java.util.Random;

public class Main
{
	public static int[] test(int size, int searchIndex)
	{
		Random random = new Random();
		int[] tab = new int[size];
		for (int i = 0; i < size; i++)
		{
			tab[i] = random.nextInt(size * 10);
		}
		Arrays.sort(tab);
		int[] returnTab = new int[2];
		long start;
		long end;
		start = System.nanoTime();
		for (int i = 0; i < 100000; i++)
		{
			BinarySearch.binarySearch(tab, tab[searchIndex]);
		}
		end = System.nanoTime();
		returnTab[0] = (int) ((end - start) / 10000);
		start = System.nanoTime();
		for (int i = 0; i < 100000; i++)
		{
			BinarySearch.interpolationSearch(tab, tab[searchIndex]);
		}
		end = System.nanoTime();
		returnTab[1] = (int) ((end - start) / 10000);
		return returnTab;
	}

	public static void test1()
	{
		for (int i = 16; i < 1000000; i *= 2)
		{
			int sumaB = 0;
			int sumaI = 0;
			final int tests = 100;
			for (int j = 0; j < tests; j++)
			{
				int[] result = test(i, (int) (((double) j + Math.random()) / tests * i));
				sumaB += result[0];
				sumaI += result[1];
			}
			System.out.println(i + "\t" + (double) sumaB / tests + "\t" + (double) sumaI / tests);
		}
	}

	public static void test2(int size)
	{
		Random random = new Random();
		Tree<Integer> tree = new Tree<>();
		for (int i = 0; i < size; i++)
		{
			tree.insert(random.nextInt(size * 5));
		}
		int[] findArray = new int[100000000];
		int index = 0;
		for (int i = 0; i < 1000; i++)
		{
			int find = random.nextInt(size * 5);
			for (int j = 0; j < 100000; j++)
			{
				findArray[index++] = find;
			}
		}

		long start, end;
		start = System.nanoTime();
		for (int i : findArray)
		{
			tree.find(i);
		}
		end = System.nanoTime();

		System.out.print(end - start + "\t");
		tree.DSW();

		start = System.nanoTime();
		for (int i : findArray)
		{
			tree.find(i);
		}
		end = System.nanoTime();

		System.out.println(end - start);
	}

	public static void test3()
	{
		Tree<Integer> tree = new Tree<>();
		tree.insert(2);
		tree.insert(7);
		tree.insert(5);
		tree.insert(2);
		tree.insert(6);
		tree.insert(9);
		tree.insert(5);
		tree.insert(8);
		tree.insert(4);
		tree.print();
		tree.DSW();
		tree.print();

		System.out.println("Pre order: ");
		tree.preOrder();
		System.out.println("\nIn order: ");
		tree.inOrder();
		System.out.println("\nPost order: ");
		tree.postOrder();
		System.out.println("\nLevel order: ");
		tree.levelOrder();
		/*
		 * System.out.println(tree.toString()); System.out.println();
		 * tree.delete(new Person("Ga", "b", 1));
		 * System.out.println(tree.toString()); System.out.println();
		 * System.out.println(tree.find(new Person("Fa", "b", 1)));
		 */
	}

	public static void main(String[] args)
	{
		if (args.length == 1)
		{
			if (args[0].equals("TEST1"))
			{
				test1();
			}
			else if (args[0].equals("TEST3"))
			{
				test3();
			}
		}
		else
		{
			// test1();
			// test3();
			for (int i = 1000; i < 20000000; i *= 2)
			{
				System.out.print("\t" + i + "\t");
				test2(i);
			}
		}
	}
}
