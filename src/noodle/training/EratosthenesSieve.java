package noodle.training;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class EratosthenesSieve {

	static Set<Integer> primes(int n) {

		if (n <= 1)
			return Collections.emptySet();

		boolean[] notPrime = new boolean[(n + 1) / 2];

		for (int i = 3; i <= n / 3; i += 2) {
			int j = 3 * i;
			while (j <= n) {
				if (j % 2 == 1)
					notPrime[j / 2] = true;
				j += i;
			}
		}

		Set<Integer> result = new TreeSet<Integer>();
		if (n >= 2)
			result.add(2);
		for (int i = 3; i <= n; i += 2) {
			if (!notPrime[i / 2])
				result.add(i);
		}

		return result;
	}

	public static void main(String[] args) {

		// for n=78 -> 21
		System.out.println(primes(78).size());
	}

}
