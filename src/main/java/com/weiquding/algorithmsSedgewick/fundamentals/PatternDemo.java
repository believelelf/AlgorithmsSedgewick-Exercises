package com.weiquding.algorithmsSedgewick.fundamentals;

import java.util.HashMap;
import java.util.Map;

/**
 * 由逗号分隔的字符串，如“北京,吉林,吉林,北京”， 输入一个匹配模式（简单的以字符来写），
 * 比如 aabb, 来判断该字符串是否符合该模式， 举个例子：
 * 1. pattern = "abba", str="北京,吉林,吉林,北京" 返回 true
 * 2. pattern = "aabb", str="北京,吉林,吉林,北京" 返回 false
 * 3. pattern = "baab", str="北京,吉林,吉林,北京" 返回 true
 */
public class PatternDemo {

    public static void main(String[] args) {
        System.out.println(findPattern("abba", "北京,吉林,吉林,北京"));
        System.out.println(findPattern("aabb", "北京,吉林,吉林,北京"));
        System.out.println(findPattern("baab", "北京,吉林,吉林,北京"));
    }
    public static boolean findPattern(String pattern, String str){
        if(pattern == null || str == null){
            return false;
        }
        char [] pChars = pattern.toCharArray();
        String[] strs = str.split(",");
        if(pChars.length != strs.length){
            return false;
        }
        Map<Character,String> map = new HashMap<>();
        for(int i = 0; i < pChars.length; i++){
            str = map.get(pChars[i]);
            if(str != null && !str.equals(strs[i])){
                return false;
            }
            map.put(pChars[i], strs[i]);
        }
        return true;
    }
}
