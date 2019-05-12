package com.weiquding.algorithmsSedgewick.fundamentals.basics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 * 1.1.21 编写一个程序，从标准输入按行读取数据，并按规定打印出来
 */
public class PrintNum {

    public static void printNum(){
        String[] lines = new String[10];
        int lineIndex = 0;
        try(Scanner scanner = new Scanner(System.in).useDelimiter("\\s")){
            while(scanner.hasNextLine()){
                String name = scanner.next();
                if("EOF".equals(name)){
                    break;
                }
                int num1 = scanner.nextInt();
                int num2 = scanner.nextInt();
                String num3 = new BigDecimal(num1).divide(new BigDecimal(num2), 3, RoundingMode.HALF_UP).toPlainString();
                lines[lineIndex++] = "" + name + "\t" + num1+ "\t" + num2 + "\t" + num3;
            }
        }
        for(String line : lines){
            if(line != null){
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) {
        printNum();
    }
}
