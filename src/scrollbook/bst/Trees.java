package scrollbook.bst;

import scrollbook.bst.BinaryTree.Node;

public class Trees {

	public static <T> BinaryTree<T> createBinaryTree() {
		return new BinaryTree<T>() {
			private Node<T> root;

			@Override
			public Node<T> getRoot() {
				return root;
			}

			@Override
			public void setRoot(Node<T> n) {
				root = n;
			}
		};
	}

	public static <T> BinaryTree<T> createBinaryTree(Node<T> root) {
		BinaryTree<T> t = createBinaryTree();
		t.setRoot(root);
		return t;
	}

	public enum TraverseOrder {
		INORDER, PREORDER, POSTORDER
	}

	public interface TraverseVisitor<T> {
		void visit(T t);
	}

	public static <T> void traverse(BinaryTree<T> tree, TraverseOrder order,
			TraverseVisitor<T> v) {
		traverse(tree.getRoot(), 0, order, v);
	}

	private static <T> void traverse(Node<T> n, int depth, TraverseOrder order,
			TraverseVisitor<T> v) {

		if (n == null)
			return;
		if (order == TraverseOrder.INORDER) {
			traverse(n.left, depth + 1, order, v);
			v.visit(n.val);
			traverse(n.right, depth + 1, order, v);
		}
		if (order == TraverseOrder.POSTORDER) {
			traverse(n.left, depth + 1, order, v);
			traverse(n.right, depth + 1, order, v);
			v.visit(n.val);
		}
		if (order == TraverseOrder.PREORDER) {
			v.visit(n.val);
			traverse(n.left, depth + 1, order, v);
			traverse(n.right, depth + 1, order, v);
		}
	}

	public static <T> boolean rotateLeft(BinaryTree<T> tree, Node<T> n) {
		if (n.right == null)
			return false;

		Node<T> parent = n.parent;
		Node<T> r = n.right;
		n.right = r.left;
		if (r.left != null)
			r.left.parent = n;
		r.left = n;
		n.parent = r;
		if (parent != null)
			r.parent = parent;
		else {
			assert n == tree.getRoot();
			tree.setRoot(r);
			r.parent = null;
		}
		return true;
	}

	public static <T> boolean rotateRight(BinaryTree<T> tree, Node<T> n) {
		if (n.left == null)
			return false;

		Node<T> parent = n.parent;
		Node<T> l = n.left;
		n.left = l.right;
		if (l.right != null)
			l.right.parent = n;
		l.right = n;
		n.parent = l;
		if (parent != null)
			l.parent = parent;
		else {
			assert n == tree.getRoot();
			tree.setRoot(l);
			l.parent = null;
		}
		return true;
	}

	public static String toString(Node<?> tree) {
		StringBuilder sb = new StringBuilder(tree.val.toString());
		if (tree.left != null) {
			sb.append("(L: ").append(toString(tree.left)).append(")");
		}
		if (tree.right != null) {
			sb.append("(R: ").append(toString(tree.right)).append(")");
		}
		return sb.append(" ").toString();
	}

	public static String toString(Nnode<?> tree) {
		StringBuilder sb = new StringBuilder(tree.val.toString());
		sb.append("(");
		for (Nnode<?> n : tree.children) {
			sb.append(toString(n)).append(" ");
		}
		return sb.append(")").toString();
	}

	public static boolean compareTrees(Node<?> first, Node<?> second) {
		if (first == null)
			return second == null;
		return first.val.equals(second.val)
				&& compareTrees(first.left, second.left)
				&& compareTrees(first.right, second.right);
	}
}
