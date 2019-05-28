package crackingcoding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/** all permutations of string */
public class Task8p4 {

	public static void permutationsOfString(String subject, String result,
			long bitmap, List<String> results) {

		if (result.length() == subject.length()) {
			results.add(result);
			return;
		}

		long j = 1;
		for (int i = 0; i < subject.length(); i++, j <<= 1) {
			if ((bitmap & j) == 0) {
				permutationsOfString(subject, result + subject.charAt(i),
						bitmap | j, results);
			}
		}
	}

	public static long factorial(int n) {
		if (n <= 0)
			return 1;
		long r = 1;
		while (--n >= 0) {
			r *= (n + 1);
		}
		return r;
	}

	public static void main(String[] args) {
		List<String> results = new ArrayList<String>();
		String string = "abcd";

		permutationsOfString(string, "", 0, results);

		System.out.println(results);
		System.out.println("NO duplicates: "
				+ (new HashSet<String>(results).size() == results.size()));
		System.out.println("SIZE: " + results.size() + " , expected size = "
				+ factorial(string.length()));
	}
}
