package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 1.1.27 二项分布，估计递归调用次数
 * 将已经计算过的值保存到数组中并给出一个更好的实现
 */
public class BinomialV1 {

    public static double binomial(int n, int k, double p, AtomicLong caller, double[][] values) {
        if (n == 0 && k == 0) {
            return 1.0;
        }
        if (n < 0 || k < 0) {
            return 0.0;
        }
        if (values[n][k] == -1) {
            caller.incrementAndGet();
            double val = (1.0 - p) * binomial(n - 1, k, p, caller, values) + p * binomial(n - 1, k - 1, p, caller, values);
            values[n][k] = val;
            return val;
        }
        return values[n][k];

    }

    public static double binomial(int n, int k, double p, AtomicLong caller) {
        double[][] values = new double[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                values[i][j] = -1;
            }
        }
        return binomial(n, k, p, caller, values);
    }

    public static void main(String[] args) {
        AtomicLong caller = new AtomicLong();
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        double p = Double.parseDouble(args[2]);
        System.out.println(binomial(n, k, p, caller));
        //0.24609375
        System.out.println("n=" + n + ";k=" + k + ";p=" + p + ";caller=" + caller.get());
        //n=10;k=5;p=0.5;caller=50

    }


}
