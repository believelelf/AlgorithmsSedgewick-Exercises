package com.weiquding.algorithmsSedgewick.fundamentals.basics;


/**
 * 1.1.18题的解题思路：
 *
 * 1. 由b的取余运算想到二进制，即将b换算为二进制，余数为1时a存在，否则此次循环产生0
 * 2. 由a+a，想到2的n次方*a-->
 * 3. 如果是加数，即为各位存在的值相加。乘法即为有值位相乘
 * mystery（2， 25）
 * ===》
 *  25-> 1      1       0       0       1
 *  a->  2^4*a   2^3*a  2^2*a   2^1*a   2^0*a
 *  相加 2^4*a + 2^3*a + 2^0*a
 *
 */
public class Mystery {

    public static int mystery(int a, int b) {
        if (b == 0) {
            return 0;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) + a;
    }

    public static int mystery2(int a, int b) {
        if (b == 0) {
            return 1;
        }
        if (b % 2 == 0) {
            return mystery(a + a, b / 2);
        }
        return mystery(a + a, b / 2) * a;
    }


    public static void main(String[] args) {
        System.out.println(mystery(2, 25));
        System.out.println(mystery(3, 11));
        System.out.println(mystery2(2, 25));
        System.out.println(mystery2(3, 11));
    }

}
