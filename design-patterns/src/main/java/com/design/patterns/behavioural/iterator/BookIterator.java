package com.design.patterns.behavioural.iterator;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName BookIterator
 * @Description 具体迭代器
 * @date 2025/3/25 19:12
 **/
public class BookIterator implements Iterator {

    private int index;
    private BookAggregate bookAggregate;

    public BookIterator(BookAggregate bookAggregate) {
        this.index = 0;
        this.bookAggregate = bookAggregate;
    }

    @Override
    public boolean hasNext() {
        return index < bookAggregate.getSize();
    }

    @Override
    public Book next() {
        Book book = bookAggregate.get(index);
        index++;
        return book;
    }
}