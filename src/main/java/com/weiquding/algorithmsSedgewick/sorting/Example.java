package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.In;
import com.weiquding.stdlib.StdOut;

/**
 * 排序示例
 */
public class Example {

    public static void sort(Comparable[] a){
        // TODO 具体算法实现 [选择、冒泡、 插入、希尔、归并、 快速、 三向快速、 堆排序]

    }

    /**
     * v 是否小于w
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     * 交换下标i与j处的数值
     * @param a 数组
     * @param i 下标i
     * @param j 下标j
     */
    private static void exch(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 在单行在打印数组
     * @param a
     */
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    /**
     * 测试数组元素是否有序
     * @param a 数组
     * @return
     */
    public static boolean isSorted(Comparable[] a){
        for(int i = 1; i < a.length; i++){
            if(less(i, i-1)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
        String[] strs = In.readStrings();
        sort(strs);
        assert isSorted(strs);
        show(strs);

    }

}
