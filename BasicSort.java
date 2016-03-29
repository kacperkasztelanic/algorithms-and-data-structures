package laboratorium.lista4;

public enum BasicSort
{

	BUBBLESORT("BubbleSort"), INSERTIONSORT("InsertionSort"), SELECTIONSORT("SelectionSort"), SHELLHIBBARD(
			"Shell (Hibbard)"), SHELLKNUTH("Shell (Knuth)"), SHELLSHELL("Shell (Shell)"), SHELLTOKUDA("Shell (Tokuda)");
	private String method;

	private BasicSort(String method)
	{
		this.method = method;
	}

	public String toString()
	{
		return method;
	}
}
