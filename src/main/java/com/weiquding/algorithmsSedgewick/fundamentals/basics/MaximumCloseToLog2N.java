package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 编写一个静态方法lg,接受一个整数型参数N,返回不大于log2N的最大整数。不要使用Math库
 * 思路：转化为2的n次方不大于N
 */
public class MaximumCloseToLog2N {

    public static void main(String[] args) {
        System.out.println(lg(1));
        System.out.println(lg(2));
        System.out.println(lg(3));
        System.out.println(lg(8));
        System.out.println(lg(9));
        System.out.println(lg(16));
        System.out.println(lg(20));
        System.out.println(lg(32));
    }

    public static int lg(int num) {
        int n = -1;
        do{
            n++;
        } while ((num = num / 2) != 0);
        return n;
    }
}
