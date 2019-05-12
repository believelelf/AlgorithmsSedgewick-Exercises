package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 编写一个递归的静态方法计算ln(N!)的值
 * https://www.av8n.com/physics/stirling-factorial.htm
 * ln(N!)	 	=	 	ln(1) + ln(2) + ln(3) + ⋯ + ln(N)
 */
public class LnNFactorial {

    public static double lnNFactorial(int n) {
        if (n == 1) {
            return 0D;
        }
        return Math.log(n) + lnNFactorial(n - 1);
    }

    public static void main(String[] args) {
        System.out.println(lnNFactorial(1));
        System.out.println(lnNFactorial(2));
        System.out.println(lnNFactorial(3));
       // System.out.println(Math.log(3));
        System.out.println(lnNFactorial(10));
    }


}
