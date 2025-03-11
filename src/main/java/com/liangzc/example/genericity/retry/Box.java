package com.liangzc.example.genericity.retry;

/**
 * @Auther: liangzc
 * @Date: 2025/3/11 16:39
 * @Description: 泛型类
 */
public class Box<T> {
    private T content;

    public void setContent(T content){
        this.content = content;
    }

    public T getContent(){
        return content;
    }


    public static void main(String[] args) {
        // 创建一个存储字符串的Box
        Box<String> stringBox = new Box<>();
        stringBox.setContent("hello");
        String boxContent = stringBox.getContent();
        System.out.println(boxContent);

        System.out.println("-------------------------------------------");
        // 创建一个存储整数的Box
        Box<Integer> integerBox = new Box<>();
        integerBox.setContent(123);
        System.out.println(integerBox.getContent());
    }
}
