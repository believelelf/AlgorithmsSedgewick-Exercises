package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;

/**
 * 归并排序：分治思想。
 * 1.将两个子数组进行排序，然后通过归并两个子数组来将整个数组排序。
 */
public class Merge {

    // 归并所需的辅助数组
    private static Comparable[] aux;

    public static void sort(Comparable[] a) {
        // 自顶向下的归并排序
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // 将左边排序
        sort(a, lo, mid);
        // 将右边排序
        sort(a, mid + 1, hi);
        // 归并两个子数组
        merge(a, lo, mid, hi);
    }

    /**
     * 原地归并的抽象
     *
     * @param a   数组
     * @param lo  低端下标
     * @param mid 中位
     * @param hi  高端下标
     */
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        // 将a[lo.. mid]和a[mid+1, hi]归并
        int i = lo;
        int j = mid + 1;
        // 复制a[]对应元素到aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            // 将两个子数组归并到a[]
            if (i > mid) {
                // 低端数组已经迭代完成，直接归并高端部分
                a[k] = aux[j++];
            } else if (j > hi) {
                // 高端数组已经迭代完成,直接归并低端部分
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                // 比较两个数组的值大小
                a[k] = aux[i++];
            } else {
                a[k] = aux[j++];
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
