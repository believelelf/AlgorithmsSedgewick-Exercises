package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;
import com.weiquding.stdlib.StdRandom;

/**
 * 快速排序：
 * 1. 切分两个子数组，当两个子数组都有序时，全局数组有序。
 * 2. 切分关键：
 * 2.1 对于某个j,a[j]已经排定
 * 2.2 a[lo]到a[j-1]中的所有元素都不大于a[j]
 * 2.3 a[j+1]到a[hi]中的所有元素都不大于a[j]
 */
public class Quick {

    public static void sort(Comparable[] a) {
        // 快速排序
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);

    }

    /**
     * 排序
     *
     * @param a
     * @param lo
     * @param hi
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 切分，
        int j = partition(a, lo, hi);
        // 将左边子数组排序
        sort(a, lo, j - 1);
        // 将右子数组排序
        sort(a, j + 1, hi);
    }

    /**
     * 切分函数：
     * 先随意取a[lo]作为切分元素，即那个将会被排定的元素，然后从数组的左端开始右扫描直到找到一个大于等于它的元素，再从数组的右端开始向左扫描直到找到一个小于等于它的元素。这两个元素是没有排定的，交换它们的位置，如此继续。
     *
     * @param a
     * @param lo
     * @param hi
     * @return
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        // 将数组切分为a[lo.. i-1], a[i], a[i+1.. hi]
        int i = lo, j = hi + 1; // 左右扫描指针
        Comparable v = a[lo]; // 切分元素
        while (true) {
            // 扫描左右，检查扫描是否结束并交换元素
            while (less(a[++i], v)) {
                if (i == hi) {
                    break;
                }
            }
            while (less(v, a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            // 如果存在两个未排定的元素，则进行交换位置
            exch(a, i, j);
        }
        // 将v = a[j]放入正确的位置
        exch(a, lo, j);
        // a[lo.. j-1] <= a[j] <= a[j+1..hi]达成
        return j;

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
