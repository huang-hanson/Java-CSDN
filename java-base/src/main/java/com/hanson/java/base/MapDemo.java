package com.hanson.java.base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName MapDemo
 * @date 2024/12/12 10:19
 **/
public class MapDemo {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();

        // getOrDefault
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        String name = map.getOrDefault("3", "张飞");
//        System.out.println(name);

        // putIfAbsent
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        map.putIfAbsent("1", "张飞");
//        System.out.println(map);
//
//        map.putIfAbsent("3", "张飞");
//        System.out.println(map);

        // compute
//        Map<String, Integer> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 2);
//        // 使用compute方法更新键"a"的值
//        map.compute("a", (key, value) -> value == null ? 0 : value + 1);
//        System.out.println(map);
//        // 输出：{a=2, b=2}
//        // 如果键"c"不存在，则插入键"c"和值3
//
//        map.compute("c", (key, value) -> value == null ? 3 : value + 1);
//        System.out.println(map);
//        // 输出：{a=2, b=2, c=3}

        // computeIfAbsent
//        Map<String, Integer> map = new HashMap<>();
//        map.put("a", 2);
//
//        // 如果键"b"不存在，则计算其值并插入
//        map.computeIfAbsent("b", key -> key.length());
//        System.out.println(map);
//        // 输出：{a=2, b=1}
//
//        // 如果键"a"存在，则不进行任何操作
//        map.computeIfAbsent("a", key -> key.length());
//        System.out.println(map);
//        // 输出：{a=2, b=1}

        // computeIfPresent
//        Map<String, Integer> map = new HashMap<>();
//        map.put("a", 1);
//        map.put("b", 2);
//
//        // 如果键"a"存在，则将其值加1
//        map.computeIfPresent("a", (key, value) -> value + 1);
//        System.out.println(map);
//        // 输出：{a=2, b=2}
//
//        // 如果键"c"不存在，则不进行任何操作
//        map.computeIfPresent("c", (key, value) -> value + 1);
//        System.out.println(map);
//        // 输出：{a=2, b=2}

        // merge
//        Map<String, Integer> map = new HashMap<>();
//        map.put("1", 50);
//        map.put("2", 60);
//        //如果key存在1，则求和，否则直接插入map
//        map.merge("1", 50, Integer::sum);
//        System.out.println(map);
//        //输出：{1=100, 2=60}

        // replaceAll
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        //将map中的所有value都加上牛逼二字
//        map.replaceAll((key, value) -> value + "牛逼");
//        System.out.println(map);
//        //输出：{1=刘备牛逼, 2=关羽牛逼}

        // Stream Api
//        Map<String, String> map = new HashMap<>();
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        Map<String, String> result = map.entrySet().stream().
//                filter(entry -> entry.getKey().equals("1")).
//                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        System.out.println(result);
//        //输出结果：{1=刘备}

//        HashMap<String, String> map = new HashMap<>();
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
//        // 输出结果：Key = 1, Value = 刘备 Key = 2, Value = 关羽

//        HashMap<String, String> map = new HashMap<>();
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        for (String key : map.keySet()) {
//            String value = map.get(key);
//            System.out.println("Key = " + key + ", Value = " + value);
//        }
//        // 输出结果：Key = 1, Value = 刘备 Key = 2, Value = 关羽

//        HashMap<String, String> map = new HashMap<>();
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        for (String value : map.values()) {
//            System.out.println("Value:" + value);
//        }
//        // 输出结果：Value:刘备 Value:关羽

//        HashMap<String, String> map = new HashMap<>();
//        map.put("1", "刘备");
//        map.put("2", "关羽");
//        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> entry = iterator.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//            // Key = 1, Value = 刘备
//            // Key = 2, Value = 关羽
//
//            // 如果需要删除某个元素
//            if (entry.getValue().equals("关羽")) {
//                iterator.remove();// 安全地删除当前元素
//            }
//        }
//        System.out.println(map);
//        // 输出结果：{1=刘备}

//        Map<String, Integer> map = new HashMap<>();
//        map.put("A", 1);
//        map.put("B", 2);
//        map.put("C", 3);
//        map.forEach((k, v) -> System.out.println("Key: " + k + ", Value: " + v));
//        // Key: A, Value: 1
//        // Key: B, Value: 2
//        // Key: C, Value: 3

        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.entrySet().stream()
                .forEach(entry -> System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()));
        // Key: A, Value: 1
        // Key: B, Value: 2
        // Key: C, Value: 3
    }
}