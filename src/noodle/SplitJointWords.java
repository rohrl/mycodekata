package noodle;

import java.util.ArrayList;
import java.util.LinkedList;

public class SplitJointWords {

	static final int ALPH_CHARS = ((int) 'z' - (int) 'a');

	static class TrieNode {
		boolean fullWord;
		char val;
		TrieNode[] children = new TrieNode[ALPH_CHARS];

		TrieNode(char c) {
			val = c;
		}
	}

	TrieNode[] trie = new TrieNode[ALPH_CHARS];
	ArrayList<LinkedList<String>> results = new ArrayList<LinkedList<String>>();

	public SplitJointWords(String[] dict) {
		createTrie(dict);
		// printTrie(trie, "");
	}

	void createTrie(String[] words) {
		for (String s : words)
			add(s);
	}

	int map(char c) {
		return c - 'a';
	}

	void add(String word) {
		// xxx empty strings, nulls etc
		int idx = map(word.charAt(0));
		TrieNode node = trie[idx];
		if (node == null) {
			node = new TrieNode(word.charAt(0));
			trie[idx] = node;
		}
		for (int i = 1; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[map(c)] == null) {
				node.children[map(c)] = new TrieNode(c);
			}
			node = node.children[map(c)];
		}
		node.fullWord = true;
	}

	void printTrie(TrieNode[] trie, String prefix) {
		for (TrieNode n : trie) {
			if (n != null) {
				if (n.fullWord)
					System.out.println(prefix + n.val);
				printTrie(n.children, prefix + n.val);
			}
		}
	}

	public ArrayList<LinkedList<String>> split(String word) {
		// null, empty ? etc
		recSplit(word, 0, new LinkedList<String>());
		return results;
	}

	private void recSplit(String word, int i, LinkedList<String> splited) {
		if (i == word.length()) {
			// hurray
			results.add(splited);
			return;
		}

		int j = i;
		TrieNode[] nodes = trie;
		while (j < word.length() && nodes[map(word.charAt(j))] != null) {

			char c = word.charAt(j);

			if (nodes[map(c)].fullWord) {
				LinkedList<String> newBranch = new LinkedList<String>(splited);
				newBranch.add(word.substring(i, j + 1));
				recSplit(word, j + 1, newBranch);
			}

			nodes = nodes[map(c)].children;
			j++;
		}
	}

	public static void main(String[] args) {

		String[] dictionary = new String[] { "hello", "world", "dupa", "cycki",
				"cyc", "ki" };

		SplitJointWords spliter = new SplitJointWords(dictionary);

		ArrayList<LinkedList<String>> results = spliter
				.split("helloworldcycki");

		for (LinkedList<String> spl : results)
			System.out.println(spl);
	}
}