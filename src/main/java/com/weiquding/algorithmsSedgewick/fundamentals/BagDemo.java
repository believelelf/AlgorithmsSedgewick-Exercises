package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * 问题：
 * 对于一组不同重量，不可重复的物品，选择一些物品装入背包，在满足背包最大重量限制的情况下，背包中最大可装入的重量是多少？假定物品个数为5个，其重量分别为2,2,4,6,3;背包最大限重为9。
 * <p>
 * 分析：
 * 1. 使用回溯算法，多阶段决策最优解，每一阶段对应一个物品的选择，其有放入与不放入两个选择，在递归树派生出两个节点，另外选择放入此物品时，还要保证背包重量不超限制，从而去除不必要的分支。
 * 2. 使用动态规划，对应前面的回溯算法实现，将求解过程划分为n个过程，每个阶段会决策一个物品是否放入背包中，每个物品决策完后，背包中的物品的重量会有多种情况，对应到不同的多种不同的状态。以一个二维数组boolean states[n][w+1]作为状态表，n代表纵向物品决策阶段，w代表每个决策阶段完成后背包中重量的可能，如果数据中的值为true，则代表有此可能，否则没有此重量。如第0个物品的重量是2，那么决策完成后，会有两种状态states[0][0] = true, states[0][2] =true;以此类推，决策完所有物品后，整个states数组就计算完成了。此时只要找出states数组中最后一层值为true最接近w（9）的值，就是背包中物品总重量的最大值。
 */
public class BagDemo {

    /**
     * 背包中物品总重量的最大值
     */
    private int maxWeight = 0;

    /**
     * 物品的个数
     */
    private final int number = 5;

    /**
     * 背包限重
     */
    private final int limit = 9;

    /**
     * 物品的单重表
     */
    private final int[] weightTable = {2, 2, 4, 6, 3};


    public int[] getWeightTable(){
        return weightTable;
    }

    public int getLimit(){
        return limit;
    }

    public int getNumber(){
        return number;
    }

    public int getMaxWeight() {
        return maxWeight;
    }


    /**
     * 使用回溯算法，找到重量最大值maxWeight
     *
     * @param phase  阶段，从0开始
     * @param weight 前一阶段决策后的对应的总重量值
     */
    public void findMaxWeight(int phase, int weight) {
        if (weight == limit || phase == number) {
            // 背包已经达到最大限重或已经决策完最后一个物品
            if (weight > maxWeight) {
                maxWeight = weight;
            }
            return;
        }
        // 不放入第phase阶段的物品
        findMaxWeight(phase + 1, weight);

        // 放入第phase阶段的物品,要限重
        if (weight + weightTable[phase] <= limit) {
            findMaxWeight(phase + 1, weight + weightTable[phase]);
        }
    }

    private boolean[][] mem = new boolean[number][limit + 1];

    /**
     * 使用回溯算法 + 备忘录，消除重复节点，找到重量最大值maxWeight
     *
     * @param phase  阶段，从0开始
     * @param weight 前一阶段决策后的对应的总重量值
     */
    public void findMaxWeightMem(int phase, int weight) {
        if (weight == limit || phase == number) {
            // 背包已经达到最大限重或已经决策完最后一个物品
            if (weight > maxWeight) {
                maxWeight = weight;
            }
            return;
        }
        // 使用备忘录，消除重复节点
        if(mem[phase][weight]){
            return;
        }
        mem[phase][weight] = true;

        // 不放入第phase阶段的物品
        findMaxWeightMem(phase + 1, weight);

        // 放入第phase阶段的物品,要限重
        if (weight + weightTable[phase] <= limit) {
            findMaxWeightMem(phase + 1, weight + weightTable[phase]);
        }
    }


    /**
     * 使用动态规划解决问题，状态表使用二维数组boolean states[number][limit+1],保存了每个阶段决策后的状态。
     * @param weightTable 物品重量对照表
     * @param number 物品个数
     * @param limit 背包限重
     * @return 背包中可容纳的最大重量
     */
    public int findMaxWeightDP(int[] weightTable, int number, int limit){
        // 初始化状态表
        boolean[][] states = new boolean[number][limit + 1];
        // 决策第1个物品（下标为0）
        states[0][0] = true; //第1个物品不放入
        if(weightTable[0] < limit){
            states[0][weightTable[0]] = true;// 第1个物品放入
        }
        // 决策后续阶段，状态表转移法
        for(int i = 1; i < number; i++){ // 决策阶段
            // 第i阶段不放入，直接复制上一决策阶段的状态
            for(int j = 0; j <= limit; j++){
                if(states[i -1][j]){
                    states[i][j] = states[i -1][j];
                }
            }
            // 第i阶段放入， 将上一决策阶段的状态，累计本阶段物品的重量，生成新的状态。
            for(int j = 0; j <= limit - weightTable[i]; j++){ // 控制当前阶段的决策物品要能放入
                if(states[i -1][j]){
                    states[i][j + weightTable[i]] = true;
                }
            }
        }

        // 从状态表中选择输出结果,从最后一个决策阶段的末尾开始循环
        for(int i = limit; i >= 0; i--){
            if(states[number -1][i]){
                return i;
            }
        }
        return 0;
    }

    /**
     * 使用动态规划解决问题，状态表使用一维数组boolean states[limit+1],保存了每个阶段决策后的状态，减少空间复杂度
     * 原因为决策完成后，实际只需最后一轮的状态。
     * @param weightTable 物品重量对照表
     * @param number 物品个数
     * @param limit 背包限重
     * @return 背包中可容纳的最大重量
     */
    public int findMaxWeightDPOneDimensional(int[] weightTable, int number, int limit){
        // 初始化状态表
        boolean[] states = new boolean[limit + 1];
        // 决策第1个物品（下标为0）
        states[0] = true; //第1个物品不放入
        if(weightTable[0] < limit){
            states[weightTable[0]] = true;// 第1个物品放入
        }
        // 决策后续阶段，状态表转移法
        for(int i = 1; i < number; i++){ // 决策阶段
            // 第i阶段不放入，直接复制上一决策阶段的状态,无需处理

            // 第i阶段放入， 将上一决策阶段的状态，累计本阶段物品的重量，生成新的状态。
            // 需要从末尾开始处理，防止状态重复计算
            for(int j = limit - weightTable[i]; j >= 0; j--){ // 控制当前阶段的决策物品要能放入
                if(states[j]){
                    states[j + weightTable[i]] = true;
                }
            }
        }

        // 从状态表中选择输出结果,从最后一个决策阶段的末尾开始循环
        for(int i = limit; i >= 0; i--){
            if(states[i]){
                return i;
            }
        }
        return 0;
    }



    public static void main(String[] args) {
        // 回溯
        BagDemo bagDemo = new BagDemo();
        bagDemo.findMaxWeight(0, 0);
        System.out.println(bagDemo.getMaxWeight());

        // 回溯+备忘录
        bagDemo = new BagDemo();
        bagDemo.findMaxWeightMem(0, 0);
        System.out.println(bagDemo.getMaxWeight());

        // 动态规划（二维状态表）
        bagDemo = new BagDemo();
        int maxWeight = bagDemo.findMaxWeightDP(bagDemo.getWeightTable(), bagDemo.getNumber(), bagDemo.getLimit());
        System.out.println(maxWeight);

        // 动态规划（一维状态表）
        bagDemo = new BagDemo();
        maxWeight = bagDemo.findMaxWeightDPOneDimensional(bagDemo.getWeightTable(), bagDemo.getNumber(), bagDemo.getLimit());
        System.out.println(maxWeight);
    }
}
