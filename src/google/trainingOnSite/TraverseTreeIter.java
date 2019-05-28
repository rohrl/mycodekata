package google.trainingOnSite;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import fb.bst.BST;
import fb.bst.BinaryTree.Node;
import fb.bst.Trees;
import fb.bst.Trees.TraverseOrder;
import fb.bst.Trees.TraverseVisitor;

public class TraverseTreeIter {

	/** (?) tail recursion - no need of stack - ugly */
	public static void preorderNoStack(BST<Integer> bst) {

		Node<Integer> n = bst.getRoot();

		while (n != null) {
			while (n.left != null) {
				System.out.print(n.val + " ");
				n = n.left;
			}
			System.out.print(n.val + " ");
			while (n.right == null) {
				while (n.parent != null && n.parent.right == n)
					n = n.parent;
				if (n.parent == null)
					return;
				n = n.parent;
			}
			n = n.right;
		}
	}

	public static void preorder(BST<Integer> bst) {

		Queue<Node<Integer>> stack = Collections
				.asLifoQueue(new LinkedList<Node<Integer>>());

		stack.add(bst.getRoot());
		while (!stack.isEmpty()) {
			Node<Integer> n = stack.poll();
			System.out.print(n.val + " ");
			if (n.right != null)
				stack.add(n.right);
			if (n.left != null)
				stack.add(n.left);
		}
	}

	public static void postorder(BST<Integer> bst) {

		// ?
	}

	public static void main(String[] args) {

		BST<Integer> bst = new BST<Integer>();
		for (int i : new int[] { 5, 1, 7, 9, 3, 4, 0, 2, 8, 6 }) {
			bst.add(i);
		}

		System.out.println("recursive:");
		Trees.traverse(bst, TraverseOrder.POSTORDER,
				new TraverseVisitor<Integer>() {
					@Override
					public void visit(Integer t) {
						System.out.print(t + " ");
					}
				});
		System.out.println("\niterative:");
		postorder(bst);
	}
}
