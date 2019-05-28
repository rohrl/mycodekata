package ms;

import java.util.Arrays;
import java.util.Random;

/** TODO wrong (?) */
public class SubsequenceWithMaxSum {

	public static final Random rand = new Random();

	public static int[] generate(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++)
			array[i] = rand.nextInt(201) - 100;
		return array;
	}

	public static class Triple<T> {
		T first;
		T second;
		T third;

		public Triple(T f, T s, T t) {
			first = f;
			second = s;
			third = t;
		}

		@Override
		public String toString() {
			return first + " " + second + " " + third;
		}

		public boolean equal(Triple<T> other) {
			return first.equals(other.first) && second.equals(other.second)
					&& third.equals(other.third);
		}
	}

	public static Triple<Integer> bruteSearch(int[] arr) {

		// int [] integral = new int [arr.length];
		// for(int i = 0, sum = 0 ; i < arr.length; i++)
		// integral [i] = (sum+=arr[i]);

		int s = 0, e = 0, maxSum = 0;
		for (int i = 0; i < arr.length; i++) {
			int tmpSum = 0;
			for (int j = i; j < arr.length; j++) {
				tmpSum += arr[j];
				if (tmpSum > maxSum) {
					maxSum = tmpSum;
					s = i;
					e = j;
				}
			}
		}

		return new Triple<Integer>(s, e, maxSum);
	}

	public static Triple<Integer> optimalSearch(int[] arr) {

		int s = 0, e = 0, min = 0, max = 0;

		int sum = 0;

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (sum < min) {
				min = sum;
				s = i;
			}
			if (sum > max) {
				max = sum;
				e = i;
			}

		}

		return new Triple<Integer>(s, e, max - min);
	}

	public static void main(String[] args) {

		int[] arr = generate(10);
		System.out.println(Arrays.toString(arr));
		Triple<Integer> brute = bruteSearch(arr);
		Triple<Integer> optimal = optimalSearch(arr);
		System.out.println(brute);
		System.out.println(optimal);
		System.out.println(brute.equal(optimal));
	}
}
