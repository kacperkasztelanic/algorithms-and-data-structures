package laboratorium.lista7.other.visualisation;

public class Person implements Comparable<Person>
{
	private String name;
	private String firstName;
	private int age;

	public Person(String name, String firstName, int age)
	{
		this.name = name;
		this.firstName = firstName;
		this.age = age;
	}

	@Override
	public String toString()
	{
		return name + " " + firstName + " " + age;
	}

	@Override
	public int compareTo(Person o)
	{
		int nameCompaction = name.compareTo(o.name);
		if (nameCompaction == 0)
		{
			return firstName.compareTo(o.firstName);
		}
		else
		{
			return nameCompaction;
		}
	}
}