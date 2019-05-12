package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdIn;
import com.weiquding.stdlib.StdOut;

import java.util.Arrays;

/**
 * 1.1.23
 * java BinarySearch tinyW.txt + < tinyT.txt
 */
public class BinarySearchV2 {
    // precondition: array a[] is sorted
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if(key < a[mid]){
                 hi = mid - 1;
            } else if (key > a[mid]){
                lo = mid + 1;
            } else{
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);

        // read key;
        String flag = args[1];
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if ("+".equals(flag) && rank(key, whitelist) == -1){
                StdOut.println(key);
            }
            if ("-".equals(flag) && rank(key, whitelist) != -1){
                StdOut.println(key);
            }

        }
    }

}
