package scrollbook.bst;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.SortedSet;

/** TESTS !!! */
public class BST<T extends Comparable<? super T>> extends AbstractSet<T>
		implements Iterable<T>, SortedSet<T>, BinaryTree<T> {

	private Node<T> root;
	private int size;
	private static Random random = new Random();

	public BST() {

	}

	public BST(Collection<T> c) {
		for (T t : c) {
			this.add(t);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> n) {
		clear();
		root = n;
	}

	@Override
	public boolean add(T val) {
		if (root == null) {
			root = new Node<T>(null, val);
		} else {

			Node<T> tmp = root;
			while (true) {

				int cmp = val.compareTo(tmp.val);
				if (cmp == 0)
					return false;

				if (cmp < 0) {
					if (tmp.left == null) {
						tmp.left = new Node<T>(tmp, val);
						break;
					} else
						tmp = tmp.left;
				} else {
					if (tmp.right == null) {
						tmp.right = new Node<T>(tmp, val);
						break;
					} else
						tmp = tmp.right;
				}
			}
		}
		size++;
		return true;
	}

	private class BstIterator implements Iterator<T> {

		Node<T> current = root;

		private BstIterator() {
			// move to entry point of next()
			iterateToLowest();
		}

		private void iterateToLowest() {
			if (current == null)
				return;
			while (current.left != null)
				current = current.left;
		}

		@Override
		public T next() {
			if (current == null)
				throw new NoSuchElementException();
			T t = current.val;
			current = successor(current);
			return t;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	Node<T> find(Comparable<? super T> val) {
		Node<T> n = root;
		while (n != null) {
			int cmp = val.compareTo(n.val);
			if (cmp == 0)
				return n;
			n = (cmp < 0) ? n.left : n.right;
		}
		return null;
	}

	@Override
	public boolean contains(Object o) {
		@SuppressWarnings("unchecked")
		Comparable<? super T> c = (Comparable<? super T>) o;
		return find(c) != null;
	}

	@Override
	public boolean remove(Object o) {
		if (o == null)
			return false;
		@SuppressWarnings("unchecked")
		Comparable<? super T> c = (Comparable<? super T>) o;

		Node<T> d = find(c);
		if (d == null)
			return false;

		delete(d);

		size--;
		return true;
	}

	private void delete(Node<T> d) {
		if (d == null)
			return;
		if (d.left == null || d.right == null) {

			Node<T> replacement = d.left == null ? d.right : d.left;

			if (d == root) {
				root = replacement;
			} else {
				if (d.parent.left == d)
					d.parent.left = replacement;
				else
					d.parent.right = replacement;
			}
			if (replacement != null)
				replacement.parent = d.parent;

		} else {
			boolean succ = random.nextBoolean();
			Node<T> r = null;
			if (succ) {
				r = d.right;
				while (r.left != null)
					r = r.left;
				d.val = r.val;
				delete(r);
			} else {
				r = d.left;
				while (r.right != null)
					r = r.right;
				d.val = r.val;
				delete(r);
			}
		}
	}

	public Node<T> successor(Node<T> n) {
		if (n == null)
			return null;
		if (n.right != null) {
			Node<T> node = n.right;
			while (node.left != null)
				node = node.left;
			return node;
		} else {
			Node<T> node = n;
			while (node.parent != null && node.parent.left != node)
				node = node.parent;
			return node.parent;
		}
	}

	public Node<T> predecessor(Node<T> n) {
		if (n == null)
			return null;
		if (n.left != null) {
			Node<T> node = n.left;
			while (node.right != null)
				node = node.right;
			return node;
		} else {
			Node<T> node = n;
			while (node.parent != null && node.parent.right != node)
				node = node.parent;
			return node.parent;
		}
	}

	public T ceiling(T t) {
		Node<T> n = successor(find(t));
		return n == null ? null : n.val;
	}

	public T floor(T t) {
		Node<T> n = predecessor(find(t));
		return n == null ? null : n.val;
	}

	@Override
	public Iterator<T> iterator() {
		return new BstIterator();
	}

	private final Comparator<? super T> comparator = new Comparator<T>() {
		@Override
		public int compare(T o1, T o2) {
			return o1.compareTo(o2);
		}
	};

	@Override
	public Comparator<? super T> comparator() {
		return comparator;
	}

	@Override
	public T first() {
		return iterator().next();
	}

	@Override
	public T last() {
		if (root == null)
			return null;
		Node<T> n = root;
		while (n.right != null)
			n = n.right;
		return n.val;
	}

	@Override
	public SortedSet<T> subSet(T arg0, T arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> tailSet(T arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<T> headSet(T arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
