package google.trainingOnSite;

import java.util.Arrays;
import java.util.Iterator;

import fb.bst.BST;
import fb.bst.BinaryTree;
import fb.bst.BinaryTree.Node;
import fb.bst.Nnode;
import fb.bst.Trees;

public class NTreeAsBiTree {

	static Node<Integer> encode(Nnode<Integer> root) {

		if (root == null)
			throw new IllegalArgumentException();

		Node<Integer> newRoot = new Node<Integer>(null, root.val);

		if (root.isLeaf())
			return newRoot;

		Iterator<Nnode<Integer>> it = root.children.iterator();
		Node<Integer> left = encode(it.next());
		Node<Integer> leftmost = left;
		while (it.hasNext()) {
			left.right = encode(it.next());
			left.right.parent = left;

			left = left.right;
		}

		newRoot.left = leftmost;
		leftmost.parent = newRoot;

		return newRoot;
	}

	static Nnode<Integer> decode(Node<Integer> root) {

		if (root == null) {
			throw new RuntimeException();
		}

		Nnode<Integer> newRoot = new Nnode<Integer>();
		newRoot.val = root.val;

		if (root.left == null) {
			return newRoot;
		}

		Node<Integer> n = root.left;
		while (n != null) {
			newRoot.children.add(decode(n));
			n = n.right;
		}

		return newRoot;
	}

	public static void main(String[] args) {

		BST<Integer> bst = new BST<Integer>(Arrays.asList(1, 2, 5, 0, -1, 6, 7,
				8, 9, 3, 4));

		// add new root to ensure that root has no right children
		BinaryTree<Integer> btree = Trees.createBinaryTree(new Node<Integer>(
				null, 100));
		btree.getRoot().left = bst.getRoot();
		bst.getRoot().parent = btree.getRoot();

		BinaryTree<Integer> newTree = Trees
				.createBinaryTree(encode(decode(btree.getRoot())));

		System.out.println(Trees.toString(btree.getRoot()));
		System.out.println(Trees.toString(newTree.getRoot()));

		System.out.println(Trees.compareTrees(btree.getRoot(),
				newTree.getRoot()));
	}

}
