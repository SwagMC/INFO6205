package edu.neu.coe.info6205.sort.par;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
class ParSort {

    public static int cutoff = 1000;

    public static void sort(int[] array, int from, int to) {
        if (to - from < cutoff) Arrays.sort(array, from, to);
        else {
            CompletableFuture<int[]> parsort1 = parsort(array, from, from + (to - from) / 2); // TO IMPLEMENT
            CompletableFuture<int[]> parsort2 = parsort(array, from + (to - from) / 2, to); // TO IMPLEMENT
            CompletableFuture<int[]> parsort = parsort1.thenCombine(parsort2, (xs1, xs2) -> {
                        int[] result = new int[xs1.length + xs2.length];
                        // TO BE IMPLEMENTED ...
                        int i = 0;
                        int j = 0;
                        for (int idx = 0; idx < result.length; idx++) {
                            if (i >= xs1.length)  result[idx] = xs2[j++];
                            else if (j >= xs2.length)  result[idx] = xs1[i++];
                            else if (xs1[i] < xs2[j]) result[idx] = xs1[i++];
                            else result[idx] = xs2[j++];
                        }
                        // ... END IMPLEMENTATION
                        return result;
                    });

            parsort.whenComplete((result, throwable) -> System.arraycopy(result, 0, array, from, result.length));
//            System.out.println("# threads: "+ ForkJoinPool.commonPool().getRunningThreadCount());
            parsort.join();
        }
    }

    private static CompletableFuture<int[]> parsort(int[] array, int from, int to) {
        return CompletableFuture.supplyAsync(
                () -> {
                    int[] result = new int[to - from];
                    // TO BE IMPLEMENTED ...
                    sort(array, from, to);
                    System.arraycopy(array, from, result, 0, result.length);
                    // ... END IMPLEMENTATION
                    return result;
                }
        );
    }
}