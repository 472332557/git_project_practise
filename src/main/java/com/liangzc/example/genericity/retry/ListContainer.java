package com.liangzc.example.genericity.retry;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: liangzc
 * @Date: 2025/3/11 17:27
 * @Description:
 */
public class ListContainer<T> implements Container<T> {
    private List<T> items = new ArrayList<>();

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < items.size()){
            return items.get(index);
        }
        throw new IndexOutOfBoundsException("Index out of bounds");
    }


    public static void main(String[] args) {
        //创建一个存储字符串的ListContainer
        Container<String> stringContainer = new ListContainer<>();
        stringContainer.add("grape");
        stringContainer.add("apple");
        try {
            System.out.println(stringContainer.get(0));
            System.out.println(stringContainer.get(1));
            System.out.println(stringContainer.get(2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 创建一个存储整数的ListContainer
        Container<Integer> integerContainer = new ListContainer<>();
        integerContainer.add(1);
        integerContainer.add(2);
        System.out.println(integerContainer.get(0));
        System.out.println(integerContainer.get(1));
    }
}
