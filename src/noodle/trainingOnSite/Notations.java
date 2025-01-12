package noodle.trainingOnSite;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import scrollbook.bst.BinaryTree.Node;
import scrollbook.bst.Trees;
import scrollbook.bst.Trees.TraverseOrder;
import scrollbook.bst.Trees.TraverseVisitor;

public class Notations {

	private static final String OPERATORS_REGEXP = "[+\\-/*]";

	private static Node<String> wireNodes(Node<String> op, Node<String> arg1,
			Node<String> arg2) {
		op.left = arg1;
		op.right = arg2;
		arg1.parent = arg2.parent = op;
		return op;
	}

	public static Node<String> fromRPN(List<String> rpn) {

		Queue<Node<String>> stack = Collections
				.asLifoQueue(new LinkedList<Node<String>>());

		for (String token : rpn) {
			Node<String> parent = new Node<String>(null, token);
			if (token.matches(OPERATORS_REGEXP)) {
				Node<String> arg2 = stack.poll();
				Node<String> arg1 = stack.poll();
				wireNodes(parent, arg1, arg2);
			}
			stack.add(parent);
		}

		return stack.poll();
	}

	public static Node<String> fromPN(List<String> pn) {

		Queue<Node<String>> stack = Collections
				.asLifoQueue(new LinkedList<Node<String>>());

		Collections.reverse(pn);

		for (String token : pn) {
			Node<String> parent = new Node<String>(null, token);
			if (token.matches(OPERATORS_REGEXP)) {
				Node<String> arg1 = stack.poll();
				Node<String> arg2 = stack.poll();
				wireNodes(parent, arg1, arg2);
			}
			stack.add(parent);
		}

		return stack.poll();
	}

	/** assumes no priority of operators but presence of braces */
	public static Node<String> fromInfix(List<String> infix) {

		// shunting yard algo

		Queue<String> operatorStack = Collections
				.asLifoQueue(new LinkedList<String>());

		Queue<Node<String>> treeStack = Collections
				.asLifoQueue(new LinkedList<Node<String>>());

		for (String token : infix) {
			if (token.matches(OPERATORS_REGEXP) || "(".equals(token)) {
				operatorStack.add(token);
			} else if (")".equals(token)) {
				String oper;
				while (!"(".equals(oper = operatorStack.poll())) {
					Node<String> arg2 = treeStack.poll();
					Node<String> arg1 = treeStack.poll();
					treeStack.add(wireNodes(new Node<String>(null, oper), arg1,
							arg2));
				}

			} else {
				treeStack.add(new Node<String>(null, token));
			}
		}

		while (!operatorStack.isEmpty()) {
			String oper = operatorStack.poll();
			Node<String> arg2 = treeStack.poll();
			Node<String> arg1 = treeStack.poll();
			treeStack.add(wireNodes(new Node<String>(null, oper), arg1, arg2));
		}

		if (treeStack.size() != 1)
			throw new RuntimeException("fromInfix: inconsistent data");

		return treeStack.poll();
	}

	public static double eval(Node<String> exp) {

		if ("+".equals(exp.val)) {
			return eval(exp.left) + eval(exp.right);
		} else if ("-".equals(exp.val)) {
			return eval(exp.left) - eval(exp.right);
		} else if ("/".equals(exp.val)) {
			return eval(exp.left) / eval(exp.right);
		} else if ("*".equals(exp.val)) {
			return eval(exp.left) * eval(exp.right);
		} else {
			return Double.valueOf(exp.val);
		}
	}

	public static List<String> toNotation(Node<String> t, TraverseOrder order) {

		final List<String> list = new ArrayList<String>();

		if (order == TraverseOrder.INORDER) {
			// normal inorder traversal enriched with braces
			toInfixNotation(t, list);
		} else {
			Trees.traverse(Trees.createBinaryTree(t), order,
					new TraverseVisitor<String>() {
						@Override
						public void visit(String t) {
							list.add(t);
						}
					});
		}
		return list;
	}

	private static void toInfixNotation(Node<String> n, List<String> list) {
		if (n.left != null) {
			if (n.left.hasChildren()) {
				list.add("(");
			}
			toInfixNotation(n.left, list);
			if (n.left.hasChildren()) {
				list.add(")");
			}
		}
		list.add(n.val);
		if (n.right != null) {
			if (n.right.hasChildren()) {
				list.add("(");
			}
			toInfixNotation(n.right, list);
			if (n.right.hasChildren()) {
				list.add(")");
			}
		}
	}

	public static void main(String[] args) {

		String expression = "0 2 3 + 5 * -";

		List<String> rpn = Arrays.asList(expression.split(" "));

		Node<String> evaluationTree = fromRPN(rpn);

		// evaluate
		out.println(eval(evaluationTree));

		out.println("INFIX notation (braces required!)");
		out.println(toNotation(evaluationTree, TraverseOrder.INORDER));

		out.println("POSTFIX (Reverse Polish) notation");
		out.println(toNotation(evaluationTree, TraverseOrder.POSTORDER));

		out.println("PREFIX (Polish) notation");
		out.println(toNotation(evaluationTree, TraverseOrder.PREORDER));

		// check...
		out.println(eval(fromRPN(toNotation(evaluationTree,
				TraverseOrder.POSTORDER))));
		out.println(eval(fromPN(toNotation(evaluationTree,
				TraverseOrder.PREORDER))));
		out.println(eval(fromInfix(toNotation(evaluationTree,
				TraverseOrder.INORDER))));
	}
}
