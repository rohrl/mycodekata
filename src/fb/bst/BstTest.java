package fb.bst;

import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;

import fb.bst.Trees.TraverseOrder;
import fb.bst.Trees.TraverseVisitor;

public class BstTest {

	static final Random rand = new Random();

	public static void main(String[] args) {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < 10; i++)
			bst.add(rand.nextInt(100));

		System.out.println("Size " + bst.size());

		Trees.traverse(bst, TraverseOrder.INORDER,
				new TraverseVisitor<Integer>() {

					@Override
					public void visit(Integer t) {
						System.out.println(t);
					}
				});

		// System.out.println("-------");
		// for (Integer i : bst)
		// System.out.println(i);
	}

	@Test
	public void testSize() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < 10; i++)
			bst.add(i);

		Assert.assertTrue(bst.size() == 10);
	}

	@Test
	public void testContains1() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < 5; i++)
			bst.add(i);

		for (int i = 0; i < 5; i++)
			Assert.assertTrue(bst.contains(i));

		Assert.assertFalse(bst.contains(6));
	}

	@Test
	public void testContains2() {

		BST<Integer> bst = new BST<Integer>();
		TreeSet<Integer> set = new TreeSet<Integer>();

		for (int i = 0; i < 1000; i++) {
			bst.add(rand.nextInt());
			set.add(rand.nextInt());
		}

		Assert.assertFalse(bst.containsAll(set));
		Assert.assertFalse(set.containsAll(bst));
	}

	@Test
	public void testRemove1() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 1; i <= 5; i++)
			bst.add(10 * i);

		bst.remove(30);

		Assert.assertTrue(bst.size() == 4);
		Assert.assertFalse(bst.contains(30));
		for (int i = 1; i <= 5; i++)
			if (i != 3)
				Assert.assertTrue(bst.contains(i * 10));
	}

	@Test
	public void testRemove2() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < 1000; i++)
			bst.add(i);

		for (int i = 0; i < 1000; i++) {
			Assert.assertTrue(bst.remove(i));
			Assert.assertTrue(bst.size() == (1000 - i - 1));
			Assert.assertTrue(!bst.contains(i));
		}

		Assert.assertTrue(bst.isEmpty());
		Assert.assertTrue(bst.size() == 0);
		Assert.assertFalse(bst.remove(0));
	}

	@Test
	public void testRemove3() {

		BST<Integer> bst = new BST<Integer>();
		TreeSet<Integer> set = new TreeSet<Integer>();

		for (int i = 0; i < 1000; i++) {
			int x = rand.nextInt();
			bst.add(x);
			set.add(x);
		}

		while (!set.isEmpty()) {
			Integer x = null;
			while (x == null)
				x = set.floor(rand.nextInt());
			
			Assert.assertTrue(set.remove(x) == bst.remove(x));

			Assert.assertTrue(set.containsAll(bst));
			Assert.assertTrue(bst.containsAll(set));
		}
	}

	@Test
	public void testIterator() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < 1000; i++)
			bst.add(rand.nextInt(200) - 100);

		Iterator<Integer> it = bst.iterator();
		int oldVal = Integer.MIN_VALUE;
		while (it.hasNext()) {
			int val = it.next();
			Assert.assertTrue(oldVal <= val);
		}
	}

	@Test
	public void testIterator2() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 1000; i > 0; i--)
			bst.add(i);

		Iterator<Integer> it = bst.iterator();
		int val = 1;
		while (it.hasNext()) {
			Assert.assertTrue(val == it.next());
			val++;
		}
		Assert.assertTrue(val == 1001);
		Assert.assertTrue(1 == bst.first());
		Assert.assertTrue(1000 == bst.last());
	}

	@Test
	public void testRotateLeft() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 0; i < 10; i++)
			bst.add(i);

		for (int i = 0; i < 10; i++)
			Trees.rotateLeft(bst, bst.find(i));

		Iterator<Integer> it = bst.iterator();
		int oldVal = Integer.MIN_VALUE;
		while (it.hasNext()) {
			int val = it.next();
			Assert.assertTrue(oldVal <= val);
		}

		for (int i = 0; i < 10; i++)
			Assert.assertTrue(bst.contains(i));
	}

	@Test
	public void testRotateRight() {

		BST<Integer> bst = new BST<Integer>();
		for (int i = 10; i < 0; i--)
			bst.add(i);

		for (int i = 10; i < 0; i--)
			Trees.rotateLeft(bst, bst.find(i));

		Iterator<Integer> it = bst.iterator();
		int oldVal = Integer.MIN_VALUE;
		while (it.hasNext()) {
			int val = it.next();
			Assert.assertTrue(oldVal <= val);
		}

		for (int i = 10; i < 0; i--)
			Assert.assertTrue(bst.contains(i));
	}
}
