package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 打印出一个M行N列的二维数组的转置（交换行和列）
 */
public class PrintTwoDimensionalArrayTranspose {

    public static void main(String[] args) {
        int[][] arrays = new int[][]{{1, 2, 3, 4}, {2, 1, 5, 4}, {1, 4, 2, 5}};
        printArrays(arrays);
    }

    private static void printArrays(int[][] arrays) {
        int width = arrays[0].length;
        int height = arrays.length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(arrays[j][i] + " ");
            }
            System.out.println();
        }

    }
}
