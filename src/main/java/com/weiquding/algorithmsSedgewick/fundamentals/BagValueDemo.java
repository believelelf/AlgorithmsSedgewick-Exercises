package com.weiquding.algorithmsSedgewick.fundamentals;

/**
 * 问题：
 * 对于一组不同重量，不可重复的物品，选择一些物品装入背包，在满足背包最大重量限制的情况下，背包中最大可装入的重量是多少？假定物品个数为5个，其重量分别为2,2,4,6,3;其物品价值分别为3，4，8，9，6；背包最大限重为9。
 * <p>
 * 分析：
 * 1. 使用回溯算法，多阶段决策最优解，每一阶段对应一个物品的选择，其有放入与不放入两个选择，在递归树派生出两个节点，另外选择放入此物品时，还要保证背包重量不超限制，从而去除不必要的分支。（ 需要3 个变量（i, cw, cv）来表示一个状态。其中，i 表示即将要决策第 i 个物品是否装入背包，cw 表示当前背包中物品的总重量，cv 表示当前背包中物品的总价值。此时使用回溯算法无法用备忘录过滤重复节点，因为在递归树只有二个变量构成的重复节点，如f(2,2,4) 和 f(2,2,3)）
 * 2. 使用动态规划，对应前面的回溯算法实现，将求解过程划分为n个过程，每个阶段会决策一个物品是否放入背包中，每个物品决策完后，背包中的物品的重量会有多种情况，对应到不同的多种不同的状态。以一个二维数组int states[n][w+1]作为状态表，n代表纵向物品决策阶段，w代表每个决策阶段完成后背包中重量的可能，如果数组中的值为每个阶段决策完后背包中物品的总价值v。如第0个物品的重量是2,价值是3，那么决策完成后，会有两种状态states[0][0] = 0, states[0][2] =3;以此类推，决策完所有物品后，整个states数组就计算完成了。此时只要找出states数组中最后一层值最大的一个元素，就是背包中物品重量满足要求的情况下价值的最大值。
 */
public class BagValueDemo {

    /**
     * 背包中物品总价值的最大值
     */
    private int maxValue = 0;

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
    /**
     * 物品的单重表
     */
    private final int[] valueTable = {3, 4, 8, 9, 6};


    public int[] getWeightTable() {
        return weightTable;
    }

    public int[] getValueTable(){
        return valueTable;
    }

    public int getLimit() {
        return limit;
    }

    public int getNumber() {
        return number;
    }

    public int getMaxValue() {
        return maxValue;
    }


    /**
     * 使用回溯算法，找到重量最大值maxWeight
     *
     * @param phase  阶段，从0开始
     * @param weight 前一阶段决策后的对应的总重量值
     * @param weight 前一阶段决策后的对应的总价值
     */
    public void findMaxValue(int phase, int weight, int value) {
        if (weight == limit || phase == number) {
            // 背包已经达到最大限重或已经决策完最后一个物品
            if (value > maxValue) {
                maxValue = value;
            }
            return;
        }
        // 不放入第phase阶段的物品
        findMaxValue(phase + 1, weight, value);

        // 放入第phase阶段的物品,要限重
        if (weight + weightTable[phase] <= limit) {
            findMaxValue(phase + 1, weight + weightTable[phase], value + valueTable[phase]);
        }
    }


    /**
     * 使用动态规划解决问题，状态表使用二维数组int states[number][limit+1],保存了每个阶段决策后的状态。
     *
     * @param weightTable 物品重量对照表
     * @param valueTable 物品价值对照表
     * @param number      物品个数
     * @param limit       背包限重
     * @return 背包中可容纳的最大重量
     */
    public int findMaxValueDP(int[] weightTable, int[] valueTable, int number, int limit) {
        // 初始化状态表,数组的值代表价值
        int[][] states = new int[number][limit + 1];
        for(int i = 0; i < number; i++){
            for(int j = 0; j < limit; j++){
                states[i][j] = -1;
            }
        }
        // 决策第1个物品（下标为0）
        states[0][0] = 0; //第1个物品不放入
        if (weightTable[0] < limit) {
            states[0][weightTable[0]] = valueTable[0];// 第1个物品放入
        }
        // 决策后续阶段，状态表转移法
        for (int i = 1; i < number; i++) { // 决策阶段
            // 第i阶段不放入，直接复制上一决策阶段的状态
            for (int j = 0; j <= limit; j++) {
                if (states[i - 1][j] >= 0) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 第i阶段放入， 将上一决策阶段的状态，累计本阶段物品的重量，生成新的状态。
            for (int j = 0; j <= limit - weightTable[i]; j++) { // 控制当前阶段的决策物品要能放入
                if (states[i - 1][j] >= 0) {
                    // 要判断同一单元格原价值与新价值大小
                    int newValue = states[i - 1][j] + valueTable[i];
                    if(newValue > states[i][j + weightTable[i]]){
                        states[i][j + weightTable[i]] = newValue;
                    }
                }
            }
        }

        // 从状态表中选择输出结果,从最后一个决策阶段的末尾开始循环
        int maxValue = 0;
        for (int i = limit; i >= 0; i--) {
            if (states[number - 1][i] > maxValue) {
                maxValue = states[number - 1][i];
            }
        }
        return maxValue;
    }

    /**
     * 使用动态规划解决问题，状态表使用一维数组int states[limit+1],保存了每个阶段决策后的状态，减少空间复杂度
     * 原因为决策完成后，实际只需最后一轮的状态。
     *
     * @param weightTable 物品重量对照表
     * @param valueTable  物品重量对照表
     * @param number      物品个数
     * @param limit       背包限重
     * @return 背包中可容纳的最大重量
     */
    public int findMaxValueDPOneDimensional(int[] weightTable, int[] valueTable, int number, int limit) {
        // 初始化状态表,数组的值代表价值
        int[] states = new int[limit + 1];
        for(int j = 0; j < limit; j++){
            states[j] = -1;
        }
        // 决策第1个物品（下标为0）
        states[0] = 0; //第1个物品不放入
        if (weightTable[0] < limit) {
            states[weightTable[0]] = valueTable[0];// 第1个物品放入
        }
        // 决策后续阶段，状态表转移法
        for (int i = 1; i < number; i++) { // 决策阶段
            // 第i阶段不放入，直接复制上一决策阶段的状态,无需处理

            // 第i阶段放入， 将上一决策阶段的状态，累计本阶段物品的重量，生成新的状态。
            // 需要从末尾开始处理，防止状态重复计算
            for (int j = limit - weightTable[i]; j >= 0; j--) { // 控制当前阶段的决策物品要能放入
                if (states[j] >= 0) {
                    int newValue = states[j] + valueTable[i];
                    if(newValue > states[j + weightTable[i]]){
                        states[j + weightTable[i]] = newValue;
                    }
                }
            }
        }

        // 从状态表中选择输出结果,从最后一个决策阶段的末尾开始循环
        int maxValue = 0;
        for (int i = limit; i >= 0; i--) {
            if (states[i] > maxValue) {
                maxValue = states[i];
            }
        }
        return maxValue;
    }


    public static void main(String[] args) {
        // 回溯
        BagValueDemo bagDemo = new BagValueDemo();
        bagDemo.findMaxValue(0, 0, 0);
        System.out.println(bagDemo.getMaxValue());

        // 动态规划（二维状态表）
        bagDemo = new BagValueDemo();
        int maxValue = bagDemo.findMaxValueDP(bagDemo.getWeightTable(), bagDemo.getValueTable(), bagDemo.getNumber(), bagDemo.getLimit());
        System.out.println(maxValue);

        // 动态规划（一维状态表）
        bagDemo = new BagValueDemo();
        maxValue = bagDemo.findMaxValueDPOneDimensional(bagDemo.getWeightTable(),bagDemo.getValueTable(), bagDemo.getNumber(), bagDemo.getLimit());
        System.out.println(maxValue);
    }
}
