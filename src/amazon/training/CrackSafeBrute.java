package amazon.training;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.TreeMultiset;

import java.util.ArrayList;
import java.util.List;

/**
 * Brute force for a simplified version of following task:
 * <p>
 * masz taki sefj, ktory zapamietuje Ci 4 ostatnio podane cyfry
 * i jesli sie zgadzaja z jego kodem, to sie otwiera
 * wiec jesli masz ciag
 * 1 2 3 4 5 6 7 8 i zaczniesz go podawac do sejfu, ktory ma kombinacje 2345
 * to sie otworzy
 * no i musisz takiego stringa wygenerowac, zeby wszystkie mozliwe kombinacje uwzglednil
 * </p>
 */
public class CrackSafeBrute {

    private static int CODE_LENGTH = 3;


    static List<String> generateAll() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < (1 << CODE_LENGTH); i++) {
            result.add(String.format("%" + CODE_LENGTH + "s",
                    Integer.toBinaryString(i)).replace(' ', '0'));

        }
        return result;
    }

    static String reduce(List<String> list) {

        StringBuilder sb = new StringBuilder(list.get(0));

        for (int i = 1; i < list.size(); i++) {

            String word = list.get(i);

            // find overlap
            int overlap = CODE_LENGTH - 1;
            while (overlap > 0) {
                String tail = sb.substring(sb.length() - overlap);
                String head = word.substring(0, overlap);
                if (tail.equals(head)) {
                    break;
                }
                overlap--;
            }

            sb.append(word.substring(overlap));
        }

        return sb.toString();
    }

    static List<List<String>> permutationsPoor(List<String> list) {
        if (list.size() == 1) {
            return ImmutableList.of(list);
        }
        List<List<String>> result = new ArrayList<>();
        List<String> copy = new ArrayList<>(list);
        for (int i = 0; i < copy.size(); i++) {
            swap(copy, 0, i);
            for (List<String> tailPerm : permutationsPoor(copy.subList(1, copy.size()))) {
                List<String> newPerm = new ArrayList<>();
                newPerm.add(copy.get(0));
                newPerm.addAll(tailPerm);
                result.add(newPerm);
            }
            swap(copy, 0, i);
        }
        return result;
    }

    static void permutationsBetter(List<String> input, int i, List<List<String>> results) {
        if (i == input.size() - 1) {
            results.add(new ArrayList<>(input));
        }
        for (int j = i; j < input.size(); j++) {
            swap(input, j, i);
            permutationsBetter(input, i + 1, results);
            swap(input, j, i);
        }
    }

    static void swap(List<String> tmp, int i, int j) {
        String val = tmp.get(j);
        tmp.set(j, tmp.get(i));
        tmp.set(i, val);
    }

    public static void main(String[] args) {

//        System.out.println(generateAll());
//        System.out.println(reduce(generateAll()));
//        System.out.println(reduce(generateAll()).length());

//        System.out.println(permutations(generateAll()).size());

//        ArrayList<List<String>> results = new ArrayList<>();
//        permutationsBetter(new ArrayList<>(ImmutableList.of("a", "b", "c")), 0, results);
//        System.out.println(results);

        List<String> allCodes = generateAll();

        List<List<String>> allPerms = new ArrayList<>();
        permutationsBetter(allCodes, 0, allPerms);

        TreeMultiset<Integer> results = TreeMultiset.create();

        allPerms.stream()
                .map(CrackSafeBrute::reduce)
                .forEach(s -> results.add(s.length()));

        System.out.println(results);

//        [10 x 16, 11 x 40, 12 x 224, 13 x 704, 14 x 1568, 15 x 3088, 16 x 5208, 17 x 6552, 18 x 7824, 19 x 6824, 20 x 4976, 21 x 2392, 22 x 784, 23 x 112, 24 x 8]
    }
}
