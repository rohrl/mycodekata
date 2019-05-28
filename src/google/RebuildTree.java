package google;

import java.util.Arrays;

import fb.bst.BST;
import fb.bst.BinaryTree;
import fb.bst.BinaryTree.Node;
import fb.bst.Trees;
import fb.bst.Trees.TraverseOrder;
import fb.bst.Trees.TraverseVisitor;

/**
 * Given the pre-order and in-order traversing result of a binary tree, write a
 * function to rebuild the tree.
 */
public class RebuildTree {

	public static void main(String[] args) {

		BST<Integer> bst = new BST<Integer>();

		for (int i : new int[] { 1, 5, 2, 7, 8, 3, 4, 6, 9, 0 }) {
			bst.add(i);
		}

		final int[] inorder = new int[bst.size()];
		Trees.traverse(bst, TraverseOrder.INORDER,
				new TraverseVisitor<Integer>() {
					int i = 0;

					@Override
					public void visit(Integer t) {
						inorder[i++] = t;
					}
				});

		final int[] preorder = new int[bst.size()];
		Trees.traverse(bst, TraverseOrder.PREORDER,
				new TraverseVisitor<Integer>() {
					int i = 0;

					@Override
					public void visit(Integer t) {
						preorder[i++] = t;
					}
				});

		System.out.println(Arrays.toString(preorder));
		System.out.println(Arrays.toString(inorder));

		BinaryTree<Integer> rebuilt = Trees.createBinaryTree(rebuild(preorder,
				0, preorder.length, inorder, 0, inorder.length, null));

		System.out.println(bst.getRoot());
		System.out.println(rebuilt.getRoot());

		if (!Trees.compareTrees(bst.getRoot(), rebuilt.getRoot()))
			System.err.println(" NOT EQUAL !!! ");
	}

	private static Node<Integer> rebuild(int[] preorder, int ps, int pe,
			int[] inorder, int is, int ie, Node<Integer> parent) {

		if (pe == ps || ie == is)
			return null;
		
		int val = preorder[ps];
		Node<Integer> n = new Node<Integer>(parent, val);
		
		if (pe - ps == 1 || ie - is == 1)
			return n;
		
		// find idx of val in inorder
		int i = is;
		while (i < ie && inorder[i] != val)
			i++;
		assert i < ie;
		n.left = rebuild(preorder, ps + 1, ps + 1 + (i - is), inorder, is, i, n);
		n.right = rebuild(preorder, ps + 1 + (i - is), pe, inorder, i + 1, ie,
				n);
		return n;
	}
}
