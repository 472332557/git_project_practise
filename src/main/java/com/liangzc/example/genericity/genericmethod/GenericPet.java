package com.liangzc.example.genericity.genericmethod;

/**
 * 泛型方法格式：访问修饰符 <T> 方法返回类型 方法名(T t)
 * @param <T>
 */
public class GenericPet<T> {

    private T name;

    //这样的不叫做泛型方法，只能说是泛型类中的成员方法。
    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public void print(){
        System.out.println(name);
    }

    //这样的格式，才称之为泛型方法，注意：泛型方法和泛型类不影响
    public <E> void say(E e){
        System.out.println(e);
    }

    //此时，泛型方法参数类型和泛型类的参数类型都给定为T，泛型方法的类型会随着泛型类的类型吗？
    //答案：不随泛型类类型，泛型方法和泛型类互不影响。
    public <T> void speak(T t){
        System.out.println(t);
    }
}
