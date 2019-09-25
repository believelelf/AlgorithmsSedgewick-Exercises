package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;

/**
 * 堆排序
 */
public class Heap {

    public static void sort(Comparable[] a) {
        // 构建堆有序
        int N = a.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        // 堆下沉排序
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    public static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            while (j < N && less(a, j, j + 1)) {
                j++;
            }
            if (!less(a, k, j)) {
                break;
            }
            exch(a, j, k);
            k = j;
        }

    }

    /**
     * v 是否小于w
     *
     * @param i
     * @param j
     * @return
     */
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[--i].compareTo(pq[--j]) < 0;
    }

    /**
     * 交换下标i与j处的数值
     *
     * @param i 下标i
     * @param j 下标j
     */
    private static void exch(Comparable[] pq, int i, int j) {
        Comparable temp = pq[--i];
        pq[i] = pq[--j];
        pq[j] = temp;
    }

    /**
     * 在单行在打印数组
     *
     * @param a
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * 测试数组元素是否有序
     *
     * @param a 数组
     * @return
     */
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a, i, i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strs = In.readStrings();
        sort(strs);
        assert isSorted(strs);
        show(strs);

    }

}
