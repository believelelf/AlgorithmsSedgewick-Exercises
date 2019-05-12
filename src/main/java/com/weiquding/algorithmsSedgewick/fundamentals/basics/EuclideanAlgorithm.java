package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 1.1.24
 * 使用欧几里得算法求两个正整数的最大公约数
 * <p>
 * 两个整数的最大公约数等于其中较小的那个数和两数相除余数的最大公约数。最大公约数（Greatest Common Divisor）缩写为GCD。
 * gcd(a,b) = gcd(b,a mod b) (不妨设a>b 且r=a mod b ,r不为0)
 * <p>
 * 1.1.25
 * 归纳法证明过程：
 * 1.假定给出一对非负数p和q,用p>q
 * 2.可以得出表达式： p = kq + r, (k，r为正数，且r<q),由此可以得出r= p % q
 * 3.假定d为p和q的公约数，即p和q都可被d整除
 * 4.对于表达式p = kq + r两边均除以d,得到p/d = kq/d + r/d,换算为 r/d = p/d - kq/d,由于第3条中假定， r/d 也为一个正整数，即d为也为r的公约数
 * 5.由第2条和第4条得出 d是q, p%q（即r）的公约数.
 * 6.假定d为q, p%q的公约数，则(p-kq) = r得出， d也是p的公约数。
 * 7.d=(p,q)和d=(q,p % q)的公约数是一样的，其最大公约数也必然相等
 */
public class EuclideanAlgorithm {

    public static int euclidean(int a, int b) {
        int max = a > b ? a : b;
        int min = a > b ? b : a;
        System.out.println("max = " + max + "; min=" + min);
        if (max % min == 0) {
            return min;
        }
        return euclidean(min, max % min);
    }

    public static void main(String[] args) {
        System.out.println(euclidean(1997, 615));
        System.out.println("-------------------");
        System.out.println(euclidean(105, 24));
        System.out.println("-------------------");
        System.out.println(euclidean(1111111, 1234567));
        System.out.println("-------------------");
        System.out.println(euclidean(16, 12));
    }


}
