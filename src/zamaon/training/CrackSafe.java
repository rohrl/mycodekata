package zamaon.training;

import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Not the optimal solution but close...
 *
 * @see CrackSafeBrute
 */
public class CrackSafe {

    static int L = 4, B = 10;
//   static int L = 3, B = 2;

    public static void main(String[] args) {

        StringBuilder word = new StringBuilder();

        TreeSet<String> remaining =
                new TreeSet<>(CrackSafeBrute.generateAll(L, B));

        word.append(remaining.pollFirst());

        outer:
        while (!remaining.isEmpty()) {

            int prefLen = L - 1;
            for (; prefLen > 0; prefLen--) {

                String prefix = word.substring(word.length() - prefLen);

                int suffixLength = L - prefLen;

                for (String suffix : CrackSafeBrute.generateAll(suffixLength, B)) {

                    String code = prefix + suffix;

                    if (remaining.contains(code)) {

                        remaining.remove(code);

                        word.append(suffix);

                        continue outer;
                    }
                }
            }

            if (prefLen == 0 && !remaining.isEmpty()) {
                word.append(remaining.pollFirst());
            }
        }

        verify(word.toString());

        System.out.println("The optimal solution can't be shorter than:\n " + (L + (int) Math.pow(B, L) - 1));
        System.out.println("Worst solution length:\n " + (L * (int) Math.pow(B, L)));
        System.out.println("Our solution length:\n " + word.length());
        System.out.println(word);
    }

    /**
     * verifies the word contains all codes
     */
    static void verify(String word) {

        Set<String> all = new HashSet<>(CrackSafeBrute.generateAll(L, B));

        for (int i = 0; i <= word.length() - L; i++) {
            String code = word.substring(i, i + L);
            all.remove(code);
        }

        Preconditions.checkState(all.isEmpty(),
                "Following codes are missing from the word: " + all);

    }
}
