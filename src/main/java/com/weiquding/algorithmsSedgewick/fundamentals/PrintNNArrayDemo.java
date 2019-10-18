package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.Scanner;

/**
 * ### 阿里面试题：打印 N*N顺时针螺旋数组
 * * 规律如下：
 * ~~~
 * 1       2       3       4       5
 * 16      17      18      19      6
 * 15      24      25      20      7
 * 14      23      22      21      8
 * 13      12      11      10      9
 * ~~~
 * <p>
 * #### 解题思路
 * 1. 将整个过程分解为两步，第一步为构建一个二维数组，第二步为打印二维数组的值。
 * 2. 对构建二维数组的过程进行拆解，可以分为以下几步：
 * 3. 构建int[N][N]初始值为-1的二维数组
 * 4. N\*N确定了二维数组中的最大值，构建一个从1开始增长到N\*N的循环。
 * 5. 根据数值螺旋增加的特征，可以将其增长拆分上、右、下、左四个方法来分别增加或减少int[i][j]的下标，以对应到其在二维坐标的位置。
 */
public class PrintNNArrayDemo {


    public static void main(String[] args) {
        // 读入变量N
        int n = new Scanner(System.in).nextInt();
        //  构建N*N初始化数组
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = -1;
            }
        }
        // 构建1 ~ n*n的循环
        // 初始方向为上
        Direction direction = Direction.UP;
        // 初始数值
        int num = 1;
        // 初始二维数组的下标,i-->外（行），j-->内（列）
        int i = 0;
        int j = 0;
        while (num <= n * n){
            arr[i][j] = num;
            switch (direction){
                case UP:
                    // 数值在上方增加时，i不变，j增加，直到j>=n或对应位置已经有非-1值
                    j++;
                    if(j >= n || arr[i][j] != -1){
                        j--;
                        i++;
                        direction = Direction.RIGHT;
                    }
                    break;
                case RIGHT:
                    // 数值在右方增加时，j不变，i增加，直到i>=n或对应位置已经有非-1值
                    i++;
                    if(i >= n || arr[i][j] != -1){
                        i--;
                        j--;
                        direction = Direction.DWON;
                    }
                    break;
                case DWON:
                    // 数值在下方增加时，i不变，j减少，直到j<0或对应位置已经有非-1值
                    j--;
                    if(j < 0 || arr[i][j] != -1){
                        j++;
                        i--;
                        direction = Direction.LEFT;
                    }
                    break;
                case LEFT:
                    // 数值在右方增加时，j不变，i减少，直到i<0或对应位置已经有非-1值
                    i--;
                    if(i < 0 || arr[i][j] != -1){
                        i++;
                        j++;
                        direction = Direction.UP;
                    }else{
                    }
                    break;
                default:
                    break;
            }
            num++;
        }
        // 打印数组
        for(int a = 0; a < n; a++){
            for(int b = 0; b < n; b++){
                if(b == n -1){
                    System.out.format("\t%d\n", arr[a][b]);
                }else{
                    System.out.format("\t%d", arr[a][b]);
                }
            }
        }

    }

    /**
     * 定义数值螺旋的四个方向
     */
    static enum Direction {
        UP,
        RIGHT,
        DWON,
        LEFT
    }

}
