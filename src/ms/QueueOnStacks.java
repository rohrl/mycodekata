package ms;

import java.util.Stack;

/*
 * queue/dequeue O(1)
 */
public class QueueOnStacks<E> {

	private Stack<E> inbox = new Stack<E>();
	private Stack<E> outbox = new Stack<E>();

	public void queue(E item) {
		inbox.push(item);
	}

	public E dequeue() {
		if (outbox.isEmpty()) {
			while (!inbox.isEmpty()) {
				outbox.push(inbox.pop());
			}
		}
		return outbox.pop();
	}

}

/*
 * remove O(1) insert O(n)
 */
class QueueOnStacksWorse<E> {
	private Stack<E> stack = new Stack<E>();

	public void insert(E elem) {
		if (!stack.empty()) {
			E topElem = stack.pop();
			insert(elem);
			stack.push(topElem);
		} else
			stack.push(elem);
	}

	public E remove() {
		return stack.pop();
	}
}