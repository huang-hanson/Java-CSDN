package com.design.patterns.behavioural.iterator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName IteratorPattern
 * @Description 测试迭代器模式
 * @date 2025/3/25 19:13
 **/
public class IteratorPattern {
    public static void main(String[] args) {
        BookAggregate bookAggregate = new BookAggregate();

        String[] books = {"数据结构", "操作系统", "计算机网络", "计算机组成原理"};
        double[] prices = {10.24, 20.48, 40.96, 81.92};

        for (int i = 0; i < books.length; i++) {
            bookAggregate.add(new Book(books[i], prices[i]));
        }
        Iterator iterator = bookAggregate.createIterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName() + " " + book.getPrice());
        }
    }
}