package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import java.util.Arrays;

/**
 * 1.1.30编写一段程序，创建一个N*N的布尔数据。其中当i和j互质时（没有相同因子），a[i][j]=true,否则为false
 */
public class NNBooleanArray {

    public static boolean[][] nnBoolean(int n) {
        boolean[][] booleans = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i==0 || j ==0 || EuclideanAlgorithm.euclidean(i,j) ==1){
                    booleans[i][j] = true;
                }
            }
        }
        return booleans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(nnBoolean(9)));
    }
}
