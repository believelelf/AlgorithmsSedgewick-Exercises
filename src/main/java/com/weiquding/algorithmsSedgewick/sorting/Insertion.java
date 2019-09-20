package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;

/**
 * 插入排序：
 * 1. 将指针向左移动，在内循环中比较左边的数组元素值，得到左边有序数组。
 * 2. 插入排序比选择排序有优势，比如面对初始有序有数组。
 */
public class Insertion {

    public static void sort(Comparable[] a) {
        // 插入排序
        // 将a[]按升序排列
        int length = a.length;
        for (int i = 1; i < length; i++) {
            // 将a[i] 插入到a[i-1], a[i-2], a[i-3]
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }

    }

    /**
     * v 是否小于w
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * 交换下标i与j处的数值
     *
     * @param a 数组
     * @param i 下标i
     * @param j 下标j
     */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
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
            if (less(i, i - 1)) {
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
