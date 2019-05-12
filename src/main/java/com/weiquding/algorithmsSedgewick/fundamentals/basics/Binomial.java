package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1.1.27 二项分布，估计递归调用次数
 */
public class Binomial {

    public static double binomial(int n, int k, double p, AtomicLong caller) {
        if (n == 0 && k == 0) {
            return 1.0;
        }
        if (n < 0 || k < 0) {
            return 0.0;
        }
        caller.incrementAndGet();
        return (1.0 - p) * binomial(n - 1, k, p, caller) + p * binomial(n - 1, k - 1, p, caller);
    }

    public static void main(String[] args) {
        AtomicLong caller = new AtomicLong();
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        System.out.println(binomial(n, k, p, caller));
        //0.24609375
        System.out.println("n=" + n + ";k=" + k + ";p=" + p + ";caller=" + caller.get());
        //n=10;k=5;p=0.5;caller=1233

    }


}
