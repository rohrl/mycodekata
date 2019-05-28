package mine;

public class GCD {

	public static int gcd(int a, int b) {
		if (a < b)
			return gcd(b, a);
		if (b == 0)
			return a;
		b = a - b;
		a = a - b;
		return a > b ? gcd(a, b) : gcd(b, a);
	}

	public static int gcd(int... nums) {
		int gcd = nums[0];
		for (int n : nums) {
			gcd = gcd(gcd, n);
		}
		return gcd;
	}

	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	public static void main(String[] args) {

		for (int i = 1; i < 20; i++)
			for (int j = 1; j <= 20; j++)
				System.out.println("GCD(" + i + "," + j + ") = " + gcd(i, j)
						+ "\tLCM = " + lcm(i, j));
	}
}
