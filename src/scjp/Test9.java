package scjp;

import java.util.ArrayList;

public class Test9 {

	public static void main(String[] args) {
		new Test9().types();
	}

	public void types() {

		asert(int.class != Integer.class);
		asert(int.class == Integer.TYPE);
		System.out.println(int.class);
		System.out.println(Integer.TYPE);
		System.out.println(Integer.class);

		asert(void.class == Void.TYPE);

		asert(Integer.class == Integer.class);

		asert(ArrayList.class == new ArrayList<Object>().getClass());

		System.out.println(int[][][].class);
		System.out.println(Integer[][][].class);

	}

	private void asert(boolean b) {
		if (!b)
			throw new AssertionError();
	}
}
