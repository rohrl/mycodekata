package zamaon.training;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Range;

import java.util.*;

public class Sandpit {

    static List<Integer> intersection(List<Integer> list1, List<Integer> list2) {

        if (list1.isEmpty() || list2.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();

        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();

        do {

            int val1 = it1.next();
            int val2 = it2.next();

            while (val1 != val2) {
                if (val1 < val2) {
                    if (!it1.hasNext()) {
                        break;
                    }
                    while (val1 < val2 && it1.hasNext()) {
                        val1 = it1.next();
                    }
                } else {
                    if (!it2.hasNext()) {
                        break;
                    }
                    while (val1 > val2 && it2.hasNext()) {
                        val2 = it2.next();
                    }
                }
            }

            if (val1 == val2) {
                result.add(val1);
            } else {
                break;
            }

        } while (it1.hasNext() && it2.hasNext());

        return result;
    }

    static int[] intersection(int[] list1, int[] list2) {

        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < list1.length && j < list2.length) {

            if (list1[i] == list2[j]) {
                result.add(list1[i]);
                i++;
                j++;
                continue;
            }

            if (list1[i] < list2[j]) {
                while (i < list1.length && list1[i] < list2[j]) {
                    i++;
                }
            } else {
                while (j < list2.length && list1[i] > list2[j]) {
                    j++;
                }
            }
        }

        return result.stream().mapToInt(n -> n).toArray();
    }

    public static class Boggle {

        int N, M;

        char matrix[][]; // N x M

        boolean vis[][]; // all false initially

        Set<String> result = new HashSet<>();

        public Boggle(int n, int m, char[][] matrix) {
            N = n;
            M = m;
            this.matrix = matrix;
            vis = new boolean[N][M];
        }

        private void findWordsRec(int i, int j, StringBuilder sb) {

            vis[i][j] = true;
            sb.append(matrix[i][j]);

            if (isWord(sb.toString())) {
                result.add(sb.toString());
            }

            for (int di = -1; di <= 1; di++) {
                for (int dj = -1; dj <= 1; dj++) {

                    int iNext = i + di;
                    int jNext = j + dj;

                    if (Range.closedOpen(0, N).contains(iNext)
                            && Range.closedOpen(0, M).contains(jNext)
                            && !vis[iNext][jNext]) {
                        findWordsRec(iNext, jNext, sb);
                    }
                }
            }

            sb.deleteCharAt(sb.length() - 1);
            vis[i][j] = false;
        }

        public Set<String> findWords() {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    findWordsRec(i, j, new StringBuilder());
                }
            }

            return result;
        }

        public boolean isWord(String testWord) {
            return ImmutableSet.of(
                    "TRAP",
                    "SEMI",
                    "DIME",
                    "DIMES",
                    "DIM",
                    "SIR",
                    "RISE",
                    "DUPA",
                    "CYCKI",
                    "URBA").contains(testWord);
        }

    }

    public static void main(String[] args) {


        System.out.println(intersection(
                ImmutableList.of(1, 2, 3),
                ImmutableList.of(2, 4, 5)));

        System.out.println(intersection(
                ImmutableList.of(1, 2, 4, 5),
                ImmutableList.of(0, 2, 3, 5)));


        System.out.println(Arrays.toString(intersection(
                new int[]{1, 2, 3},
                new int[]{2, 4, 5})));

        System.out.println(Arrays.toString(intersection(
                new int[]{1, 2, 4, 5},
                new int[]{0, 2, 3, 5})));


        char[][] matrix = {{'A', 'D', 'S', 'E'}, {'F', 'C', 'I', 'M'},
                {'P', 'U', 'R', 'T'}, {'E', 'A', 'B', 'D'}};
        System.out.println(
                new Boggle(4, 4, matrix).findWords());

    }
}
