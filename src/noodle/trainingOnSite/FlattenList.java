package noodle.trainingOnSite;

public class FlattenList {

	static class Node {
		int val;
		Node next;
		Node child;

		public Node(int i) {
			val = i;
		}
	}

	/** resturns last node of the list */
	public static Node flatten(Node head) {

		if (head == null)
			throw new IllegalArgumentException("head == null");

		Node next = head.next;

		Node tail = null;
		if (head.child != null) {
			tail = flatten(head.child);
			head.next = head.child;
			tail.next = next;
		}
		head.child = null;

		if (next == null)
			return tail == null ? head : tail;
		else
			return flatten(next);
	}

	public static int generate(Node head, float PofNext, float PofChild) {
		int next = head.val + 1;
		if (Math.random() < PofChild) {
			head.child = new Node(next);
			next = generate(head.child, PofNext, PofChild);
		}
		if (Math.random() < PofNext) {
			head.next = new Node(next);
			next = generate(head.next, PofNext, PofChild);
		}
		return next;
	}

	public static void print(Node n) {
		System.out.print(n.val + " ");
		if (n.child != null) {
			System.out.print("[ ");
			print(n.child);
			System.out.print("] ");
		}
		if (n.next != null)
			print(n.next);
	}

	public static void main(String[] args) {

		Node l = new Node(0);
		generate(l, 0.6f, 0.4f);
		System.out.println("original:");
		print(l);
		System.out.println("\nflattened:");
		flatten(l);
		print(l);

	}
}
