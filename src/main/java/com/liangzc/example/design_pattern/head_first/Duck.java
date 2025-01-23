package com.liangzc.example.design_pattern.head_first;

/**
 * 因为鸭子飞的特性不是每个鸭子都具备的，比如：玩具鸭就不会飞，所以需要将这种变化的特性提取出来，单独去实现飞的特性，还是要面向接口编程
 * 新建一个DuckFlyHandler接口，不同特性的鸭子去实现该接口，比如：LiveDuck实现类实现了飞的特性；ToyDuckFly实现类实现玩具鸭不会飞的特性
 * <p>
 * 现在、飞和叫的特性都已单独提取出去，由特定类DuckFlyHandler、DuckQuackHandler去处理了，和Duck类没有任何关系了，其他类也可以引用这些处理飞和叫的特定类
 * 所以必须要注释掉fly和quack方法
 * 现在定义DuckFlyHandler、DuckQuackHandler实例变量（接口类型、而不是具体的实现类）,定义相似的newFly（）和newQuack（）方法代替之前的fly（）和quack（）方法
 */
public abstract class Duck {

//    public abstract void fly();  飞的特性是变化的，所以需要提取出去

    /**
     * 所有的鸭子都会游泳和呱呱叫，所以由这个Duck父类处理
     * 而每一种鸭子外观可能不一样，所以定义为抽象方法display()，由具体的子类去实现自己的外观
     */
    public void swim() {
        System.out.println("鸭子在游泳！");
    }

    //呱呱叫的行为已提取出去单独实现 DuckQuackHandler
/*    public void quack(){
        System.out.println("呱呱叫！");
    }*/

    public abstract void display();

    DuckFlyHandler duckFlyHandler;

    DuckQuackHandler duckQuackHandler;

    void newFly() {
        duckFlyHandler.fly();
    }

    void newQuack() {
        duckQuackHandler.quack();
    }

    public void setDuckFlyHandler(DuckFlyHandler flyHandler) {
        this.duckFlyHandler = flyHandler;
    }

    public void setDuckQuackHandler(DuckQuackHandler quackHandler) {
        this.duckQuackHandler = quackHandler;
    }
}
