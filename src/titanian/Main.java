package titanian;
import java.io.UnsupportedEncodingException;

import titanian.findcommonancestor.MyFindCommonAncestor;


public class Main {

	public static synchronized boolean foo() {
		try {
			return true;
		} finally {
			return false;
		}
	}

	public static void main(String[] args) {

		String[] commits = { "G", "F", "E", "D", "C", "B", "A" };
		String[][] parents = { { "F", "D" }, { "E", "C" }, { "B" }, { "C" },
				{ "B" }, { "A" }, null };
		String commit1 = "D";
		String commit2 = "F";

		MyFindCommonAncestor tmp = new MyFindCommonAncestor();

		System.out.println(tmp.findCommmonAncestor(commits, parents, commit2,
				commit1));

		System.out.println(tmp.findCommmonAncestor(commits, parents, "A", "D"));

		// for (String s : map.keySet()) {
		// System.out.println("|" + s + "|" + map.get(s) + "|");
		// }

		try {
			System.out.println(java.net.URLDecoder.decode("\u00A4", "US-ASCII"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

/**
 * Implement a method decodes a String a returns a corresponding Map. The
 * accepted format of the String is a URL parameter string
 * (key=value&key=value&...). Empty keys and values are allowed, but the equals
 * sign must be present (e.g. "=value", "key="). If the given String is empty,
 * an empty Map should be returned. If the given String is null, null should be
 * returned. If the given String has an invalid format, an
 * IllegalArgumentException should be thrown. Your implementation must implement
 * the MapDecoder interface.
 */
