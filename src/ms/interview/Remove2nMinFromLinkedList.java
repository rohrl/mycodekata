package ms.interview;

import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Remove2nMinFromLinkedList {

    public static class Node {

        int value;

        Node next;

        @Override
        public String toString() {
            return value + (next != null ? "," + next.toString() : "");
        }
    }


    /**
     * Removes second minimum from linked list and returns the new head.
     * Does modify original list.
     * If multiple elements have that value, all are removed.
     *
     * @param head the head of the list.
     * @return new head or {@code null} if list is empty or has 1 element.
     */
    public static Node remove2ndMinFromLinkedList(Node head) {

        // size <= 1
        if (head == null || head.next == null) {
            //throw new UnsupportedOperationException("List needs to have at least 2 elements");
            return null;
        }

        // size == 2
        if (head.next.next == null) {
            if (head.value == head.next.value) {
                return null;
            } else if (head.value < head.next.value) {
                head.next = null;
                return head;
            } else {
                return head.next;
            }
        }

        int min1, min2;

        if (head.value < head.next.value) {
            min1 = head.value;
            min2 = head.next.value;
        } else {
            min1 = head.next.value;
            min2 = head.value;
        }

        // first pass - find 2nd min
        Node current = head.next;
        do {
            current = current.next;
            if (current.value < min1) {
                min2 = min1;
                min1 = current.value;
            } else if (current.value < min2) {
                min2 = current.value;
            }
        } while (current.next != null);

        // 2nd pass - remove it
        Node newHead = head;

        // if head is to be removed
        while (newHead != null && newHead.value == min2) {
            newHead = newHead.next;
        }

        // if all values were the same
        if (newHead == null) {
            return null;
        }

        current = head.next;
        Node previous = head;
        while (current != null) {
            if (current.value == min2) {
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }

        return newHead;
    }

    public static Node create(List<Integer> javaList) {

        if (javaList.isEmpty()) {
            return null;
        }

        Iterator<Integer> it = javaList.iterator();
        Node head = new Node();
        head.value = it.next();

        Node previous = head;
        while (it.hasNext()) {
            Node newNode = new Node();
            newNode.value = it.next();
            previous.next = newNode;
            previous = newNode;
        }

        return head;
    }

    public static void main(String[] args) {

        Stream.of(
                ImmutableList.of(1, 2, 3),
                ImmutableList.of(1),
                ImmutableList.of(2, 1),
                ImmutableList.of(2, 2),
                ImmutableList.of(3, 3, 3),
                ImmutableList.of(3, 2, 1),
                ImmutableList.of(3, 2, 2),
                ImmutableList.of(3, 3, 2),
                ImmutableList.of(3, 5, 2, 1, 6, 1, 7, 0, 1, 2)
        ).forEach(list -> {
            System.out.println(list + "\t -> [" + remove2ndMinFromLinkedList(create(list)) + "]");
        });

    }
}
