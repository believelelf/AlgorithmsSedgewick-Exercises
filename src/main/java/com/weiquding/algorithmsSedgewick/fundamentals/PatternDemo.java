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

    public static boolean findPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        char[] patterns = pattern.toCharArray();
        String[] strs = str.split(",");
        if (patterns.length != strs.length) {
            return false;
        }
        Map<Character, String> char2Str = new HashMap<>();
        Map<String, Character> str2Char = new HashMap<>();
        for (int i = 0; i < patterns.length; i++) {
            if (char2Str.containsKey(patterns[i])) {
                if (!char2Str.get(patterns[i]).equals(strs[i])) {
                    return false;
                }
            } else {
                if (str2Char.containsKey(strs[i])) {
                    return false;
                }
                char2Str.put(patterns[i], strs[i]);
                str2Char.put(strs[i], patterns[i]);
            }
        }
        return true;
    }
}
