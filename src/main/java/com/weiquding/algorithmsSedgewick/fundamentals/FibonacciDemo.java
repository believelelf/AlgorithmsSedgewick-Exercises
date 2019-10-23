package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.HashMap;
import java.util.Map;

/**
 * 对于斐波那契数列fib(n) = fib(n-1) + fib(n-2) : {0, 1, 1, 2, 3, 5, 8, ...},求第n项的值。
 */
public class FibonacciDemo {

    /**
     * 递归实现
     * @param n
     * @return
     */
    public long fibonacci(int n){
        if (n < 2) {
            return n;
        }
        return fibonacci(n -1) + fibonacci(n -2);
    }



    private Map<Integer, Long> cached = new HashMap<>();

    /**
     * 递归实现，以缓存来消除重复节点的计算。
     * @param n
     * @return
     */
    public long fibonacciCached(int n){
        if(n < 2){
            cached.put(n, (long)n);
            return n;
        }
        Long a = cached.get(n - 1) == null ? fibonacciCached(n - 1) : cached.get(n - 1);
        Long b = cached.get(n - 2) == null ? fibonacciCached(n - 2) : cached.get(n - 2);
        Long ret = a + b;
        cached.put(n, ret);
        return ret;
    }

    /**
     * 动态规划实现
     * 状态转移方程fib(n) = fib(n-1) + fib(n -2),迭代递推方法实现
     * @param n
     * @return
     */
    public long fibonacciDP(int n){
        if(n < 2){
            return  n;
        }
        // 迭代递推
        int last = 1; // 上一项 n-1
        int nextToLast = 0; //上上一项n-2
        int answer = 1;
        for(int i = 2; i <= n; i++){
            answer = last + nextToLast;
            nextToLast = last;
            last = answer;
        }
        return answer;
    }

    public static void main(String[] args) {
        long currTime = System.currentTimeMillis();
        System.out.println(new FibonacciDemo().fibonacci(46));
        System.out.format("fibonacci use time:[%d]\n", System.currentTimeMillis() - currTime);
        currTime = System.currentTimeMillis();
        System.out.println(new FibonacciDemo().fibonacciCached(46));
        System.out.format("fibonacciCached use time:[%d]\n", System.currentTimeMillis() - currTime);
        currTime = System.currentTimeMillis();
        System.out.println(new FibonacciDemo().fibonacciDP(46));
        System.out.format("fibonacciDP use time:[%d]\n", System.currentTimeMillis() - currTime);
    }


}
