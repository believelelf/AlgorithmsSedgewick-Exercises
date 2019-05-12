package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdIn;

import java.util.Arrays;

/**
 * 1.1.29
 * rank()方法全接受一个键和一个整数有序数组作为参数并返回数组中小于该键的元素数量
 * count()方法返回数组中等于该键的元素的数量。
 * 验证方法：
 * 如果i和j分别是rank(key, a)及count(key,a)的返回值，则满足a[i...i+j-1]部分的元素就是所有和key相等的元素
 * java BinarySearch tinyW.txt < tinyT.txt
 */
public class BinarySearchV3 {
    // precondition: array a[] is sorted
    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                // 向前移动mid,直到不相等。
                while (--mid >= 0 && a[mid] == key) ;
                return mid + 1;
            }
        }
        return -1;
    }

    public static int count(int key, int[] a) {
        int c = 0;
        for (int j = rank(key, a); a[j] == key; j++) {
            c++;
        }
        return c;
    }

    public static boolean verify(int key, int[] a, int i, int j) {
        for (int b = 0; b < a.length; b++) {
            if ((b < i || b > i + j - 1) && a[b] == key
                    || (b >= i && b <= i + j - 1) && a[b] != key) {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        int[] whitelist = In.readInts(args[0]);

        Arrays.sort(whitelist);

        // read key;
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int i = rank(key, whitelist);
            int j = count(key, whitelist);
            boolean isVerifyed = verify(key, whitelist, i, j);
            System.out.println("rank(key,a)==" + i + ";count(key,a)==" + j + ";verify(key,a,i,j)=" + isVerifyed);
        }
    }

}
