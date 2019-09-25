package com.weiquding.algorithmsSedgewick.sorting;

import com.weiquding.stdlib.StdOut;

/**
 * 基于堆实现的优先队列
 *
 * @param <Key>
 */
public class MaxPQ<Key extends Comparable<Key>> {


    private Key[] pq; // 基于堆的完全二叉树
    private int N = 0;  // 存储于pq[1.. N]中，pq[0]未使用

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(N--, 1);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    /**
     * 由下至上的堆有序化（上浮）
     */
    private void swim(int k) {
        // 子结点小于父节点时上浮
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k /= 2;
        }
    }

    /**
     * 由上至下的堆有序化（下沉）
     *
     * @param k
     */
    private void sink(int k) {
        // 父节点小于子节点的大值时下沉
        while (2 * k <= N) {
            int j = 2 * k;
            // 取子节点较大值
            if (j < N && less(j, j + 1)) {
                j++;
            }
            // 如果不小于跳过循环
            if(!less(k, j)){
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**
     * v 是否小于w
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * 交换下标i与j处的数值
     *
     * @param i 下标i
     * @param j 下标j
     */
    private void exch(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /**
     * 在单行在打印数组
     *
     * @param a
     */
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }


}
