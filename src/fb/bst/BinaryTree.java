package fb.bst;

public interface BinaryTree<T> {

	public static class Node<T> {
		public T val;
		public Node<T> left;
		public Node<T> right;
		public Node<T> parent;

		public Node(Node<T> parent, T val) {
			this.parent = parent;
			this.val = val;
		}

		@Override
		public String toString() {
			String me = "(" + val.toString();
			if (left != null)
				me += " L: " + left;
			if (right != null)
				me += " R: " + right;
			return me + ")";
		}

		public boolean hasChildren() {
			return left != null && right != null;
		}

		public boolean isLeftChild() {
			return parent != null && parent.left == this;
		}

		public boolean isRightChild() {
			return parent != null && parent.right == this;
		}
	}

	public Node<T> getRoot();

	public void setRoot(Node<T> n);
}
