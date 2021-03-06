package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 给出exR1(6)的返回值：311361142246
 *
 */
public class ExR1 {

    public static String exR1(int n) {
        if (n <= 0) {
            return "";
        }
        return exR1(n - 3) + n + exR1(n - 2) + n;
    }

    public static void main(String[] args) {
        System.out.println(exR1(6));
    }

}
