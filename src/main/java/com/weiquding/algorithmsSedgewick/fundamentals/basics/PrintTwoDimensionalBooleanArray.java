package com.weiquding.algorithmsSedgewick.fundamentals.basics;

/**
 * 编写一段代码，打印出一个二维布尔数组的内容。其中使用*表示值，空格表示假。打印出行号和列号。
 */
public class PrintTwoDimensionalBooleanArray {


    public static void main(String[] args) {
        boolean[][] booleans = new boolean[4][5];
        booleans[0] = new boolean[]{true, false, true, false, false};
        booleans[1] = new boolean[]{true, false, false, false, true};
        booleans[2] = new boolean[]{false, true, true, false, true};
        booleans[3] = new boolean[]{true, false, false, true, false};
        printArrays(booleans);

    }


    private static void printArrays(boolean[][] booleans) {
        assert booleans != null && booleans.length > 0;
        int width = booleans[0].length;
        for (int i = 0; i < width; i++) {
            if (i == 0) {
                System.out.print("0 ");
            }
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        int lineNumber = 1;
        for (boolean[] vals : booleans) {
            System.out.print(lineNumber++);
            System.out.print(" ");
            for (boolean val : vals) {
                System.out.print(convertValue(val) + " ");
            }
            System.out.println();
        }
    }


    private static String convertValue(boolean val) {
        return val ? "*" : " ";
    }

}
