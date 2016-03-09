package laboratorium.lista3;

/**
 * 
 * @author Kacper Kasztelanic
 *
 */
public class Vehicle implements Comparable<Vehicle>
{
	int yearOfConstruction;
	int id;

	public Vehicle(int id, int yearOfConstruction)
	{
		this.id = id;
		this.yearOfConstruction = yearOfConstruction;
	}

	public Vehicle(Vehicle other)
	{
		this.yearOfConstruction = other.yearOfConstruction;
		this.id = other.id;
	}

	public int getYearOfConstruction()
	{
		return yearOfConstruction;
	}

	public void setYearOfConstruction(int yearOfConstruction)
	{
		this.yearOfConstruction = yearOfConstruction;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + yearOfConstruction;
		return result;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (yearOfConstruction != other.yearOfConstruction)
			return false;
		return true;
	}

	public String toString()
	{
		return String.format("Id: %d|YoM: %d", id, yearOfConstruction);
	}

	public int compareTo(Vehicle o)
	{
		int result = yearOfConstruction - o.yearOfConstruction;
		return result != 0 ? result : id - o.id;
	}
}
