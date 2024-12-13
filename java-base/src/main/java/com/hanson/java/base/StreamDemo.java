package com.hanson.java.base;

import com.hanson.java.entity.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName StreamDemo
 * @date 2024/12/12 19:21
 **/
public class StreamDemo {

    public static void main(String[] args) {
        // 创建一个List来存储Person对象
        List<Person> personList = new ArrayList<>();

        // 创建8个Person对象并添加到列表中
        personList.add(new Person(1, "Alice", 5000, 30, "Female", "New York"));
        personList.add(new Person(2, "Bob", 6000, 35, "Male", "Los Angeles"));
        personList.add(new Person(3, "Charlie", 5500, 28, "Male", "Chicago"));
        personList.add(new Person(4, "Diana", 7000, 40, "Female", "Miami"));
        personList.add(new Person(5, "Ethan", 4800, 25, "Male", "Houston"));
        personList.add(new Person(6, "Fiona", 5300, 32, "Female", "Seattle"));
        personList.add(new Person(7, "George", 6200, 38, "Male", "Boston"));
        personList.add(new Person(8, "Hannah", 5900, 29, "Female", "San Francisco"));

//        filter(personList);

//        map(personList);

//        List<String> strings = flatMap(personList);

//        List<Person> peek = peek(personList);

//        reduce(personList);

//        collect(personList);

        String allNames = personList.parallelStream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("All Names (Parallel): " + allNames);
    }

    private static void collect(List<Person> personList) {
        //收集到 List集合
        List<String> names = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        //收集到 Set集合
        Set<String> cities = personList.stream()
                .map(Person::getArea)
                .collect(Collectors.toSet());
        //收集到 Map集合
        Map<Integer, String> idToNameMap = personList.stream()
                .collect(Collectors.toMap(Person::getId, Person::getName));

        Map<Integer, Person> idToPersonMap = personList.stream()
                .collect(Collectors.toMap(Person::getId, v -> v));
        //收集到自定义集合
        List<Person> sortedList = personList.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());
        //使用 Collectors.joining 连接字符串
        String namesString = personList.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        //Collectors.groupingBy
        Map<String, List<Person>> cityToPeopleMap = personList.stream()
                .collect(Collectors.groupingBy(Person::getArea));
        //使用 Collectors.partitioningBy 分区
        Map<Boolean, List<Person>> isAdultMap = personList.stream()
                .collect(Collectors.partitioningBy(person -> person.getAge() >= 18));
        //使用 Collectors.summarizingInt 计算统计信息
        IntSummaryStatistics salarySummary = personList.stream()
                .collect(Collectors.summarizingInt(Person::getSalary));
        double average = salarySummary.getAverage();//平均值
        int max = salarySummary.getMax();//最大值
        long count = salarySummary.getCount();//计数
        int min = salarySummary.getMin();//最小值
        long sum = salarySummary.getSum();//求和
    }

    private static void reduce(List<Person> personList) {
        // 1. 计算总薪资
        int totalSalary = personList.stream()
                .map(Person::getSalary)
                .reduce(0, Integer::sum);
        System.out.println("总薪资: " + totalSalary);

        // 2. 计算平均薪资
        Optional<Double> averageSalary = personList.stream()
                .map(Person::getSalary)
                .reduce((a, b) -> a + b)
                .map(sum -> sum / (double) personList.size());
        System.out.println("平均薪资: " + averageSalary.orElse(0.0));

        // 3. 查找最高薪资
        Optional<Integer> maxSalary = personList.stream()
                .map(Person::getSalary)
                .reduce(Integer::max);
        System.out.println("最高薪资: " + maxSalary.orElse(0));

        // 4. 查找最低薪资
        Optional<Integer> minSalary = personList.stream()
                .map(Person::getSalary)
                .reduce(Integer::min);
        System.out.println("最低薪资: " + minSalary.orElse(0));

        // 5. 计算年龄之和
        int totalAge = personList.stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum);
        System.out.println("总年龄: " + totalAge);
    }

    private static List<Person> peek(List<Person> personList) {
        return personList.stream()
                .peek(person -> System.out.println("Before filter: " + person)) // 打印每个Person对象
                .filter(person -> person.getAge() > 30) // 过滤年龄大于30的Person对象
                .peek(person -> System.out.println("After filter: " + person)) // 打印过滤后的Person对象
                .collect(Collectors.toList());
    }

    private static List<String> flatMap(List<Person> personList) {
        return personList.stream()
                .map(person -> person.getName()) // 将Person对象转换为名字
                .flatMap(name -> Stream.of(name, name.toUpperCase())) // 将名字转换为名字和名字的大写形式
                .collect(Collectors.toList());
    }


    private static void map(List<Person> personList) {
        // 1. 提取所有人的名字
        List<String> names = personList.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("所有人的名字: " + names);

        // 2. 提取所有人的薪资
        List<Integer> salaries = personList.stream()
                .map(Person::getSalary)
                .collect(Collectors.toList());
        System.out.println("所有人的薪资: " + salaries);

        // 3. 提取所有人的地区
        List<String> cities = personList.stream()
                .map(Person::getArea)
                .collect(Collectors.toList());
        System.out.println("所有人的城市: " + cities);

        // 4. 提取所有人的年龄，并加上10
        List<Integer> agesPlus10 = personList.stream()
                .map(person -> person.getAge() + 10)
                .collect(Collectors.toList());
        System.out.println("所有人的年龄加十: " + agesPlus10);

        // 5. 提取薪资信息并格式化为字符串
        List<String> salaryInfo = personList.stream()
                .map(person -> person.getName() + "的薪资为: " + person.getSalary())
                .collect(Collectors.toList());
        System.out.println("薪资信息: " + salaryInfo);
    }

    private static void filter(List<Person> personList) {
        // 1. 筛选出所有女性
        List<Person> females = personList.stream()
                .filter(person -> person.getSex().equalsIgnoreCase("female"))
                .collect(Collectors.toList());
        System.out.println("女性列表: " + females);

        // 2. 筛选出薪资高于5000的人员
        List<Person> highSalary = personList.stream()
                .filter(person -> person.getSalary() > 5000)
                .collect(Collectors.toList());
        System.out.println("薪资高于5000的人员: " + highSalary);

        // 3. 筛选出年龄在30岁及以上的人员
        List<Person> ageAbove30 = personList.stream()
                .filter(person -> person.getAge() >= 30)
                .collect(Collectors.toList());
        System.out.println("年龄在30岁及以上的人员: " + ageAbove30);

        // 4. 筛选出居住在特定城市（例如"New York"）的人员
        List<Person> livingInNewYork = personList.stream()
                .filter(person -> person.getArea().equalsIgnoreCase("New York"))
                .collect(Collectors.toList());
        System.out.println("居住在纽约的人员: " + livingInNewYork);

        // 5. 筛选出名字以"A"开头的人员
        List<Person> namesStartingWithA = personList.stream()
                .filter(person -> person.getName().startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("名字以A开头的人员: " + namesStartingWithA);
    }

}