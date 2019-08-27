package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;

/**
 * 选择排序：不断地选择剩余元素中的最小元素
 * 1.实现
 * 1.1 找到数组中最小的那个最小的元素，将它与数组的第一个元素交换位置。
 * 1.2 在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。
 * 1.3 如此往复，直到整个数组元素排序。
 * <p>
 * 2. 特点：
 * 2.1 运行时间与输入无关: 输入数据有序或无序都不影响比较次数
 * 2.2 数据移动是最少的：每次交换都会改变两个数组元素的值，因此选择排序用了N次交换。
 * <p>
 * 3. 复杂度分析：
 * 3.1 时间复杂度： O(N^2)
 * 3.2 空间复杂度： O(1)
 * <p>
 * 4 稳定性（如果一个排序算法能够保留数组中重复元素的相对位置则可以被称为是稳定的）：稳定
 */

public class Selection {

    /**
     * 将a[]进行升序排列
     * @param a
     */
    public static void sort(Comparable[] a) {
        // 具体算法实现 [选择]
        int length = a.length;
        for(int i = 0; i < length; i++){
            // 将a[i]与a[i+1... length-1]中最小元素交换

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
