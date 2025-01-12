package scrollbook;

import java.util.Arrays;
import java.util.Random;

//@MyAnnotation
public class BigIntSum {

	private static final int MAX_DIGITS = 12;
	static Random random = new Random();

	static byte[] generateNumber() {
		int size = random.nextInt(MAX_DIGITS);
		byte[] n = new byte[size];
		while (--size >= 0) {
			n[size] = (byte) random.nextInt(10);
		}
		return n;
	}

	static void print(byte[] n) {
		// for (int i = n.length - 1; i >= 0; i--) {
		// if (n[i] < 0 || n[i] > 9)
		// throw new RuntimeException("Invalid digits: " + n[i]);
		// System.out.print(n[i]);
		// }
		System.out.println(convert(n));
	}

	static long convert(byte[] n) {
		long result = 0;
		long pow = 1;
		for (int i = 0; i < n.length; i++, pow *= 10)
			result += n[i] * pow;
		return result;
		// new BigInteger()
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			byte[] n1 = generateNumber();
			byte[] n2 = generateNumber();

			print(n1);
			print(n2);

			// System.out.println("Sum = ");
			print(sum(n1, n2));

			if ((convert(n1) + convert(n2)) == convert(sum(n1, n2)))
				System.out.println("OK");
			else
				throw new RuntimeException("INCORRECT");
		}
	}

	private static byte[] sum(byte[] n1, byte[] n2) {
		byte[] result = new byte[Math.max(n1.length, n2.length) + 1];

		byte overflow = 0;
		int i = 0;
		while (i < result.length) {
			byte sum = (byte) overflow;
			if (i < n1.length)
				sum += n1[i];
			if (i < n2.length)
				sum += n2[i];
			result[i] = (byte) (sum % 10);
			overflow = (byte) (sum / 10);
			i++;
		}

		if (result[result.length - 1] == 0)
			result = Arrays.copyOf(result, result.length - 1);

		return result;
	}
}
