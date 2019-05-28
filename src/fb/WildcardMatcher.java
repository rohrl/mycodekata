package fb;

import java.util.Scanner;

// TODO
public class WildcardMatcher {

	/** pattern allows * and ? */
	public static boolean matches(String pattern, String input) {

		return false;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String pattern = scanner.nextLine();
		String input = scanner.nextLine();
		System.out.println(matches(pattern, input));
	}
}
