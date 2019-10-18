package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目2：实现3个线程交替打印1，2，3，4,...,99
 * 解题思路：
 *  基于一把锁和三个条件变量进行相互睡眠和通知
 */
public class PrintNumDemo {

    public static void main(String[] args) {
        PrintTask printTask = new PrintTask();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printTask.printNum(printTask.getFirstCondition(), printTask.getSecondCondition());
            }
        }, "1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printTask.printNum(printTask.getSecondCondition(), printTask.getThirdCondition());
            }
        }, "2").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                printTask.printNum(printTask.getThirdCondition(), printTask.getFirstCondition());
            }
        }, "3").start();
    }
}

/**
 * 打印任务
 */
class PrintTask {

    private int num = 1;
    // 多个线程共用一把锁
    private final Lock lock = new ReentrantLock();
    // 定义三个条件变量用于相互通知
    private final Condition firstCondition = lock.newCondition();
    private final Condition secondCondition = lock.newCondition();
    private final Condition thirdCondition = lock.newCondition();

    public void printNum(Condition self, Condition next) {
        try{
            lock.lock();
            while (num < 100){
                System.out.format("线程[%s]打印数字[%d]\n", Thread.currentThread().getName(), num);
                num ++;
                next.signal();
                try {
                    self.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            next.signal();
        }finally {
            lock.unlock();
        }
    }

    public Condition getFirstCondition() {
        return firstCondition;
    }

    public Condition getSecondCondition() {
        return secondCondition;
    }

    public Condition getThirdCondition() {
        return thirdCondition;
    }
}