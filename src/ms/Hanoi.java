package ms;

import java.util.ArrayDeque;
import java.util.Deque;

public class Hanoi {

	public static interface Listener {
		void moved(int from, int to);
	}

	Deque<Integer>[] stacks;

	Listener listener;

	@SuppressWarnings("unchecked")
	public Hanoi(int initStackNo, int size, Listener l) {
		stacks = (Deque<Integer>[]) new Deque[] { new ArrayDeque<Integer>(size),
				new ArrayDeque<Integer>(size), new ArrayDeque<Integer>(size) };

		for (int i = 0; i < size; i++)
			stacks[initStackNo].push(size - i);
		listener = l;
	}

	private void move(int from, int to) {
		listener.moved(from, to);
		if (peek(from) > peek(to))
			throw new RuntimeException("Hanoi stack invariant broken");
		stacks[to].push(stacks[from].pop());
	}

	public int getSize(int stack) {
		return stacks[stack].size();
	}

	public int peek(int stack) {
		if (stacks[stack].isEmpty())
			return Integer.MAX_VALUE;
		return stacks[stack].peek();
	}

	public void hanoi(int fromStack, int toStack, int howMany) {
		if (howMany == 0 || getSize(fromStack) == 0)
			return;

		int other = otherThan(fromStack, toStack);
		hanoi(fromStack, other, howMany - 1);
		move(fromStack, toStack);
		hanoi(other, toStack, howMany - 1);
	}

	private int otherThan(int fromStack, int toStack) {
		for (int i = 0; i < 3; i++)
			if (i != fromStack && i != toStack)
				return i;
		return -1; // never happens
	}

	public static void main(String[] args) {

		class MyListener implements Listener {
			public int moves = 0;

			@Override
			public void moved(int from, int to) {
				System.out.println(from + " -> " + to);
				moves++;
			}
		}
		MyListener listener = new MyListener();

		final int STACK_SIZE = 10;

		Hanoi h = new Hanoi(0, STACK_SIZE, listener);
		h.hanoi(0, 1, STACK_SIZE);

		System.out.println("ELEMENTS: " + STACK_SIZE + " MOVES: "
				+ listener.moves);

		System.out.println("SIZE of 1: " + h.getSize(0));
		System.out.println("SIZE of 2: " + h.getSize(1));
		System.out.println("SIZE of 3: " + h.getSize(2));

		System.out.println("TOP of 1: " + h.peek(0));
		System.out.println("TOP of 2: " + h.peek(1));
		System.out.println("TOP of 3: " + h.peek(2));
	}
}
