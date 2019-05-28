package google;

import java.util.Deque;
import java.util.LinkedList;

/**
 * TODO  recursive/iterative approaches, conversions infix <-> postfix <-> prefix
 * operators priorities, evaluation in each case, OO design
 */
public class RPN {

	static String fromRpnToInfix(String rpn) {

		Deque<String> stack = new LinkedList<String>();
		char[] input = rpn.toCharArray();
		int i = 0;
		stack.push("" + input[i++]);
		stack.push("" + input[i++]);
		while (i < input.length) {
			String c = "" + input[i++];
			if (c.matches("[a-z0-9]+")) {
				stack.push(c);
			} else {
				String a = stack.pop();
				String b = stack.pop();
				stack.push("(" + b + c + a + ")");
			}
		}

		return stack.pop();
	}

	/** Shunting-yard algorithm */
	static String fromInfixToRpn(String rpn) {

		Deque<String> stack = new LinkedList<String>();
		char[] input = rpn.toCharArray();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < input.length) {
			String c = "" + input[i++];
			if (c.matches("[a-z0-9]+")) {
				sb.append(c);
			} else if (c.equals("(")) {
				stack.push(c);
			} else if (c.equals(")")) {
				while (!(c = stack.pop()).equals("(")) {
					sb.append(c);
				}
			} else {
				// operator
				stack.push(c);
			}
		}

		return sb.toString();
	}

	static class Node {
		String val;
		Node left;
		Node right;

		Node(String v) {
			val = v;
		}
	}

	static Node treeFromRpn(String rpn) {

		Deque<Node> stack = new LinkedList<Node>();
		char[] input = rpn.toCharArray();
		int i = 0;
		stack.push(new Node("" + input[i++]));
		stack.push(new Node("" + input[i++]));
		while (i < input.length) {
			String c = "" + input[i++];
			if (c.matches("[a-z0-9]+")) {
				stack.push(new Node(c));
			} else {
				Node a = stack.pop();
				Node b = stack.pop();
				Node parent = new Node(c);
				parent.left = b;
				parent.right = a;
				stack.push(parent);
			}
		}
		return stack.pop();
	}

	static String printTreeInorder(Node root) {
		String left = root.left == null ? "" : "("
				+ printTreeInorder(root.left) + ")";
		String right = root.right == null ? "" : "("
				+ printTreeInorder(root.right) + ")";
		return left + root.val + right;
	}

	public static void main(String[] args) {

		System.out.println(fromRpnToInfix("abc+*"));
		System.out.println(fromRpnToInfix("abc+d*+e-"));

		System.out.println(fromInfixToRpn("(a*(b+c))"));
		System.out.println(fromInfixToRpn("((a+((b+c)*d))-e)"));

		System.out.println(printTreeInorder(treeFromRpn("abc+*")));
		// TODO treeFromInfix
	}
}
