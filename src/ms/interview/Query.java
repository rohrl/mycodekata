package ms.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Query {

	private static List<String> allSubqueries(String s) {

		String[] split = s.split(" ");
		List<String> list = new LinkedList<String>();

		for (int i = 0; i < split.length; i++) {
			String sub = "";
			for (int j = i; j < split.length; j++) {
				sub += split[j];
				list.add(sub);
				sub += " ";
			}
		}

		return list;
	}

	private static HashSet<String> db = new HashSet<String>(
			Arrays.asList(new String[] { "merc benz", "bmw", "audi" }));

	private static boolean containsDatabasePhrase(String query) {
		for (String s : allSubqueries(query))
			if (db.contains(s))
				return true;
		return false;
	}

	public static void main(String[] args) {
		System.out
				.println(containsDatabasePhrase("cheapest merc benz in cracow") == true);
		System.out
				.println(containsDatabasePhrase("cheapest merc in cracow") == false);
		System.out
				.println(containsDatabasePhrase("cheapest benz in cracow") == false);
		System.out.println(containsDatabasePhrase("merc benz") == true);
		System.out.println(containsDatabasePhrase("bmw") == true);
		System.out.println(containsDatabasePhrase("bmw in warsaw") == true);
		System.out.println(containsDatabasePhrase("warsaw bmw ") == true);
		System.out.println(containsDatabasePhrase("warsaw bmw_m5 ") == false);
	}
}
