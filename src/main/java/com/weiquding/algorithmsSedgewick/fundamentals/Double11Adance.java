package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * ##### 问题
 * 购物活动中有各种促销活动，比如“满 200 元减 50 元”。假设你的购物车中有 n 个（n>100）想买的商品，她希望从里面选几个，在凑够满减条件的前提下，让选出来的商品价格总和最大程度地接近满减条件（200 元），这样就可以极大限度地“薅羊毛”。
 *
 * ##### 分析
 * 1. 使用动态规划解决此问题，其符合多阶段决策最优解模型，使用状态转移表法求解思路，定义一个二维数组boolean states[n][v], 其n表示物品选择的决策阶段，v代表当前选择的所有物品的总价。
 * 2. 为最大限度“薅羊毛”，需求得一个坐标最大限度 states[n][v] 接近 v =200, 且小于限制金额（如满减条件的两倍）
 * 3. 取得对应坐标点states[i][j]后，再判断达到此状态的前置节点，其只能为states[i-1][j]和states[i-1][j- values[i]],states[i-1][j]代表第i个物品未选择，不用考虑。states[i-1][j-values[j]],代表第i个物品有选择，打印此物品，再继续看前一阶段的物品是否有选择，直到j==0,即此物品选择组合拆分完毕。
 */
public class Double11Adance {

    /**
     * 选择符合满减条件的最能“薅羊毛”的商品组合
     * @param items 商品表
     * @param number 商品个数
     * @param condition 满减条件值，如200
     * @return 选择的商品组合
     */
    public List<Product> double11Adance(Product[] items, int number, int condition){
        List<Product> products = new LinkedList<>();
        // 确定最大价格限制，太大了没有“薅羊毛”的价值，决定此值用于构建状态表
        int limit = 2 * condition;
        boolean[][] states = new boolean[number][limit + 1];
        // 处理第1个物品的选择
        states[0][0] = true;
        if(items[0].getValue() <= limit){
            states[0][items[0].getValue()] = true;
        }
        // 处理后续n-1阶段的决策
        for(int i = 1; i < number; i++){
            // 不选择此物品,则直接将前一阶段（i-1）的状态进行复制
            for(int j = 0; j <= limit; j++){
                if(states[i - 1][j]){
                    states[i][j] = true;
                }
            }

            // 选择此物品，则将前一阶段状态（i-1, j）累积本阶段物品的价值变为状态（i, j + values[i]）,但不超出限制金额
            for(int j = 0 ; j <= limit - items[i].getValue(); j++){
                if(states[i - 1][j]){
                    states[i][j + items[i].getValue()] = true;
                }
            }
        }

        // 从最后一个阶段中选择满足条件（大于满减条件condition小于等于金额限制limit）的节点
        int j;
        for(j = condition; j <= limit; j++){
            if(states[number - 1][j]){
                break;
            }
        }

        // 判断是否有此状态满足要求
        if(j == limit +1){
            // 无节点东路要求，返回空集合
            return products;
        }
        System.out.println("选择物品总价值["+j+"]");

        // 根据节点状态，反推其物品组合，从最一个阶段向前反推各个阶段的物品是否有选择，直到j==0,
        for(int i = number - 1; i >= 1; i--){ //第2轮的物品是否有选择要根据前一轮的状态进行判断，所以不能减到0
            // 判断是否本阶段物品有选择
            if(j - items[i].getValue() >= 0 && states[i - 1][j - items[i].getValue()]){
                products.add(items[i]);
                j = j - items[i].getValue();
            }else {
                System.out.println("没有选择此物品["+items[i]+"]");
            }
        }
        if(j != 0){
            // 如果j没有减到0，说明第1轮的物品也有选择
            products.add(items[0]);
        }
        return products;
    }

    public static void main(String[] args) {
        Product[] items = new Product[]{
            new Product(1, "商品1", 11),
            new Product(2, "商品2", 13),
            new Product(3, "商品3", 19),
            new Product(4, "商品4", 25),
            new Product(5, "商品5", 17),
            new Product(6, "商品6", 39),
            new Product(7, "商品7", 60),
            new Product(8, "商品8", 49),
            new Product(9, "商品9", 100),
            new Product(10, "商品10", 29),
            new Product(11, "商品11", 50),
            new Product(12, "商品12", 34),
            new Product(13, "商品13", 3),
            new Product(14, "商品14", 29),
            new Product(15, "商品15", 14),
            new Product(16, "商品16", 47),
            new Product(17, "商品17", 7),
            new Product(18, "商品18", 21),
            new Product(19, "商品19", 15),
            new Product(20, "商品20", 37)
        };
        List<Product> products = new Double11Adance().double11Adance(items, 20,200);
        System.out.println("选择物品个数为"+products.size()+"；\n其组合为:");
        for(Product product : products){
            System.out.println(product);
        }
    }

    /**
     * 商品属性
     */
    public static class Product{
        /**商品id*/
        private final int id;
        /**
         * 商品名
         */
        private final String name;
        /**
         * 商品单价
         */
        private final int value;

        public Product(int id, String name, int value){
            this.id = id;
            this.name = name;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o){
                return true;
            }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
            Product product = (Product) o;
            return id == product.id &&
                    value == product.value &&
                    Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, value);
        }
    }
}
