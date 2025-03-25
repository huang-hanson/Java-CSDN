package com.design.patterns.behavioural.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BookAggregate
 * @Description 具体聚合类 - 书籍集合
 * @date 2025/3/25 19:10
 **/
public class BookAggregate implements Aggregate {

    private List<Book> list = new ArrayList<>();

    public void add(Book book) {
        list.add(book);
    }

    public Book get(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public Iterator createIterator() {
        return new BookIterator(this);
    }
}