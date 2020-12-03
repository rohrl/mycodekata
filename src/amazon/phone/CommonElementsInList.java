package amazon.phone;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.*;

/**
 * Given two lists, write a function that will return the common values in the list.
 * e.g [1,2,3,4] and [1,4,7] should return [1,4]
 */
public class CommonElementsInList {

    /**
     * O(N+M)
     */
    public static Set<Integer> findCommonValues(List<Integer> list1, List<Integer> list2) {

        Preconditions.checkNotNull(list1, "List1 can't be null");
        Preconditions.checkNotNull(list2, "List2 can't be null");

        if (list1.size() > list2.size()) {
            List<Integer> tmp = list1;
            list1 = list2;
            list2 = tmp;
        }

        HashSet<Integer> list1Values = new HashSet<>(list1);
        Set<Integer> result = new HashSet<>();
        for (int val : list2) {
            if (list1Values.contains(val)) {
                result.add(val);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println(findCommonValues(Collections.emptyList(), Collections.emptyList()));
        System.out.println(findCommonValues(Collections.emptyList(), ImmutableList.of(1, 2)));
        System.out.println(findCommonValues(ImmutableList.of(1), ImmutableList.of(1)));
        System.out.println(findCommonValues(ImmutableList.of(1), ImmutableList.of(2)));
        System.out.println(findCommonValues(ImmutableList.of(1, 2, 3, 4), ImmutableList.of(1, 4, 7)));
        System.out.println(findCommonValues(ImmutableList.of(1, 4, 7), ImmutableList.of(1, 2, 3, 4)));

    }

    // follow-up task:
    // common elements in both lists

//    HashSet<Integer> encoountered = new HashSet();
//    HashSet<Integer> result = new HashSet();
//    for(int val : list1) {
//            if(encoountered.contains(val)) {
//                result.add(val);
//            } else {
//                encoountered.add(val);
//            }
//        }
//    for(int val : list2) {
//            if(encoountered.contains(val)) {
//                result.add(val);
//            } else {
//                encoountered.add(val);
//            }
//        }
//    return result;

}
