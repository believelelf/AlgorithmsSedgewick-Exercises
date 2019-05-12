package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 1.1.22 打印参数lo及hi,并按照递归的深度缩进
 */
public class BinarySearchV1 {

    public static int rank(int key, int[] a) {
        int retract = 0;
        return rank(key, a, 0, a.length - 1, retract);
    }

    public static int rank(int key, int[] a, int lo, int hi, int retract) {
        printRetract(lo, hi, retract);
        if (lo > hi) {
            return -1;
        }
        int mid = lo + (hi - lo) / 2;
        if (key < a[mid]) {
            return rank(key, a, lo, mid - 1, ++retract);
        } else if (key > a[mid]) {
            return rank(key, a, mid + 1, hi, ++retract);
        }else {
            return mid;
        }
    }

    private static void printRetract(int lo, int hi, int retract) {
        while (retract-- > 0){
            System.out.print("\t");
        }
        System.out.println(lo + "\t" + hi);
    }

    public static void main(String[] args) {
        System.out.println("===================");
        System.out.println(rank(10, new int[]{1, 2, 3, 4}));
        System.out.println("===================");
        System.out.println(rank(1, new int[]{1, 2, 3, 4}));
    }

}
