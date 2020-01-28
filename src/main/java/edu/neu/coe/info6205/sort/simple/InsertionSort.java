package edu.neu.coe.info6205.sort.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertionSort<X extends Comparable<X>> implements Sort<X> {

    /**
     * Constructor for InsertionSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public InsertionSort(Helper<X> helper) {
        this.helper = helper;
    }

    public InsertionSort() {
        this(new Helper<>("InsertionSort"));
    }

    @Override
    public void sort(X[] xs, int from, int to) {
        for (int i = from; i < to; i++) {
            // Invariant 1: elements xs[from..i] are in order
            // TO BE IMPLEMENTED ...
            for (int j = i; j > from; j--) {
                if (helper.less(xs[j], xs[j - 1])) {
                    helper.swap(xs, from, to, j, j - 1);
                }
            }
            // ... END IMPLEMENTATION
        }
    }

    @Override
    public String toString() {
        return helper.toString();
    }

    @Override
    public Helper<X> getHelper() {
        return helper;
    }

    public static void main(String[] args) {
//        final List<Integer> list = new ArrayList<>();
//        list.add(3);
//        list.add(4);
//        list.add(2);
//        list.add(1);
//        Integer[] xs = list.toArray(new Integer[0]);
//        Helper<Integer> helper = new Helper<>("InsertionSort", xs.length);
//        InsertionSort<Integer> sorter = new InsertionSort<Integer>(helper);
//        Integer[] ys = sorter.sort(xs);
//        System.out.println(Arrays.toString(ys));

        String word = "Type";
        final char[] charsX = word.toCharArray();
        final Character[] chars = new Character[charsX.length];
        for (int i=0; i<chars.length; i++) chars[i] = charsX[i];
        Helper<Character> helper = new Helper("InsertionSort", chars.length);
        InsertionSort<Character> sorter = new InsertionSort<>(helper);
        Character[] sorted = sorter.sort(chars);
        final StringBuilder result = new StringBuilder();
        for (Character x: sorted) result.append(x);
        System.out.println(result.toString());
    }

    private final Helper<X> helper;
}
