package atlassian.findcommonancestor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MyFindCommonAncestor implements FindCommonAncestor {

	private Map<String, List<String>> parentsLookup = new HashMap<String, List<String>>();
	private Map<String, Integer> ageLookup = new HashMap<String, Integer>();
	private HashSet<String> visited = new HashSet<String>();

	private void firstDFS(String hash, int depth) {
		visited.add(hash);
		for (String parent : parentsLookup.get(hash))
			if (!visited.contains(parent))
				firstDFS(parent, depth + 1);
	}

	private String secondDFS(String hash) {
		if (visited.contains(hash))
			return hash;

		String minHash = null;
		for (String parent : parentsLookup.get(hash)) {
			String commonAnc = secondDFS(parent);
			if (minHash == null
					|| ageLookup.get(commonAnc) < ageLookup.get(minHash))
				minHash = commonAnc;
		}

		return minHash;
	}

	public String findCommmonAncestor(String[] commitHashes,
			String[][] parentHashes, String commitHash1, String commitHash2) {

		int age = 0;
		for (String h : commitHashes)
			ageLookup.put(h, age++);

		for (int i = 0; i < commitHashes.length; i++)
			parentsLookup.put(commitHashes[i],
					parentHashes[i] == null ? Collections.<String> emptyList()
							: Arrays.asList(parentHashes[i]));

		// mark parents of first
		firstDFS(commitHash1, 0);
		// iterate over second's parents until common ancestor found
		// returns most recent one
		String commonAnc = secondDFS(commitHash2);

		visited.clear();
		parentsLookup.clear();
		ageLookup.clear();

		return commonAnc;
	}
}