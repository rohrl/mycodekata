package google.trainingOnSite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fb.bst.BinaryTree.Node;

/**
 * http://en.wikipedia.org/wiki/Catalan_number
 */
public class CatalanNumbers {

	public static List<Node<Object>> allBinaryTreesOfNLeaves(int n) {

		if (n == 1) {
			return Collections.<Node<Object>> singletonList(new Node<Object>(
					null, null));
		}

		List<Node<Object>> results = new ArrayList<Node<Object>>();

		// see Catalan Number recursive equation for explanation of below algo:
		for (int i = 1; i < n; i++) {
			for (Node<Object> left : allBinaryTreesOfNLeaves(i)) {
				for (Node<Object> right : allBinaryTreesOfNLeaves(n - i)) {
					results.add(merge(left, right));
				}
			}
		}

		return results;
	}

	private static Node<Object> merge(Node<Object> left, Node<Object> right) {
		Node<Object> parent = new Node<Object>(null, null);
		parent.left = left;
		parent.right = right;
		left.parent = right.parent = parent;
		return parent;
	}

	/** this represents tree as a arithmetic formula */
	public static String printTreeLeafs(Node<?> tree) {
		if (!tree.hasChildren())
			return "x";
		else
			return "(" + printTreeLeafs(tree.left) + "*"
					+ printTreeLeafs(tree.right) + ")";
	}

	/**
	 * this maps a tree to sequence of N pairs of braces where N = # of inner
	 * nodes in tree
	 */
	public static String asBraceSeq(Node<?> tree) {
		if (!tree.hasChildren())
			return "";
		return "(" + (tree.left.hasChildren() ? asBraceSeq(tree.left) : "") + ")"
				+ (tree.right.hasChildren() ? asBraceSeq(tree.right) : "");
	}

	public static void main(String[] args) {

		for (int n = 1; n < 7; n++) {
			// size = Catalan Number
			// also the number of possible N pairs of braces
			List<Node<Object>> trees = allBinaryTreesOfNLeaves(n);
			System.out.println(trees.size() + " possible trees with " + n
					+ " leafes:");
			for (Node<Object> t : trees)
				System.out.print(printTreeLeafs(t) + " ");
			System.out.println();
			for (Node<Object> t : trees)
				System.out.print(asBraceSeq(t) + " ");
			System.out.println();
		}

	}
}
