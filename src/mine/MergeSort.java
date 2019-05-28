package mine;

import java.util.Arrays;

public class MergeSort {

	public static <T extends Comparable<? super T>> void sort(T[] arr) {

		if (arr.length == 1)
			return;

		if (arr.length == 2) {
			if (arr[0].compareTo(arr[1]) > 0) {
				T tmp = arr[0];
				arr[0] = arr[1];
				arr[1] = tmp;
			}
		}

		int half = arr.length / 2;
		T[] arr1 = Arrays.copyOfRange(arr, 0, half);
		T[] arr2 = Arrays.copyOfRange(arr, half, arr.length);
		sort(arr1);
		sort(arr2);

		int i = 0, j = 0, k = 0;
		while (i < arr.length) {

			if (k >= arr2.length
					|| (j < arr1.length && arr1[j].compareTo(arr2[k]) <= 0)) {
				arr[i++] = arr1[j++];
			} else {
				arr[i++] = arr2[k++];
			}
		}

	}

	public static void main(String[] args) {

		class Point implements Comparable<Point> {
			int x, y;

			Point(int a, int b) {
				x = a;
				y = b;
			}

			public int compareTo(Point o) {
				return x - o.x;
			};

			@Override
			public String toString() {
				return "(" + x + "," + y + ")";
			}
		}

		Point[] a = new Point[20];
		for (int i = 0; i < a.length; i++) {
			a[i] = new Point((int) (Math.random() * 10), i);
		}

		sort(a);

		// is our sorting stable? let's see :)
		System.out.println(Arrays.toString(a));
	}
}
