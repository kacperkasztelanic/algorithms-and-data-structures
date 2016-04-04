package laboratorium.lista6;

import laboratorium.lista6.generics.map.GenericArrayMap;
import laboratorium.lista6.generics.set.GenericArraySet;
import laboratorium.lista6.generics.set.GenericSet;
import laboratorium.lista6.nongenerics.map.ArrayMap;
import laboratorium.lista6.nongenerics.map.Map;
import laboratorium.lista6.nongenerics.set.ArraySet;
import laboratorium.lista6.nongenerics.set.Set;

public class Test
{
	public static String Set()
	{
		Set set = new ArraySet();
		StringBuilder sb = new StringBuilder();
		sb.append(set.toString()).append(System.lineSeparator());
		set.add("qwerty");
		set.add("asdfgh");
		set.add("qwerty");
		sb.append(set.toString()).append(System.lineSeparator());
		set.remove("asdfgh");
		sb.append(set.toString()).append(System.lineSeparator());
		set.remove("qwerty");
		sb.append(set.toString()).append(System.lineSeparator());
		return sb.toString();
	}

	public static String GenericSet()
	{
		GenericSet<String> set = new GenericArraySet<>();
		StringBuilder sb = new StringBuilder();
		sb.append(set.toString()).append(System.lineSeparator());
		set.add("qwerty");
		set.add("asdfgh");
		set.add("qwerty");
		sb.append(set.toString()).append(System.lineSeparator());
		set.remove("asdfgh");
		sb.append(set.toString()).append(System.lineSeparator());
		set.remove("qwerty");
		sb.append(set.toString()).append(System.lineSeparator());
		return sb.toString();
	}

	public static String Map()
	{
		Map map = new ArrayMap();
		StringBuilder sb = new StringBuilder();
		sb.append(map.toString()).append(System.lineSeparator());
		map.put("qwerty", 1);
		map.put("asdfgh", 2);
		map.put("qwerty", 3);
		sb.append(map.toString()).append(System.lineSeparator());
		map.remove("asdfgh");
		sb.append(map.toString()).append(System.lineSeparator());
		map.remove("qwerty");
		sb.append(map.toString()).append(System.lineSeparator());
		return sb.toString();
	}

	public static String GenericMap()
	{
		GenericArrayMap<String, Integer> map = new GenericArrayMap<>();
		StringBuilder sb = new StringBuilder();
		sb.append(map.toString()).append(System.lineSeparator());
		map.put("qwerty", 1);
		map.put("asdfgh", 2);
		map.put("qwerty", 3);
		sb.append(map.toString()).append(System.lineSeparator());
		map.remove("asdfgh");
		sb.append(map.toString()).append(System.lineSeparator());
		map.remove("qwerty");
		sb.append(map.toString()).append(System.lineSeparator());
		return sb.toString();
	}

	public static void main(String[] args)
	{
		System.out.println(Set());
		System.out.println(GenericSet());
		System.out.println(Map());
		System.out.println(GenericMap());
	}
}
