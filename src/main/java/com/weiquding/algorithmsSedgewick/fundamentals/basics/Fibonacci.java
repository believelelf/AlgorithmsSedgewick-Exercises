package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.1.19优化F(N),利用缓存前值的方法
 */
public class Fibonacci {

    public static long F(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return F(N - 1) + F(N - 2);
    }

    public static long FCached(int N, Map<Integer, Long> cached) {
        if (N == 0) {
            cached.put(0, 0L);
            return 0;
        }
        if (N == 1) {
            cached.put(1, 1L);
            return 1;
        }
        Long a = cached.get(N - 1) == null ? F(N - 1) : cached.get(N - 1);
        Long b = cached.get(N - 2) == null ? F(N - 2) : cached.get(N - 2);
        Long ret = a + b;
        cached.put(N, ret);
        return ret;
    }

    public static void main(String[] args) {
 /*       for (int N = 0; N < 100; N++) {
            System.out.println(N + " " + F(N));
        }*/
        Map<Integer, Long> cached = new HashMap<Integer, Long>();
        for (int N = 0; N < 100; N++) {
            System.out.println(N + " " + FCached(N, cached));
        }
    }
}
