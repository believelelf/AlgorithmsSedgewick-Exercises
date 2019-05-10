package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 编写一个静态方法histogram()，接受一个整形数组a[]和一个整数M为参数并返回一个大小为M的数组，
 * 其中第i个元素的值为整数i在参数数组中出现的次数。
 * 如果a[]中的值均在0到M-1之间，返回数组中所有元素之和应该和a.length相等
 */
public class Histogram {

    public static int[] histogram(int[] a, int M) {
        int[] retArr = new int[M];
        for (int i = 0, len = a.length; i < len; i++) {
            int val = a[i];
            if (val >= 0 && val < M) {
                retArr[val]++;
            }
        }
        return retArr;
    }

    public static void main(String[] args) {
        //int[] a = {0, 2, 4, 2, 4, 1, 3, 5, 6, 9};
        int[] a = {0, 2, 4, 2, 4, 1, 3, 5};
        int length = a.length;
        int M = 6;
        int[] ret = histogram(a, M);
        int sum = 0;
        for (int val : ret) {
            sum += val;
        }
        if(sum == length){
            System.out.println("sum equals a.length");
        }
    }
}
