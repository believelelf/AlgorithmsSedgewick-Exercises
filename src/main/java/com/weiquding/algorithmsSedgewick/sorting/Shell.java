package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;

/**
 * 希尔排序：
 * 1. 思想：使数据中任意间隔为h元素都是有序的。这样的数组被称为h有序数组。
 * 换句话说，一个h有序数组就是h个互相独立的有序数组编织在一起组成一个数组。
 * 2. 实现：对于每个h,用插入排序将h个子数组独立地排序。
 * 但因为子数组是相互独立的，一个更简单的方法是在h子数组中将每个元素交换到比它大的元素之前去。
 * 只需要在插入排序中将移动元素的距离由1改为h即可。这样希尔排序的实现就转化为了一个类似于插入排序便使用不同增量的过程。
 */
public class Shell {

    public static void sort(Comparable[] a) {
        // 希尔排序:将a[]按升序排列
        int length = a.length;
        // 构建子数组h的元素下标
        int h = 1;
        while (h < length / 3) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            // 将数组变为h有序
            for (int i = h; i < length; i++) {
                // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    // 当h为1，退化为插入排序。
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
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
